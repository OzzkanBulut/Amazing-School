package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import com.eazybytes.eazyschool.utils.constant.EazySchoolConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean CreateNewPerson(Person person){

        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);

        person.setRoles(role);
        personRepository.save(person);

        if(null!=person && person.getPersonId()>0){

            isSaved = true;
        }
        return isSaved;
    }
}
