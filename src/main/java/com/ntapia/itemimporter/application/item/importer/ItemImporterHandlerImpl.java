package com.ntapia.itemimporter.application.item.importer;

import com.ntapia.itemimporter.application.category.search.FindCategorySearchHandler;
import com.ntapia.itemimporter.application.category.search.FindCategorySearchQuery;
import com.ntapia.itemimporter.application.currency.search.FindCurrencySearchHandler;
import com.ntapia.itemimporter.application.currency.search.FindCurrencySearchQuery;
import com.ntapia.itemimporter.application.item.ItemService;
import com.ntapia.itemimporter.application.item.importer.parser.FileParserStrategyRouter;
import com.ntapia.itemimporter.application.item.search.FindItemSearchHandler;
import com.ntapia.itemimporter.application.item.search.FindItemSearchQuery;
import com.ntapia.itemimporter.application.user.search.FindUserSearchHandler;
import com.ntapia.itemimporter.application.user.search.FindUserSearchQuery;
import com.ntapia.itemimporter.domain.Category;
import com.ntapia.itemimporter.domain.Currency;
import com.ntapia.itemimporter.domain.Item;
import com.ntapia.itemimporter.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;

@Service
public class ItemImporterHandlerImpl implements ItemImporterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemImporterHandlerImpl.class);

    @Value("${app.importer.clientWaitTimeout}")
    private long clientWaitTimeout;

    private final ExecutorService executorService;

    private final FindCurrencySearchHandler findCurrencySearchHandler;
    private final FindCategorySearchHandler findCategorySearchHandler;
    private final FindUserSearchHandler findUserSearchHandler;
    private final FindItemSearchHandler findItemSearchHandler;

    private final ItemService itemService;

    public ItemImporterHandlerImpl(FindCurrencySearchHandler findCurrencySearchHandler, FindCategorySearchHandler findCategorySearchHandler, FindUserSearchHandler findUserSearchHandler, FindItemSearchHandler findItemSearchHandler, ItemService itemService) {
        this.findCurrencySearchHandler = findCurrencySearchHandler;
        this.findCategorySearchHandler = findCategorySearchHandler;
        this.findUserSearchHandler = findUserSearchHandler;
        this.findItemSearchHandler = findItemSearchHandler;
        this.itemService = itemService;

        executorService = Executors.newVirtualThreadPerTaskExecutor();
    }

    @Override
    public void execute(ItemImporterCommand itemImporterCommand) {
        LOGGER.debug("Item importer for: {}", itemImporterCommand);

        FileParserStrategyRouter.apply(itemImporterCommand, itemDTO -> {
            executorService.submit(() -> importItem(itemDTO));
        });
    }

    private void importItem(ItemDTO itemDTO) {
        LOGGER.debug("Processing request item: {}", itemDTO);

        findItem(itemDTO).ifPresent(item -> {
            item.setId(itemDTO.itemId());
            item.setSiteId(itemDTO.site());

            var categoryFuture = findCategory(item);
            var currencyFuture = findCurrency(item);
            var userFuture = findUser(item);

            var combinedFuture = CompletableFuture.allOf(categoryFuture, currencyFuture, userFuture);

            try {
                combinedFuture.get(clientWaitTimeout, TimeUnit.SECONDS);
                var category = categoryFuture.get();
                var currency = currencyFuture.get();
                var user = userFuture.get();

                Optional.of(category).ifPresent(item::setCategory);
                Optional.of(currency).ifPresent(item::setCurrency);
                Optional.of(user).ifPresent(item::setSeller);

                LOGGER.debug("Item data collected: {}", item);
                itemService.persist(item);

            } catch (InterruptedException e) {
                LOGGER.error("Importer interrupted: {}", e.getMessage(), e);
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw new ItemImporterResultException(e);
            } catch (TimeoutException e) {
                throw new ItemImporterTimeoutException(e);
            }
        });
    }

    private Optional<Item> findItem(ItemDTO itemDTO) {
        return Optional.of(this.findItemSearchHandler.handle(new FindItemSearchQuery(itemDTO.site() + itemDTO.itemId())));
    }

    private CompletableFuture<User> findUser(Item item) {
        return CompletableFuture.supplyAsync(() -> {
                    if (Objects.nonNull(item.getSeller()) && Objects.nonNull(item.getSeller().getId())) {
                        return findUserSearchHandler.handle(new FindUserSearchQuery(item.getSeller().getId()));
                    }
                    return null;
                }, this.executorService
        );
    }

    private CompletableFuture<Currency> findCurrency(Item item) {
        return CompletableFuture.supplyAsync(() -> {
                    if (Objects.nonNull(item.getCurrency()) && Objects.nonNull(item.getCurrency().getId())) {
                        return findCurrencySearchHandler.handle(new FindCurrencySearchQuery(item.getCurrency().getId()));
                    }
                    return null;
                }, this.executorService
        );
    }

    private CompletableFuture<Category> findCategory(Item item) {
        return CompletableFuture.supplyAsync(() -> {
                    if (Objects.nonNull(item.getCategory()) && Objects.nonNull(item.getCategory().getId())) {
                        return findCategorySearchHandler.handle(new FindCategorySearchQuery(item.getCategory().getId()));
                    }
                    return null;
                }, this.executorService
        );
    }
}
