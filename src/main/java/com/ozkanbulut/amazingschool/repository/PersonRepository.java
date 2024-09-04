package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByEmail(String email);


    @Query("select p from Person p where p.roles.roleId = :roleId ")
    List<Person> findAllStudents(int roleId);



}
