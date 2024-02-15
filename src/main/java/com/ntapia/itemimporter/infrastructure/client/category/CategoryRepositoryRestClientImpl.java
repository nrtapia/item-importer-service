package com.ntapia.itemimporter.infrastructure.client.category;

import com.ntapia.itemimporter.domain.Category;
import com.ntapia.itemimporter.domain.CategoryRepository;
import com.ntapia.itemimporter.infrastructure.config.MeliRestClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryRepositoryRestClientImpl implements CategoryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryRepositoryRestClientImpl.class);

    private final MeliRestClient meliRestClient;

    public CategoryRepositoryRestClientImpl(MeliRestClient meliRestClient) {
        this.meliRestClient = meliRestClient;
    }

    @Override
    public Optional<Category> findById(String id) {
        try {
            var categoryResponse = meliRestClient.getCategory(id);
            return Optional.of(CategoryMapper.INSTANCE.responseToDomain(categoryResponse));
        } catch (FeignException.NotFound ignored) {
            return Optional.empty();
        } catch (FeignException fex) {
            LOGGER.error("Failed to find category: {} {}", id, fex.getMessage(), fex);
            throw new FindCategoryException(String.format("Id: %s - %s", id, fex.getMessage()), fex);
        }
    }
}
