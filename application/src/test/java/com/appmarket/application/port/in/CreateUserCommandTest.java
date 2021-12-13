package com.appmarket.application.port.in;

import com.appmarket.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

class CreateUserCommandTest {


    @Test
    void deveRetornarExceptionCasoOnomeDocumentEmailELoginSejaVazio() {
        var createUserCommand = CreateUserUseCase.CreateUserCommand.builder();
        Assertions.assertThrows(ConstraintViolationException.class, createUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoODocumentoFiqueForaDoTamanhoEsperado() {
        var createUserCommand = CreateUserUseCase.CreateUserCommand.builder()
                .name("teste")
                .login("teste")
                .email("teste@gmail.com")
                .password("12345")
                .confirmedPassword("12345")
                .document("123456789101112");

        Assertions.assertThrows(ConstraintViolationException.class, createUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoEmailNaoEstejaNoFormatoEsperado() {
        var createUserCommand = CreateUserUseCase.CreateUserCommand.builder()
                .name("teste")
                .login("teste")
                .email("testegmail.com")
                .password("12345")
                .confirmedPassword("12345")
                .document("123456789101");

        Assertions.assertThrows(ConstraintViolationException.class, createUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoSenhaNaoEstejaNoTamanhoMinimoEsperado() {
        var createUserCommand = CreateUserUseCase.CreateUserCommand.builder()
                .name("teste")
                .login("teste")
                .email("teste@gmail.com")
                .password("123")
                .confirmedPassword("123")
                .document("123456789101");

        Assertions.assertThrows(ConstraintViolationException.class, createUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoAsSenhasNaoCorrespondam() {
        var createUserCommand = CreateUserUseCase.CreateUserCommand.builder()
                .name("teste")
                .login("teste")
                .email("teste@gmail.com")
                .password("12345")
                .confirmedPassword("123456")
                .document("123456789101");

        Assertions.assertThrows(PasswordNotMatchException.class, createUserCommand::build);
    }

}
