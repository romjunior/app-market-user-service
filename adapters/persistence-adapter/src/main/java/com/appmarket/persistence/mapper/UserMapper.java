package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(final UserEntity userEntity);

    @Mapping(source = "roles", target = "roles")
    User toUserWithRoles(final UserEntity userEntity, final Set<String> roles);

    List<User> toUserList(List<UserEntity> entities);

    UserEntity toUserEntity(final User user);

}
