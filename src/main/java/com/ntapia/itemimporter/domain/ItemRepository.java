package com.ntapia.itemimporter.domain;

import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findById(String id);

    void persist(Item item);
}
