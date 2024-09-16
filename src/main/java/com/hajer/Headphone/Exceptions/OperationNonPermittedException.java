package com.hajer.Headphone.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public class OperationNonPermittedException extends RuntimeException{

    private final String errMsg;

    private final String operationId;

    private final String source;

    private final String dependency;
}
