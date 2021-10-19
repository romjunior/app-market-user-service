package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import com.appmarket.domain.User;
import com.appmarket.exception.PasswordNotMatchException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;

public interface EditUserUseCase {

    Optional<User> editUser(final EditUserCommand editUserCommand);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class EditUserCommand extends SelfValidating<EditUserCommand> {
        @NotNull
        UUID id;
        @NotEmpty
        String name;
        @NotEmpty
        @Size(max = 14, min = 11)
        String document;
        @Email
        String email;
        @Size(max = 8, min = 5)
        String password;
        @Size(max = 8, min = 5)
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

        private void validatePassword(final String password, final String confirmedPassword) {
            if(null != password && !password.equals(confirmedPassword)) {
                throw new PasswordNotMatchException("Senhas não são iguais");
            }
        }
    }

}
