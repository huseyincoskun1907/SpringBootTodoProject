package com.hc.todoproje.Repository;

import org.springframework.data.repository.CrudRepository;

import com.hc.todoproje.entity.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    List<Role> findAll();
    Role findByRolename(String name);
}