package org.elgordogato.supercalculator.exceptions;


public class BadRequestException extends RuntimeException {
    public BadRequestException(String params, String operation) {
        super(String.format("Unsupported params %s for operation %s", params, operation));
    }
}