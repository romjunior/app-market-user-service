package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityToUserMapper {

    User toUser(final UserEntity userEntity);

}
