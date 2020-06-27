package com.pg.rest.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class MyContainerResponseFilterImpl implements ContainerResponseFilter {

	Logger logger = Logger.getLogger(this.getClass().getName());
	@Override
    public void filter(ContainerRequestContext requestContext, 
      ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("X-Test", "Filter test");
        
        logger.info("Inside MyContainerResponseFilterImpl");
    }
}
