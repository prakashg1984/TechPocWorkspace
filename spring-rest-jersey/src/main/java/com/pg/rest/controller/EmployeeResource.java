package com.pg.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;

import com.pg.model.Employee;
import com.pg.rest.service.EmployeeService;

@Path("/employees")
public class EmployeeResource {

	@Autowired
	EmployeeService employeeService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getAllEmployees() {
        return Response.ok(employeeService.getAllEmployees()).build();
    }
	
	@GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Employee getEmployee(@PathParam("id") int id) {
        return employeeService.getEmployee(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateEmployee(Employee employee, @PathParam("id") int id) {
    	employeeService.updateEmployee(employee, id);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }
    
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addEmployee(Employee employee, @Context UriInfo uriInfo) {
    	employeeService.addEmployee(new Employee(employee.getId(), employee.getFirstName()));
        return Response.status(Response.Status.CREATED.getStatusCode()).header("Location", String.format("%s/%s", uriInfo.getAbsolutePath().toString(), employee.getId())).build();
    }
}
