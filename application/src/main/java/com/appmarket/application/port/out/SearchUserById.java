package com.appmarket.application.port.out;

import com.appmarket.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface SearchUserById {

    Optional<User> searchUserById(final UUID id);
}
