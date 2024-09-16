package com.hajer.Headphone.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;


@RequiredArgsConstructor
public class ObjectValidationsException extends RuntimeException{

    @Getter
    private final Set<String> violations;

    @Getter
    private final String violationSource;
}
