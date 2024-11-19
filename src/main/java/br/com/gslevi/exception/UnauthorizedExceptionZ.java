package br.com.gslevi.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UnauthorizedExceptionZ extends WebApplicationException {

    public UnauthorizedExceptionZ(String message) {
        super(Response.status(Response.Status.UNAUTHORIZED)
                .entity(message)
                .build());
    }


}
