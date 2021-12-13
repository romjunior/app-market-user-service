package com.appmarket.application.port.in;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

class DeactivateUserCommandTest {

    @Test
    void deveRetornarUmaExcecaoCasoOIdSejaNulo() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> DeactivateUserUseCase.DeactivateUserCommand.of(null));
    }

    @Test
    void deveConstruirUmObjetoValidoERetornarOUUID() {
        final var uuid = UUID.randomUUID();
        final var command = DeactivateUserUseCase.DeactivateUserCommand.of(uuid);

        Assertions.assertEquals(uuid, command.getId());
    }
}
