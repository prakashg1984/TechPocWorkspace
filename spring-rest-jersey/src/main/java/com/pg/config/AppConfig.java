package com.pg.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

@ApplicationPath("/rest")
public class AppConfig extends ResourceConfig {

	/* public Set<Class<?>> getClasses() {
	        return new HashSet<Class<?>>(Arrays.asList(EmployeeResource.class));
	        
	       
	    }*/
	
	public AppConfig() {
		 packages("com.pg",
	        		"org.glassfish.jersey.examples.jackson"
	        		); 
		 
	        register(JacksonFeature.class);
	        register(JacksonJaxbJsonProvider.class,
					MessageBodyReader.class, MessageBodyWriter.class);

	}
	 
}
