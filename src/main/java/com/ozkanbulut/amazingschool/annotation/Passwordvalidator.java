package com.ozkanbulut.amazingschool.annotation;

import com.ozkanbulut.amazingschool.validation.PasswordStrengthValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Passwordvalidator {

    String message() default "Please choose a strong password";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
