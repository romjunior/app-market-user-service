package com.appmarket.persistence;

import com.appmarket.application.port.out.AddRole;
import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.GetRoleByUserId;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.application.port.out.SearchUserByQuery;
import com.appmarket.domain.User;
import com.appmarket.persistence.mapper.UserMapper;
import com.appmarket.persistence.repository.UserRepository;
import com.appmarket.persistence.specification.UserSearchSpecification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class UserPersistenceAdapter implements CreateUser, EditUser, SearchUserByEmailOrLogin, SearchUserById, SearchUserByQuery {

    UserRepository userRepository;
    AddRole addRole;
    GetRoleByUserId getRoleByUserId;
    UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(final User user) {
        final var userResult = userRepository.save(
                userMapper.toUserEntity(user)
        );
        user.roles().forEach(role -> addRole.addRoleToUser(userResult.getId(), role));
        return userMapper.toUserWithRoles(userResult, user.roles());
    }

    @Override
    public User editUser(final User user) {
     return userMapper.toUserWithRoles(userRepository.save(userMapper.toUserEntity(user)), user.roles());
    }

    @Override
    public List<User> searchUserByEmailOrLogin(final String email, final String login) {
        return userRepository.findAllByEmailOrLogin(email, login)
                .stream().map(userEntity -> userMapper.toUserWithRoles(
                        userEntity,
                        getRoleByUserId.getRolesByUserId(userEntity.getId())
                    )
                ).toList();
    }

    @Override
    public Optional<User> searchUserById(final UUID id) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    final var roles = getRoleByUserId.getRolesByUserId(userEntity.getId());
                    return userMapper.toUserWithRoles(userEntity, roles);
                });
    }

    @Override
    public List<User> searchUser(final String name, final String email, final String login, final String document) {
        return userRepository.findAll(UserSearchSpecification.buildSearch(name, email, login, document))
                .stream().map(userEntity -> {
                    final var roles = getRoleByUserId.getRolesByUserId(userEntity.getId());
                    return userMapper.toUserWithRoles(userEntity, roles);
                }).toList();
    }
}
