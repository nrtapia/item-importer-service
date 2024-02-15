package com.ntapia.itemimporter.application.item;

import com.ntapia.itemimporter.domain.Item;
import com.ntapia.itemimporter.domain.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public void persist(Item item) {
        itemRepository.persist(item);
    }
}
