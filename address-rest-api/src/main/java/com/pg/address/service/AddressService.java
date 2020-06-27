package com.pg.address.service;

import java.util.List;

import com.pg.address.AddressBO;

/**
 * This interface declares the methods that provides CRUD operations for
 * Address objects.
 * @author pg939j
 */
public interface AddressService {

    /**
     * Creates a new Address entry.
     * @param Address  The information of the created Address entry.
     * @return      The information of the created Address entry.
     */
    AddressBO create(AddressBO Address);

    /**
     * Deletes a Address entry.
     * @param id    The id of the deleted Address entry.
     * @return      THe information of the deleted Address entry.
     */
    AddressBO delete(String id);

    /**
     * Finds all Address entries.
     * @return      The information of all Address entries.
     */
    List<AddressBO> findAll();

    /**
     * Finds a single Address entry.
     * @param id    The id of the requested Address entry.
     * @return      The information of the requested Address entry.
     */
    AddressBO findById(String id);

    /**
     * Updates the information of a Address entry.
     * @param Address  The information of the updated Address entry.
     * @return      The information of the updated Address entry.
     */
    AddressBO update(AddressBO Address);
}
