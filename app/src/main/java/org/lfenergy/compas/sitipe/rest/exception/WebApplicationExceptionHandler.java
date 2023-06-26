package org.lfenergy.compas.sitipe.rest.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class WebApplicationExceptionHandler implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        final Map<String, String> response = new HashMap<>();
        response.put("message", e.getMessage());
        return Response.status(e.getResponse().getStatus())
                .entity(response)
                .build();
    }
}

