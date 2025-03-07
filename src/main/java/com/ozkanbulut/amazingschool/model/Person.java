package com.ozkanbulut.amazingschool.model;

import com.ozkanbulut.amazingschool.annotation.FieldsValueMatch;
import com.ozkanbulut.amazingschool.annotation.Passwordvalidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Emails do not match!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotBlank(message = "Name must not be blank!")
    @Size(min = 3,message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank!")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be at least 10 digits!")
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank!")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Confirm Email must not be blank!")
    @Email(message = "Please provide a valid confirm email address")
    @Transient // Do not do anything related to jpa or db with this field!
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank!")
    @Size(min = 5,message = "Password must be at least 5 characters long")
    @Passwordvalidator
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank!")
    @Size(min = 5,message = "Confirm Password must be at least 5 characters long")
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,targetEntity = Roles.class)
    @JoinColumn(name = "role_id",referencedColumnName ="roleId", nullable = false )
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,targetEntity = Address.class)
    @JoinColumn(name = "address_id",referencedColumnName ="addressId",nullable = true )
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER,optional = true)
    @JoinColumn(name = "class_id",referencedColumnName = "classId",nullable = true)
    private AmazingClass amazingClass;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "person_courses",
            joinColumns ={
            @JoinColumn(name ="person_id",referencedColumnName = "personId")
            },
            inverseJoinColumns ={
            @JoinColumn(name = "course_id",referencedColumnName = "courseId")
            } )
    private Set<Courses> courses = new HashSet<>();


}
