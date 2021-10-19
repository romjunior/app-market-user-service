package com.appmarket.persistence;

import com.appmarket.application.port.out.AddRole;
import com.appmarket.exception.InconsistencyException;
import com.appmarket.persistence.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RolePersistencyAdapter implements AddRole {

    RoleRepository roleRepository;

    @Override
    public void addRoleToUser(final UUID id, final String role) {
        roleRepository.findByName(role)
                .map(roleEntity -> roleRepository.saveRelation(id, roleEntity.getId()))
                .orElseThrow(() -> new InconsistencyException("role does not exists"));
    }
}
