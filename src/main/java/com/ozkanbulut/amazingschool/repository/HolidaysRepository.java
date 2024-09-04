package com.ozkanbulut.amazingschool.repository;

import com.ozkanbulut.amazingschool.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidaysRepository extends CrudRepository<Holiday,String> {


}
