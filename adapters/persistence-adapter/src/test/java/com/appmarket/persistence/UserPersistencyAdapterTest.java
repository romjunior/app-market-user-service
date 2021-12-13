package com.appmarket.persistence;

import com.appmarket.application.port.out.AddRole;
import com.appmarket.application.port.out.GetRoleByUserId;
import com.appmarket.domain.Role;
import com.appmarket.domain.User;
import com.appmarket.persistence.mapper.UserMapper;
import com.appmarket.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@DataJpaTest
@EnableJpaAuditing
class UserPersistencyAdapterTest {

    @Autowired
    UserRepository userRepository;

    UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @MockBean
    AddRole addRole;

    @MockBean
    GetRoleByUserId getRoleByUserId;

    UserPersistenceAdapter userPersistenceAdapter;

    @BeforeEach
    void setUp() {
        this.userPersistenceAdapter = new UserPersistenceAdapter(userRepository, addRole, getRoleByUserId, mapper);
    }

    @Test
    void QuandoSalvarUmUsuario_AoConsultarDevoRetornarEle() {
        final var user = User.builder()
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        Mockito.doNothing().when(addRole).addRoleToUser(any(), eq(Role.DEFAULT.name()));
        Mockito.when(getRoleByUserId.getRolesByUserId(any())).thenReturn(Set.of(Role.DEFAULT.name()));

        final var result = userPersistenceAdapter.createUser(user);

        Assertions.assertEquals(Optional.of(result), userPersistenceAdapter.searchUserById(result.id()));
    }

    @Test
    void QuandoEditarUmUsuario_AoConsultarDevoRetornarEleAtualizado() {
        final var user = User.builder()
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        Mockito.doNothing().when(addRole).addRoleToUser(any(), eq(Role.DEFAULT.name()));
        Mockito.when(getRoleByUserId.getRolesByUserId(any())).thenReturn(Set.of(Role.DEFAULT.name()));
        final var savedUser = userPersistenceAdapter.createUser(user);

        final var editedUser = User.builder()
                .id(savedUser.id())
                .name("maria")
                .document("123456784334")
                .email("maria@gmail.com")
                .login("maria")
                .password("12312312")
                .active(false)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        final var result = userPersistenceAdapter.editUser(editedUser);

        Assertions.assertEquals(editedUser, result);
    }

    @Test
    void QuandoEuPesquisarUmUsuarioQueExistaPorEmail_devoRetornarUmaListaComACorrespondencia() {
        final var searchEmail = "jhon@gmail.com";
        final var searchLogin = "birobiro";

        final var user = User.builder()
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        Mockito.doNothing().when(addRole).addRoleToUser(any(), eq(Role.DEFAULT.name()));
        Mockito.when(getRoleByUserId.getRolesByUserId(any())).thenReturn(Set.of(Role.DEFAULT.name()));
        final var savedUser = userPersistenceAdapter.createUser(user);

        final var expectedUser = User.builder()
                .id(savedUser.id())
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .build();

        Assertions.assertEquals(List.of(expectedUser), userPersistenceAdapter.searchUserByEmailOrLogin(searchEmail, searchLogin));
    }

    @Test
    void QuandoEuPesquisarUmUsuarioQueExistaPorLogin_devoRetornarUmaListaComACorrespondencia() {
        final var searchEmail = "birobiro@gmail.com";
        final var searchLogin = "jhon";

        final var user = User.builder()
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        Mockito.doNothing().when(addRole).addRoleToUser(any(), eq(Role.DEFAULT.name()));
        Mockito.when(getRoleByUserId.getRolesByUserId(any())).thenReturn(Set.of(Role.DEFAULT.name()));
        final var savedUser = userPersistenceAdapter.createUser(user);

        final var expectedUser = User.builder()
                .id(savedUser.id())
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .build();

        Assertions.assertEquals(List.of(expectedUser), userPersistenceAdapter.searchUserByEmailOrLogin(searchEmail, searchLogin));
    }

    @Test
    void QuandoEuPesquisarUmUsuarioQueNaoExistaPorLoginOuEmailPesquisa_devoRetornarUmaListaVazia() {
        final var searchEmail = "maria@gmail.com";
        final var searchLogin = "maria";

        final var user = User.builder()
                .name("john")
                .document("12345678910")
                .email("jhon@gmail.com")
                .login("jhon")
                .password("1234567")
                .active(true)
                .roles(Set.of(Role.DEFAULT.name()))
                .build();

        Mockito.doNothing().when(addRole).addRoleToUser(any(), eq(Role.DEFAULT.name()));
        Mockito.when(getRoleByUserId.getRolesByUserId(any())).thenReturn(Set.of(Role.DEFAULT.name()));

        userPersistenceAdapter.createUser(user);

        Assertions.assertEquals(List.of(), userPersistenceAdapter.searchUserByEmailOrLogin(searchEmail, searchLogin));
    }

}
