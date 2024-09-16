package com.hajer.Headphone.Validators;

import com.hajer.Headphone.Exceptions.ObjectValidationsException;
import jakarta.validation.*;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Objectsvalidator<T> {

    private  final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private final Validator validator=factory.getValidator();
    public  void validate (T objectToValidate){

        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

        if(!violations.isEmpty()){
            Set<String> ErrorMessages=violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());

            //todo raise an exception
            throw new ObjectValidationsException(ErrorMessages,objectToValidate.getClass().getName());
        }

    }
}
