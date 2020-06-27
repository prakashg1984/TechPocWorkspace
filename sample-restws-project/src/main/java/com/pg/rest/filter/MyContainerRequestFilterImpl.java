package com.pg.rest.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class MyContainerRequestFilterImpl implements ContainerRequestFilter {

	Logger logger = Logger.getLogger(this.getClass().getName());
	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Override
    public void filter(ContainerRequestContext requestContext) 
                    throws IOException { 
		
		String authCredentials = requestContext
				.getHeaderString(AUTHENTICATION_HEADER);
		
		logger.info("requestContext "+requestContext.getHeaders());
		logger.info("authCredentials "+authCredentials);


	}
}
