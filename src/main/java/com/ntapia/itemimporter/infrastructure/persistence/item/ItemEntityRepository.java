package com.ntapia.itemimporter.infrastructure.persistence.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {
}
