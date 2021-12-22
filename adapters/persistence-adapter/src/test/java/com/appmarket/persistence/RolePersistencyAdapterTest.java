package com.appmarket.persistence;

import com.appmarket.exception.RoleNotExistsException;
import com.appmarket.persistence.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Set;
import java.util.UUID;

@DataJpaTest
class RolePersistencyAdapterTest {

    @Autowired
    RoleRepository repository;

    RolePersistencyAdapter rolePersistencyAdapter;

    @BeforeEach
    void setUp() {
        rolePersistencyAdapter = new RolePersistencyAdapter(repository);
    }

    @Test
    @Sql("createUser.sql")
    void QuandoEuAdicionarARole_ElaDeveSerAdicionadaComSucesso() {
        final var role = "DEFAULT";
        final var id = UUID.fromString("cfa485c7-d850-43e4-87a5-d15ef37f16c1");

        Assertions.assertDoesNotThrow(() -> {
            rolePersistencyAdapter.addRoleToUser(id, role);
        });
    }

    @Test
    @Sql("createUser.sql")
    void QuandoEuTentarAdicionarUmaRoleQueNaoExiste_DevoRetornarUmaExcecao() {
        final var role = "NOEXISTS";
        final var id = UUID.fromString("cfa485c7-d850-43e4-87a5-d15ef37f16c1");

        Assertions.assertThrows(RoleNotExistsException.class, () -> {
            rolePersistencyAdapter.addRoleToUser(id, role);
        });
    }

    @Test
    @Sql("createUser.sql")
    void QuandoEuPesquisarUmUsuarioComRoleExistente_deveRetornarAListaDeRoles() {
        final var role = "DEFAULT";
        final var id = UUID.fromString("cfa485c7-d850-43e4-87a5-d15ef37f16c1");

        rolePersistencyAdapter.addRoleToUser(id, role);

        Assertions.assertEquals(Set.of(role), rolePersistencyAdapter.getRolesByUserId(id));
    }

    @Test
    @Sql("createUser.sql")
    void QuandoEuPesquisarAsRolesDeUmUsuarioQueNaoTenhaRole_deveRetornarUmaListaVaziaDeRoles() {
        final var id = UUID.fromString("cfa485c7-d850-43e4-87a5-d15ef37f16c1");

        Assertions.assertEquals(Set.of(), rolePersistencyAdapter.getRolesByUserId(id));

    }

    @Test
    void QuandoEuPesquisarAsRolesDeUmUsuarioQueNaoExiste_deveRetornarUmaListaVaziaDeRoles() {
        final var id = UUID.randomUUID();

        Assertions.assertEquals(Set.of(), rolePersistencyAdapter.getRolesByUserId(id));

    }
}
