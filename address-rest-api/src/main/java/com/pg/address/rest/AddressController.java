package com.pg.address.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pg.address.AddressBO;
import com.pg.address.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

import java.util.List;

/**
 * This controller provides the public API that is used to manage the information
 * of Address entries.
 * @author pg939j
 */
@Api(value = "/api/Address" , description = "Address Operations")
@RestController
@RequestMapping("/api/Address")
final class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressController.class);

    private final AddressService service;

    @Autowired
    AddressController(AddressService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Address", notes = "Create a call log record in mongo collection", response = AddressBO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Service not available"),
			@ApiResponse(code = 500, message = "Unexpected Runtime error") })
    AddressBO create(@ApiParam(value = "Address request object", required = true) @RequestBody @Valid AddressBO AddressEntry) {
        LOGGER.info("Creating a new Address entry with information: {}", AddressEntry);

        AddressBO created = service.create(AddressEntry);
        LOGGER.info("Created a new Address entry with information: {}", created);

        return created;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Address", notes = "Delete Address record in mongo collection", response = AddressBO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Service not available"),
			@ApiResponse(code = 500, message = "Unexpected Runtime error") })
    AddressBO delete(@PathVariable("id") String id) {
        LOGGER.info("Deleting Address entry with id: {}", id);

        AddressBO deleted = service.delete(id);
        LOGGER.info("Deleted Address entry with information: {}", deleted);

        return deleted;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "Find All Address", notes = "Fina All record in mongo collection", response = AddressBO.class)
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Service not available"),
			@ApiResponse(code = 500, message = "Unexpected Runtime error") })
    List<AddressBO> findAll() {
        LOGGER.info("Finding all Address entries");

        List<AddressBO> AddressEntries = service.findAll();
        LOGGER.info("Found {} Address entries", AddressEntries.size());

        return AddressEntries;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    AddressBO findById(@PathVariable("id") String id) {
        LOGGER.info("Finding Address entry with id: {}", id);

        AddressBO AddressEntry = service.findById(id);
        LOGGER.info("Found Address entry with information: {}", AddressEntry);

        return AddressEntry;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    AddressBO update(@RequestBody @Valid AddressBO AddressEntry) {
        LOGGER.info("Updating Address entry with information: {}", AddressEntry);

        AddressBO updated = service.update(AddressEntry);
        LOGGER.info("Updated Address entry with information: {}", updated);

        return updated;
    }

}
