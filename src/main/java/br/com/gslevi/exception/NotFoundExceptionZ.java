package br.com.gslevi.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class NotFoundExceptionZ extends WebApplicationException {

    public NotFoundExceptionZ(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .build());
    }


}
