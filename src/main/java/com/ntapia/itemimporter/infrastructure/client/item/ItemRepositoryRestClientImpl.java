package com.ntapia.itemimporter.infrastructure.client.item;

import com.ntapia.itemimporter.domain.Item;
import com.ntapia.itemimporter.domain.ItemRepository;
import com.ntapia.itemimporter.infrastructure.config.MeliRestClient;
import com.ntapia.itemimporter.infrastructure.persistence.item.ItemEntityRepository;
import com.ntapia.itemimporter.infrastructure.persistence.item.PersistItemException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemRepositoryRestClientImpl implements ItemRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemRepositoryRestClientImpl.class);

    private final MeliRestClient meliRestClient;
    private final ItemEntityRepository itemEntityRepository;

    public ItemRepositoryRestClientImpl(MeliRestClient meliRestClient, ItemEntityRepository itemEntityRepository) {
        this.meliRestClient = meliRestClient;
        this.itemEntityRepository = itemEntityRepository;
    }

    @Override
    public Optional<Item> findById(String id) {
        try {
            var itemResponse = meliRestClient.getItem(id);
            return Optional.of(ItemMapper.INSTANCE.responseToDomain(itemResponse));
        } catch (FeignException.NotFound ignored) {
            return Optional.empty();
        } catch (FeignException fex) {
            LOGGER.error("Failed to find item: {} {}", id, fex.getMessage(), fex);
            throw new FindItemException(String.format("Id: %s - %s", id, fex.getMessage()), fex);
        }
    }

    @Override
    public void persist(Item item) {
        LOGGER.info("Item persisting: {}", item);

        var itemEntity = ItemMapper.INSTANCE.domainToEntity(item);
        try {
            var entity = this.itemEntityRepository.save(itemEntity);
            LOGGER.info("Item entity persisted: {}", entity);
        } catch (Exception exception) {
            throw new PersistItemException(String.format("Id: %s - %s", item.getSiteId() + item.getId(), exception.getMessage()), exception);
        }
    }
}
