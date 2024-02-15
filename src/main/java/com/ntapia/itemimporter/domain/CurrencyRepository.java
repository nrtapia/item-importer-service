package com.ntapia.itemimporter.domain;

import java.util.Optional;

public interface CurrencyRepository {

    Optional<Currency> findById(String id);
}
