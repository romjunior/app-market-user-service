package com.appmarket.application.port.in;

import com.appmarket.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

class CreateUserCommandTest {


    @Test
    void deveRetornarExceptionCasoOnomeDocumentEmailELoginSejaVazio() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            CreateUserUseCase.CreateUserCommand.builder().build();
        });
    }

    @Test
    void deveRetornarExceptionCasoODocumentoFiqueForaDoTamanhoEsperado() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            CreateUserUseCase.CreateUserCommand.builder()
                    .name("teste")
                    .login("teste")
                    .email("teste@gmail.com")
                    .password("12345")
                    .confirmedPassword("12345")
                    .document("123456789101112")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoEmailNaoEstejaNoFormatoEsperado() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            CreateUserUseCase.CreateUserCommand.builder()
                    .name("teste")
                    .login("teste")
                    .email("testegmail.com")
                    .password("12345")
                    .confirmedPassword("12345")
                    .document("123456789101")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoSenhaNaoEstejaNoTamanhoMinimoEsperado() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            CreateUserUseCase.CreateUserCommand.builder()
                    .name("teste")
                    .login("teste")
                    .email("teste@gmail.com")
                    .password("123")
                    .confirmedPassword("123")
                    .document("123456789101")
                    .build();
        });
    }

    @Test
    void deveRetornarExceptionCasoAsSenhasNaoCorrespondam() {
        Assertions.assertThrows(PasswordNotMatchException.class, () -> {
            CreateUserUseCase.CreateUserCommand.builder()
                    .name("teste")
                    .login("teste")
                    .email("teste@gmail.com")
                    .password("12345")
                    .confirmedPassword("123456")
                    .document("123456789101")
                    .build();
        });
    }

}
