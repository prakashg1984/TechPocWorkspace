package com.pg.address;

import io.swagger.annotations.ApiModel;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * This data transfer object contains the information of a single todo
 * entry and specifies validation rules that are used to ensure that only
 * valid information can be saved to the used database.
 * @author pg939j
 */
@ApiModel(value = "Address Request", description = "the event data in input payload")
public final class AddressBO {

    private String id;

    private String streetNr;
    private String streetNrSuffix;
    private String streetNrLast;
    private String streetNrLastSuffix;
    private String streetName;
    private String streetType;
    private String streetSuffix;
    private String postcode;
    private String locality;
    private String city;
    private String stateOrProvince;
    private String country;

    public AddressBO() {

    }

    public String getId() {
        return id;
    }

   

    public void setId(String id) {
        this.id = id;
    }

	public String getStreetNr() {
		return streetNr;
	}

	public void setStreetNr(String streetNr) {
		this.streetNr = streetNr;
	}

	public String getStreetNrSuffix() {
		return streetNrSuffix;
	}

	public void setStreetNrSuffix(String streetNrSuffix) {
		this.streetNrSuffix = streetNrSuffix;
	}

	public String getStreetNrLast() {
		return streetNrLast;
	}

	public void setStreetNrLast(String streetNrLast) {
		this.streetNrLast = streetNrLast;
	}

	public String getStreetNrLastSuffix() {
		return streetNrLastSuffix;
	}

	public void setStreetNrLastSuffix(String streetNrLastSuffix) {
		this.streetNrLastSuffix = streetNrLastSuffix;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreetSuffix() {
		return streetSuffix;
	}

	public void setStreetSuffix(String streetSuffix) {
		this.streetSuffix = streetSuffix;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddressBO [id=" + id + ", streetNr=" + streetNr
				+ ", streetNrSuffix=" + streetNrSuffix + ", streetNrLast="
				+ streetNrLast + ", streetNrLastSuffix=" + streetNrLastSuffix
				+ ", streetName=" + streetName + ", streetType=" + streetType
				+ ", streetSuffix=" + streetSuffix + ", postcode=" + postcode
				+ ", locality=" + locality + ", city=" + city
				+ ", stateOrProvince=" + stateOrProvince + ", country="
				+ country + "]";
	}

    
}
