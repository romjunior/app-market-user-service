package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListUserEntityToUserMapper {

    List<User> toUserList(List<UserEntity> entities);
}
