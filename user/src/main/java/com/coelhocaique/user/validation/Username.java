package com.lsouza.user.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UsernameImpl.class)
@Target({ FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {
	
	String message() default "Username already exists";
    
    Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
