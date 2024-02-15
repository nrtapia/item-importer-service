package com.ntapia.itemimporter.infrastructure.client.item;

import com.ntapia.itemimporter.domain.Item;
import com.ntapia.itemimporter.infrastructure.persistence.item.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "currency.id", source = "currencyId")
    @Mapping(target = "seller.id", source = "sellerId")
    Item responseToDomain(ItemResponse itemResponse);

    @Mapping(target = "site", source = "siteId")
    @Mapping(target = "name", source = "category.name")
    @Mapping(target = "description", source = "currency.description")
    @Mapping(target = "nickname", source = "seller.nickname")
    ItemEntity domainToEntity(Item item);
}
