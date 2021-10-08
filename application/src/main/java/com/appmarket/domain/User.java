package com.appmarket.domain;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

public record User(UUID id, String name, String document, String login, String email, String password, boolean active, Set<String> roles) {
    @Builder
    public User {}

    public User deactivateUser() {
        return User.builder()
                .id(id)
                .name(name)
                .document(document)
                .login(login)
                .email(email)
                .password(password)
                .active(false)
                .roles(roles)
                .build();
    }
}
