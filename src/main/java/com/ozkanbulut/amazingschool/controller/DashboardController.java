package com.ozkanbulut.amazingschool.controller;

import com.ozkanbulut.amazingschool.model.Person;
import com.ozkanbulut.amazingschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;



    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession session) {

        Person person = personRepository.findByEmail(authentication.getName());
        log.info(authentication.getAuthorities().toString());

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if(null != person.getAmazingClass() && null != person.getAmazingClass().getName()){
            model.addAttribute("enrolledClass",person.getAmazingClass().getName());
        }

        session.setAttribute("loggedInPerson",person);
        return "dashboard.html";
    }

}
