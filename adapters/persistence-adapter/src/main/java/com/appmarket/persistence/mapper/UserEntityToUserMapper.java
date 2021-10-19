package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;

//@Mapper(componentModel = "spring")
public interface UserEntityToUserMapper {

    User toUser(final UserEntity userEntity);

}
