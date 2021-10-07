package com.appmarket.application.port.in;

import com.appmarket.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

class EditUserCommandTest {

    @Test
    void deveRetornarExceptionCasoOnomeDocumentEmailSejaVazio() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            EditUserUseCase.EditUserCommand.builder().build();
        });
    }

    @Test
    void deveRetornarExceptionCasoODocumentoFiqueForaDoTamanhoEsperado() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            EditUserUseCase.EditUserCommand.builder()
                    .id(UUID.randomUUID())
                    .name("teste")
                    .email("teste@gmail.com")
                    .document("123456789101112")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoEmailNaoEstejaNoFormatoEsperado() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            EditUserUseCase.EditUserCommand.builder()
                    .id(UUID.randomUUID())
                    .name("teste")
                    .email("testegmail.com")
                    .document("12345678901")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoAsSenhasNaoCorrespondam() {
        Assertions.assertThrows(PasswordNotMatchException.class, () -> {
            EditUserUseCase.EditUserCommand.builder()
                    .id(UUID.randomUUID())
                    .name("teste")
                    .email("teste@gmail.com")
                    .password("12345")
                    .confirmedPassword("123456")
                    .document("123456789101")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoASenhaSejaNula() {
        Assertions.assertThrows(PasswordNotMatchException.class, () -> {
            EditUserUseCase.EditUserCommand.builder()
                    .id(UUID.randomUUID())
                    .name("teste")
                    .email("teste@gmail.com")
                    .password("12345")
                    .document("123456789101")
                    .build();
        });
    }

}
