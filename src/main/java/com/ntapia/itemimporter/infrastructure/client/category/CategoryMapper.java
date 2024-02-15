package com.ntapia.itemimporter.infrastructure.client.category;

import com.ntapia.itemimporter.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category responseToDomain(CategoryResponse categoryResponse);
}
