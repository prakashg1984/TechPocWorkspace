package com.pg.address.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.address.Address;
import com.pg.address.AddressBO;
import com.pg.address.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves Address objects
 * to MongoDB database.
 * @author pg939j
 */
@Service
final class MongoDBAddressService implements AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBAddressService.class);

    private final AddressRepository repository;

    @Autowired
    MongoDBAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressBO create(AddressBO address) {
        LOGGER.info("Creating a new Address entry with information: {}", address);

        Address persisted = Address.getBuilder()
                .streetNr(address.getStreetNr())
                .streetNrSuffix(address.getStreetNrSuffix())
                .streetNrLast(address.getStreetNrLast())
                .streetNrLastSuffix(address.getStreetNrLastSuffix())
                .streetName(address.getStreetName())
                .streetType(address.getStreetType())
                .streetSuffix(address.getStreetSuffix())
                .postcode(address.getPostcode())
                .locality(address.getLocality())
                .city(address.getCity())
                .stateOrProvince(address.getStateOrProvince())
                .country(address.getCountry())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new Address entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    public AddressBO delete(String id) {
        LOGGER.info("Deleting a Address entry with id: {}", id);

        Address deleted = findAddressById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted Address entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    public List<AddressBO> findAll() {
        LOGGER.info("Finding all Address entries.");

        List<Address> AddressEntries = repository.findAll();

        LOGGER.info("Found {} Address entries", AddressEntries.size());
        
        return convertToDTOs(AddressEntries);
    }

   /* private List<AddressBO> convertToDTOs(List<Address> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }*/

    public AddressBO findById(String id) {
        LOGGER.info("Finding Address entry with id: {}", id);

        Address found = findAddressById(id);

        LOGGER.info("Found Address entry: {}", found);

        return convertToDTO(found);
    }

    public AddressBO update(AddressBO Address) {
        LOGGER.info("Updating Address entry with information: {}", Address);

        Address updated = findAddressById(Address.getId());
        updated.update(Address.getStreetNr(), Address.getStreetNrSuffix(), Address.getStreetNrLast(), Address.getStreetNrLastSuffix(), Address.getStreetName(), 
        		Address.getStreetType(), Address.getStreetSuffix(), Address.getPostcode(), Address.getLocality(), Address.getCity(), Address.getStateOrProvince(), Address.getCountry());
        updated = repository.save(updated);

        LOGGER.info("Updated Address entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Address findAddressById(String id) {
        Optional<Address> result = repository.findOne(id);
        return result.get();

    }

    private AddressBO convertToDTO(Address model) {
        AddressBO dto = new AddressBO();

        dto.setId(model.getId());
        dto.setStreetNr(model.getStreetNr());
        dto.setStreetNrSuffix(model.getStreetNrSuffix());
        dto.setStreetNrLast(model.getStreetNrLast());
        dto.setStreetNrLastSuffix(model.getStreetNrLastSuffix());
        dto.setStreetName(model.getStreetName());
        dto.setStreetType(model.getStreetType());
        dto.setStreetSuffix(model.getStreetSuffix());
        dto.setPostcode(model.getPostcode());
        dto.setLocality(model.getLocality());
        dto.setCity(model.getCity());
        dto.setStateOrProvince(model.getStateOrProvince());
        dto.setCountry(model.getCountry());

        return dto;
    }
    
    
    private List<AddressBO> convertToDTOs(List<Address> model) {
    	
    	List<AddressBO> addressBOList = new ArrayList<AddressBO>();
    	
    	for(Address address : model){
        AddressBO dto = new AddressBO();

        dto.setId(address.getId());
        dto.setStreetNr(address.getStreetNr());
        dto.setStreetNrSuffix(address.getStreetNrSuffix());
        dto.setStreetNrLast(address.getStreetNrLast());
        dto.setStreetNrLastSuffix(address.getStreetNrLastSuffix());
        dto.setStreetName(address.getStreetName());
        dto.setStreetType(address.getStreetType());
        dto.setStreetSuffix(address.getStreetSuffix());
        dto.setPostcode(address.getPostcode());
        dto.setLocality(address.getLocality());
        dto.setCity(address.getCity());
        dto.setStateOrProvince(address.getStateOrProvince());
        dto.setCountry(address.getCountry());
        
        addressBOList.add(dto);
    	}
        return addressBOList;
    }

}
