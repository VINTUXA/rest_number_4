package com.example.rest.rest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderFilterValidValidator.class) // будет отвечать за валидацию
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderFilterValid {

    String message() default "Pagindation should be declared. If you declare minCost or MaxCost, then both of them should be specified!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
