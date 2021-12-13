package com.appmarket.application.service;

import com.appmarket.application.port.in.SearchUserIdUseCase;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

class SearchUserServiceTest {

    @Mock
    private SearchUserById searchUserById;

    private SearchUserService searchUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        searchUserService = new SearchUserService(searchUserById);
    }

    @Test
    void deveRetornarOUsuarioDadoUmIdExistenteNaBase() {
        final var uuid = UUID.randomUUID();
        final var user = User.builder()
                .build();
        Mockito.when(searchUserById.searchUserById(uuid)).thenReturn(Optional.of(user));

        final var result = searchUserService.searchUserId(SearchUserIdUseCase.SearchUserIdCommand.of(uuid));

        Assertions.assertEquals(Optional.of(user), result);
    }

    @Test
    void deveRetornarUmOptionalEmptyCasoNaoPossuaUmUsuarioRegistrado() {
        final var uuid = UUID.randomUUID();
        Mockito.when(searchUserById.searchUserById(uuid)).thenReturn(Optional.empty());

        final var result = searchUserService.searchUserId(SearchUserIdUseCase.SearchUserIdCommand.of(uuid));

        Assertions.assertEquals(Optional.empty(), result);
    }
}
