package com.appmarket.persistence.mapper;

import com.appmarket.domain.User;
import com.appmarket.persistence.model.UserEntity;

import java.util.List;

public interface ListUserEntityToUserMapper {

    List<User> toUserList(List<UserEntity> entities);
}
