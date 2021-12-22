package com.appmarket.persistence;

import com.appmarket.application.port.out.AddRole;
import com.appmarket.application.port.out.GetRoleByUserId;
import com.appmarket.exception.RoleNotExistsException;
import com.appmarket.persistence.model.RoleEntity;
import com.appmarket.persistence.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RolePersistencyAdapter implements AddRole, GetRoleByUserId {

    RoleRepository roleRepository;

    @Override
    public void addRoleToUser(final UUID id, final String role) {
        roleRepository.findByName(role)
                .map(roleEntity -> roleRepository.saveRelation(id, roleEntity.getId()))
                .orElseThrow(RoleNotExistsException::new);
    }

    @Override
    public Set<String> getRolesByUserId(UUID userId) {
        final var roleIds = roleRepository.findAllRolesIdsByUserId(userId);
        return roleRepository.findAllByIdIn(roleIds)
                .stream().map(RoleEntity::getName)
                .collect(Collectors.toSet());
    }
}
