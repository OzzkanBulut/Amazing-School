package com.ozkanbulut.amazingschool.service;

import com.ozkanbulut.amazingschool.model.Person;
import com.ozkanbulut.amazingschool.model.Roles;
import com.ozkanbulut.amazingschool.repository.PersonRepository;
import com.ozkanbulut.amazingschool.repository.RolesRepository;
import com.ozkanbulut.amazingschool.utils.constant.AmazingSchoolConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean CreateNewPerson(Person person) {

        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(AmazingSchoolConstants.STUDENT_ROLE);

        person.setRoles(role);

        person.setPwd(passwordEncoder.encode(person.getPwd()));
        personRepository.save(person);

        if (null != person && person.getPersonId() > 0) {

            isSaved = true;
        }
        return isSaved;
    }
}
