package com.appmarket.application.port.in;

import com.appmarket.exception.PasswordNotMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import java.util.UUID;

class EditUserCommandTest {

    @Test
    void deveRetornarExceptionCasoOnomeDocumentEmailSejaVazio() {
        var editUserCommand = EditUserUseCase.EditUserCommand.builder();
        Assertions.assertThrows(ConstraintViolationException.class, editUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoOdocumentoFiqueForaDoTamanhoEsperado() {
        var editUserCommand = EditUserUseCase.EditUserCommand.builder()
                .id(UUID.randomUUID())
                .name("teste")
                .email("teste@gmail.com")
                .document("123456789101112");

        Assertions.assertThrows(ConstraintViolationException.class, editUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoEmailNaoEstejaNoFormatoEsperado() {
        var editUserCommand =  EditUserUseCase.EditUserCommand.builder()
                .id(UUID.randomUUID())
                .name("teste")
                .email("testegmail.com")
                .document("12345678901");

        Assertions.assertThrows(ConstraintViolationException.class, editUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoAsSenhasNaoCorrespondam() {
        var editUserCommand = EditUserUseCase.EditUserCommand.builder()
                .id(UUID.randomUUID())
                .name("teste")
                .email("teste@gmail.com")
                .password("12345")
                .confirmedPassword("123456")
                .document("123456789101");

        Assertions.assertThrows(PasswordNotMatchException.class, editUserCommand::build);
    }

    @Test
    void deveRetornarExceptionCasoAsenhaSejaNula() {
        var editUserCommand = EditUserUseCase.EditUserCommand.builder()
                .id(UUID.randomUUID())
                .name("teste")
                .email("teste@gmail.com")
                .password("12345")
                .document("123456789101");

        Assertions.assertThrows(PasswordNotMatchException.class, editUserCommand::build);
    }

}
