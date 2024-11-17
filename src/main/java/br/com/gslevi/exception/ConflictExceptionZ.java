package br.com.gslevi.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ConflictExceptionZ extends WebApplicationException {

    public ConflictExceptionZ(String message) {
        super(Response.status(Response.Status.CONFLICT)
                .entity(message)
                .build());
    }
}
