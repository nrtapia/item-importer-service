package com.ntapia.itemimporter.infrastructure.client.user;

import com.ntapia.itemimporter.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User responseToDomain(UserResponse userResponse);
}
