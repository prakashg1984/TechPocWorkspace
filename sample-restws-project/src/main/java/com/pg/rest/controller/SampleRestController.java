package com.pg.rest.controller;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pg.rest.data.InputAnyJsonData;
import com.pg.rest.data.InputJaxbData;
import com.pg.rest.service.SpringRestService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Path("/sample")
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
@Tag(name = "Example")
@Controller
public class SampleRestController {

	Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	SpringRestService springRestService;
	
	@Autowired
	public SampleRestController(SpringRestService springRestService) {
		this.springRestService = springRestService;
	}
	
	@GET
	@Path("/get")
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = "application/json"
	        ),
	        description = "Sample Get URL"
	    )
	public Response getMethod() {
 
		String output = "Jersey say Generic Halo : ";
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/{param}")
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = "application/json"
	        ),
	        description = "Sample Get URL using Path Param"
	    )
	public Response getPathParam(@PathParam("param") String msg) {
 
		String output = "Jersey say Halo Path Param : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = "application/json"
	        ),
	        description = "Sample Get URL using Query Param"
	    )
	public Response getQueryParam(@QueryParam("param") String msg) {
 
		String output = "Jersey say Halo Query Param : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@POST
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = "application/json"
	        ),
	        description = "Sample POST URL using Generic Json"
	    )
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response postSampleRequest(InputAnyJsonData inputData,@Context HttpHeaders headers) {
 
		logger.info("Jersey say Halo PostSample : " + inputData);
		
		springRestService.postSampleRequest(inputData);
 
		return Response.status(200).entity(inputData).build();
 
	}
	
	@POST
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = "application/json"
	        ),
	        description = "Sample POST URL using Jaxb"
	    )
	@Path("/post")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
	public Response postSampleInput(InputJaxbData inputJaxbData,@Context HttpHeaders headers) {
 
		logger.info("Jersey say Halo PostSample : " + inputJaxbData);
		springRestService.postSampleRequest(inputJaxbData);
		return Response.status(200).entity(inputJaxbData).build();
 
	}
}
