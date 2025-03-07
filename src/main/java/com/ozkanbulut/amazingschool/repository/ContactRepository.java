package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    // @Query("SELECT c FROM Contact c WHERE c.status = :status ") --> Same thing with JPQL
    // @Query(value = "SELECT * FROM contact_msg c WHERE c.status = :status ",nativeQuery = true) -- With Native Sql Query
    Page<Contact> findByStatus(String status, Pageable pageable); // Pagination


    @Transactional
    @Modifying
    @Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
    int updateStatusById(String status, int id);



}
