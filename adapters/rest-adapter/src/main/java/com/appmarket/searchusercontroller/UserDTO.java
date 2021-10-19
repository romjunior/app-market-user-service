package com.appmarket.searchusercontroller;

import com.appmarket.domain.User;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

record UserDTO(UUID id, String name, String document, String login, String email, String password, boolean active, Set<String> roles) {
    @Builder
    public UserDTO {}

    public static UserDTO buildDTO(final User user) {
        return UserDTO.builder()
                .id(user.id())
                .document(user.document())
                .email(user.email())
                .login(user.login())
                .name(user.name())
                .active(user.active())
                .roles(user.roles())
                .password(user.password())
                .build();
    }
}
