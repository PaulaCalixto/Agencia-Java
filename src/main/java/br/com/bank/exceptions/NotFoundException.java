package br.com.bank.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class NotFoundException extends WebApplicationException {

    public NotFoundException(String errorMessage) {
        super(Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\": \"" + errorMessage + "\"}")
                .build()
        );
    }
}
