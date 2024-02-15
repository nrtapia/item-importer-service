package com.ntapia.itemimporter.infrastructure.client.currency;

import com.ntapia.itemimporter.domain.Currency;
import com.ntapia.itemimporter.domain.CurrencyRepository;
import com.ntapia.itemimporter.infrastructure.config.MeliRestClient;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrencyRepositoryRestClientImpl implements CurrencyRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRepositoryRestClientImpl.class);

    private final MeliRestClient meliRestClient;

    public CurrencyRepositoryRestClientImpl(MeliRestClient meliRestClient) {
        this.meliRestClient = meliRestClient;
    }

    @Override
    public Optional<Currency> findById(String id) {
        try {
            var currencyResponse = meliRestClient.getCurrency(id);
            return Optional.of(CurrencyMapper.INSTANCE.responseToDomain(currencyResponse));
        } catch (FeignException.NotFound ignored) {
            return Optional.empty();
        } catch (FeignException fex) {
            LOGGER.error("Failed to find currency: {} {}", id, fex.getMessage(), fex);
            throw new FindCurrencyException(String.format("Id: %s - %s", id, fex.getMessage()), fex);
        }
    }
}
