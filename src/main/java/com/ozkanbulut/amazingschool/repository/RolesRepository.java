package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {

    Roles getByRoleName(String roleName);




}
