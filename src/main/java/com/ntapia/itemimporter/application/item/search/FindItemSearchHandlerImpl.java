package com.ntapia.itemimporter.application.item.search;

import com.ntapia.itemimporter.domain.Item;
import com.ntapia.itemimporter.domain.ItemRepository;
import com.ntapia.itemimporter.application.item.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FindItemSearchHandlerImpl implements FindItemSearchHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindItemSearchHandlerImpl.class);

    private final ItemRepository itemRepository;

    public FindItemSearchHandlerImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item handle(FindItemSearchQuery query) {
        LOGGER.debug("Item searching for: {}", query);
        return itemRepository.findById(query.id()).orElseThrow(ItemNotFoundException::new);
    }
}
