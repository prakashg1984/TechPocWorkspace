package com.pg.address.repository;

import org.springframework.data.repository.Repository;

import com.pg.address.Address;
import com.pg.address.service.*;

import java.util.List;
import java.util.Optional;

/**
 * This repository provides CRUD operations for Address Objects
 * 
 * @author pg939j
 */
public interface AddressRepository extends Repository<Address, String> {

    /**
     * Deletes a Address entry from the database.
     * @param deleted   The deleted Address entry.
     */
    void delete(Address deleted);

    /**
     * Finds all Address entries from the database.
     * @return  The information of all Address entries that are found from the database.
     */
    List<Address> findAll();

    /**
     * Finds the information of a single Address entry.
     * @param id    The id of the requested todo entry.
     * @return      The information of the found todo entry. If no todo entry
     *              is found, this method returns an empty {@link java.util.Optional} object.
     */
    Optional<Address> findOne(String id);

    /**
     * Saves a new Address entry to the database.
     * @param saved The information of the saved Address entry.
     * @return      The information of the saved Address entry.
     */
    Address save(Address saved);
}
