package br.com.gslevi.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InternalServerErrorExceptionZ extends WebApplicationException {

    public InternalServerErrorExceptionZ(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(message)
                .build());
    }
}
