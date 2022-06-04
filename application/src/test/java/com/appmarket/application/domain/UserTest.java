package com.appmarket.application.domain;

import com.appmarket.domain.Role;
import com.appmarket.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

class UserTest {

    @Test
    void deveRetornarOmesmoUsuarioDesativado() {
        final var user = User.builder()
                .id(UUID.randomUUID())
                .login("jhon")
                .email("jhon@gmail.com")
                .name("jhon silva")
                .roles(Set.of(Role.DEFAULT.name()))
                .document("12345678910")
                .password("1234567")
                .active(true)
                .build();

        final var expectedUser = User.builder()
                .id(user.id())
                .login(user.login())
                .email(user.email())
                .name(user.name())
                .roles(user.roles())
                .document(user.document())
                .password(user.password())
                .active(false)
                .build();

        final var result = user.deactivateUser();

        Assertions.assertEquals(expectedUser, result);
    }

}
