package com.example.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.Calendar;

public class BirthdayValidator implements ConstraintValidator<ValidBirthday, Date> {

    @Override
    public boolean isValid(Date birthday, ConstraintValidatorContext context) {
        if (birthday == null) {
            return true; // если null, доверяем другой аннотации (например, @NotNull)
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        int year = calendar.get(Calendar.YEAR);

        return year >= 2000 && year <= 3000;
    }
}

