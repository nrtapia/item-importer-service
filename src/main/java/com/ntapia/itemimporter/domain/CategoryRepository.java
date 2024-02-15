package com.ntapia.itemimporter.domain;

import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(String id);
}
