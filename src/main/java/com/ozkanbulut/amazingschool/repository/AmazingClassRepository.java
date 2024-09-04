package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.AmazingClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazingClassRepository extends JpaRepository<AmazingClass,Integer> {


}
