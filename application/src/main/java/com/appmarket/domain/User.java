package com.appmarket.domain;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

public record User(UUID id, String name, String document, String login, String email, String password, boolean active, Set<String> roles) {
    @Builder
    public User {}
}
