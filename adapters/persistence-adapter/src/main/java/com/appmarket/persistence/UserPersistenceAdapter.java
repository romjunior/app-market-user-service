package com.appmarket.persistence;

import com.appmarket.application.port.out.AddRole;
import com.appmarket.application.port.out.CreateUser;
import com.appmarket.application.port.out.DeactivateUser;
import com.appmarket.application.port.out.EditUser;
import com.appmarket.application.port.out.GetRoleByUserId;
import com.appmarket.application.port.out.SearchUserByEmailOrLogin;
import com.appmarket.application.port.out.SearchUserById;
import com.appmarket.domain.User;
import com.appmarket.persistence.mapper.UserMapper;
import com.appmarket.persistence.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class UserPersistenceAdapter implements CreateUser, EditUser, SearchUserByEmailOrLogin, SearchUserById, DeactivateUser {

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
        return userMapper.toUser(userRepository.save(userMapper.toUserEntity(user)));
    }

    @Override
    public List<User> searchUserByEmailOrLogin(final String email, final String login) {
        return userMapper.toUserList(userRepository.findAllByEmailOrLogin(email, login));
    }

    @Override
    public Optional<User> searchUserById(UUID id) {
        return userRepository.findById(id)
                .map(userEntity -> {
                    final var roles = getRoleByUserId.getRolesByUserId(userEntity.getId());
                    return userMapper.toUserWithRoles(userEntity, roles);
                });
    }

    @Override
    public User deactivateUser(User user) {
        return editUser(user);
    }
}
