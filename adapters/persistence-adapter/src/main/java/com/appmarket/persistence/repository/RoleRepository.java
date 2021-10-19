package com.appmarket.persistence.repository;

import com.appmarket.persistence.model.RoleEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(final String name);

    List<RoleEntity> findAllByIdIn(final List<Long> ids);

    @Modifying
    @Query(value = "insert into roles_users(userid, roleid, creation_date) values(?1, ?2, current_timestamp) ", nativeQuery = true)
    int saveRelation(final UUID userid, final Long roleid);

    @Query(value = "select roleid from roles_users where userid = ?1", nativeQuery = true)
    List<Long> findAllRolesIdsByUserId(final UUID userId);

}
