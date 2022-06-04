package com.appmarket.application.port.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

class SearchUserIdCommandTest {

    @Test
    void deveRetornarUmaExcecaoCasoOidSejaNulo() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> SearchUserIdUseCase.SearchUserIdCommand.of(null));
    }

    @Test
    void deveConstruirUmObjetoValidoEretornaroUUID() {
        final var uuid = UUID.randomUUID();
        final var command = SearchUserIdUseCase.SearchUserIdCommand.of(uuid);

        Assertions.assertEquals(uuid, command.getId());
    }

}
