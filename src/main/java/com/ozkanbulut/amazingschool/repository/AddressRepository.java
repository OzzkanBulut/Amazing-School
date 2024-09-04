package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
