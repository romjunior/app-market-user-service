package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import com.appmarket.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public interface CreateUserUseCase {

    UUID createUser(final CreateUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class CreateUserCommand extends SelfValidating<CreateUserCommand> {
        @NotEmpty
        String name;
        @NotEmpty
        @Size(max = 14, min = 11)
        String document;
        @NotEmpty
        String login;
        @NotNull
        @Email
        String email;
        @NotEmpty
        @Size(max = 8, min = 5)
        String password;
        @Size(max = 8, min = 5)
        String confirmedPassword;

        @Builder
        public CreateUserCommand(final String name, final String document,
                                 final String login, final String email,
                                 final String password,
                                 final String confirmedPassword) {
            this.name = name;
            this.document = document;
            this.login = login;
            this.email = email;
            this.password = password;
            this.confirmedPassword = confirmedPassword;
            this.validateSelf();
            this.validatePassword(password, confirmedPassword);
        }

        private void validatePassword(final String password, final String confirmedPassword) {
            if(!password.equals(confirmedPassword)) {
                throw new PasswordNotMatchException();
            }
        }
    }
}
