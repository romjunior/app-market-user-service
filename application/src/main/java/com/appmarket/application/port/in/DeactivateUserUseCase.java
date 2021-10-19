package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface DeactivateUserUseCase {

    Optional<UUID> deactivateUser(final DeactivateUserCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class DeactivateUserCommand extends SelfValidating<DeactivateUserCommand> {
        @NotNull
        UUID id;

        private DeactivateUserCommand(final UUID id) {
            this.id = id;
            this.validateSelf();
        }

        public static DeactivateUserCommand of(final UUID id) {
            return new DeactivateUserCommand(id);
        }
    }

}
