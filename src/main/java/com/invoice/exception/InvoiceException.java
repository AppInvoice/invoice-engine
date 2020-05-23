package com.invoice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Main service exception.
 * As extends ResponseStatusException, will manage the HttpStatus retrieved.
 */
public class InvoiceException extends ResponseStatusException {

    public InvoiceException(final HttpStatus status, final String reason) {
        super(status, reason);
    }
}
