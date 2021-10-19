package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;

//@Mapper(componentModel = "spring")
public interface UserToUserEntityMapper {
    UserEntity toUserEntity(final User user);
}
