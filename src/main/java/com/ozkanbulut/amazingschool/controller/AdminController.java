package com.ozkanbulut.amazingschool.controller;

import com.ozkanbulut.amazingschool.model.AmazingClass;
import com.ozkanbulut.amazingschool.model.Courses;
import com.ozkanbulut.amazingschool.model.Person;
import com.ozkanbulut.amazingschool.repository.CoursesRepository;
import com.ozkanbulut.amazingschool.repository.AmazingClassRepository;
import com.ozkanbulut.amazingschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    AmazingClassRepository amazingClassRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<AmazingClass> amazingClasses = amazingClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("amazingClasses", amazingClasses);
        modelAndView.addObject("amazingClass", new AmazingClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("amazingClass") AmazingClass amazingClass) {
        amazingClassRepository.save(amazingClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<AmazingClass> eazyClass = amazingClassRepository.findById(id);
        for (Person person : eazyClass.get().getPersons()) {
            person.setAmazingClass(null);
            personRepository.save(person);
        }
        amazingClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<AmazingClass> eazyClass = amazingClassRepository.findById(classId);
        modelAndView.addObject("amazingClass", eazyClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("amazingClass", eazyClass.get());
        if (error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        AmazingClass amazingClass = (AmazingClass) session.getAttribute("amazingClass");
        Person personEntity = personRepository.findByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)) {
            modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + amazingClass.getClassId()
                    + "&error=true");
            return modelAndView;
        }
        personEntity.setAmazingClass(amazingClass);
        personRepository.save(personEntity);
        amazingClass.getPersons().add(personEntity);
        amazingClassRepository.save(amazingClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId=" + amazingClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        AmazingClass amazingClass = (AmazingClass) session.getAttribute("amazingClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setAmazingClass(null);
        amazingClass.getPersons().remove(person.get());
        AmazingClass amazingClassSaved = amazingClassRepository.save(amazingClass);
        session.setAttribute("amazingClass", amazingClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId=" + amazingClass.getClassId());
        return modelAndView;
    }
    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
//        List<Courses> courses = coursesRepository.findByOrderByNameDesc(); --> Static sorting
        List<Courses> courses = coursesRepository.findAll(Sort.by("name").descending()); // Dynamic sorting
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Courses());
        return modelAndView;
    }
    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model,@ModelAttribute("course") Courses course){
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model,@RequestParam int id,HttpSession session,
                                     @RequestParam(required = false) String error){
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses",courses.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("courses",courses.get());
        String errorMessage;
        if(error != null){
            errorMessage = "Invalid Email entered!";
            modelAndView.addObject("errorMessage",errorMessage);
        }
        return modelAndView;

    }

    @GetMapping("/viewAllStudents")
    public ModelAndView viewAllStudents(Model model,@RequestParam(required = false) String error,
                                        HttpSession session){
        ModelAndView modelAndView = new ModelAndView("all_students.html");
        List<Person> students = personRepository.findAllStudents(2);

        model.addAttribute("students",students);
        String errorMessage;
        if (error != null) {
            errorMessage = "No students!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;


    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView modelAndView(Model model,@RequestParam int personId,
                                     HttpSession session){

        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses);
        courses.getPersons().remove(person);

        personRepository.save(person.get());
        session.setAttribute("courses",courses);
        return new ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model,@ModelAttribute("person") Person person,
                                           HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        Courses courses = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.findByEmail(person.getEmail());

        if(personEntity== null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId()+"&error=true");
            return modelAndView;
        }

        personEntity.getCourses().add(courses);
        courses.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses",courses);
        modelAndView.setViewName("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;

    }
}