package com.ntapia.itemimporter.application.category.search;

import com.ntapia.itemimporter.domain.Category;
import com.ntapia.itemimporter.domain.CategoryRepository;
import com.ntapia.itemimporter.application.category.CategoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindCategorySearchHandlerImpl implements FindCategorySearchHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindCategorySearchHandlerImpl.class);

    private final CategoryRepository categoryRepository;

    public FindCategorySearchHandlerImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category handle(FindCategorySearchQuery query) {
        LOGGER.debug("Category searching for: {}", query);
        return categoryRepository.findById(query.id()).orElseThrow(CategoryNotFoundException::new);
    }
}