package com.eazybytes.eazyschool.validation;

import com.eazybytes.eazyschool.annotation.Passwordvalidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<Passwordvalidator,String> {

    List<String> weakPasswords;

    @Override
    public void initialize(Passwordvalidator constraintAnnotation) {
        weakPasswords = List.of("12345","password","qwerty");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {
        return passwordField!=null && (!weakPasswords.contains(passwordField));
    }
}
