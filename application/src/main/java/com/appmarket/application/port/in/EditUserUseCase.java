package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import com.appmarket.domain.User;
import com.appmarket.exception.PasswordNotMatchException;
import com.appmarket.exception.UserNotFoundException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface EditUserUseCase {

    User editUser(final EditUserCommand editUserCommand) throws UserNotFoundException;

    @Value
    @EqualsAndHashCode(callSuper = false)
    class EditUserCommand extends SelfValidating<EditUserCommand> {
        @NotNull
        @NotEmpty
        UUID id;
        @NotNull
        @NotEmpty
        String name;
        @NotNull
        @NotEmpty
        String document;
        @NotNull
        @Email
        String email;
        String password;
        String confirmedPassword;

        @Builder
        public EditUserCommand(
                final UUID id,
                final String name,
                final String document,
                final String email,
                final String password,
                final String confirmedPassword) throws PasswordNotMatchException {
            this.id = id;
            this.name = name;
            this.document = document;
            this.email = email;
            this.password = password;
            this.confirmedPassword = confirmedPassword;
            this.validateSelf();
            this.validatePassword(password, confirmedPassword);
        }

        private void validatePassword(final String password, final String confirmedPassword) throws PasswordNotMatchException {
            if(null != password && !password.equals(confirmedPassword)) {
                throw new PasswordNotMatchException("Senhas não são iguais");
            }
        }
    }

}
