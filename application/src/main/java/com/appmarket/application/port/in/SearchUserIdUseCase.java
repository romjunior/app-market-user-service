package com.appmarket.application.port.in;

import com.appmarket.common.SelfValidating;
import com.appmarket.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

public interface SearchUserIdUseCase {

    Optional<User> searchUserId(final SearchUserIdCommand command);

    @Value
    @EqualsAndHashCode(callSuper = false)
    class SearchUserIdCommand extends SelfValidating<SearchUserIdCommand> {
        @NotNull
        UUID id;

        private SearchUserIdCommand(final UUID id) {
            this.id = id;
            this.validateSelf();
        }

        public static SearchUserIdCommand of(final UUID id) {
            return new SearchUserIdCommand(id);
        }
    }

}
