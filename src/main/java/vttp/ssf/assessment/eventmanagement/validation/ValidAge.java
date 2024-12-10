package vttp.ssf.assessment.eventmanagement.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


// Define this annotation to be used for field validation
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)
public @interface ValidAge {
    
    String message() default "You must be at least 21 years old to participate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
