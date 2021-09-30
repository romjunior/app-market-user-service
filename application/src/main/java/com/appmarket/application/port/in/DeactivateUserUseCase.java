package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public interface DeactivateUserUseCase {

    void deactivateUser(final DeactivateUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeactivateUserCommand extends SelfValidating<DeactivateUserCommand> {
        @NotNull
        @NotEmpty
        UUID id;

        public DeactivateUserCommand(final UUID id) {
            this.id = id;
            this.validateSelf();
        }
    }

}
