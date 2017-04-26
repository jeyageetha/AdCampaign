package org.comcast.adserver.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.comcast.adserver.model.ErrorMessage;

public class ActiveAdNotFoundExceptionMapper implements ExceptionMapper<ActiveAdNotFoundException> {

	@Override
	public Response toResponse(ActiveAdNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"https://jersey.java.net/documentation/latest/index.html");
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	
	}

}
