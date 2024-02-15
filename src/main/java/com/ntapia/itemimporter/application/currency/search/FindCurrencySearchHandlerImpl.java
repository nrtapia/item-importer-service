package com.ntapia.itemimporter.application.currency.search;

import com.ntapia.itemimporter.domain.Currency;
import com.ntapia.itemimporter.domain.CurrencyRepository;
import com.ntapia.itemimporter.application.category.CategoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindCurrencySearchHandlerImpl implements FindCurrencySearchHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindCurrencySearchHandlerImpl.class);

    private final CurrencyRepository currencyRepository;

    public FindCurrencySearchHandlerImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency handle(FindCurrencySearchQuery query) {
        LOGGER.debug("Currency searching for: {}", query);
        return currencyRepository.findById(query.id()).orElseThrow(CategoryNotFoundException::new);
    }
}