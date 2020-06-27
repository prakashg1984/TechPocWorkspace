package com.pg.address;
import org.springframework.data.annotation.Id;

import com.pg.util.*;
/**
 * @author pg939j
 */
public class Address {

    static final int MAX_LENGTH_DESCRIPTION = 500;
    static final int MAX_LENGTH_TITLE = 100;

    @Id
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

    public Address() {}

    private Address(Builder builder) {
    	this.streetNr= builder.streetNr;
    	this.streetNrSuffix= builder.streetNrSuffix;
    	this.streetNrLast= builder.streetNrLast;
    	this.streetNrLastSuffix= builder.streetNrLastSuffix;
    	this.streetName= builder.streetName;
    	this.streetType= builder.streetType;
    	this.streetSuffix= builder.streetSuffix;
    	this.postcode= builder.postcode;
    	this.locality= builder.locality;
    	this.city= builder.city;
    	this.stateOrProvince= builder.stateOrProvince;
    	this.country= builder.country;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }



    public void update(String streetNr,String streetNrSuffix,String streetNrLast,String streetNrLastSuffix,String streetName,String streetType,String streetSuffix,String postcode,String locality,String city,String stateOrProvince,String country) {
    	this.streetNr=streetNr;
    	this.streetNrSuffix=streetNrSuffix;
    	this.streetNrLast=streetNrLast;
    	this.streetNrLastSuffix=streetNrLastSuffix;
    	this.streetName=streetName;
    	this.streetType=streetType;
    	this.streetSuffix=streetSuffix;
    	this.postcode=postcode;
    	this.locality=locality;
    	this.city=city;
    	this.stateOrProvince=stateOrProvince;
    	this.country=country;
    }



    /**
     * We don't have to use the builder pattern here because the constructed class has only two String fields.
     * However, I use the builder pattern in this example because it makes the code a bit easier to read.
     */
    public static class Builder {

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

        private Builder() {}

        public Address build() {
        	Address build = new Address(this);
            return build;
        }

        public Builder streetNr(String streetNr) {
    		this.streetNr = streetNr;
    		return this;
    	}


        public Builder streetNrSuffix(String streetNrSuffix) {
    		this.streetNrSuffix = streetNrSuffix;
    		return this;
    	}


        public Builder streetNrLast(String streetNrLast) {
    		this.streetNrLast = streetNrLast;
    		return this;
    	}


        public Builder streetNrLastSuffix(String streetNrLastSuffix) {
    		this.streetNrLastSuffix = streetNrLastSuffix;
    		return this;
    	}


        public Builder streetName(String streetName) {
    		this.streetName = streetName;
    		return this;
    	}


        public Builder streetType(String streetType) {
    		this.streetType = streetType;
    		return this;
    	}


        public Builder streetSuffix(String streetSuffix) {
    		this.streetSuffix = streetSuffix;
    		return this;
    	}


        public Builder postcode(String postcode) {
    		this.postcode = postcode;
    		return this;
    	}


        public Builder locality(String locality) {
    		this.locality = locality;
    		return this;
    	}


        public Builder city(String city) {
    		this.city = city;
    		return this;
    	}


        public Builder stateOrProvince(String stateOrProvince) {
    		this.stateOrProvince = stateOrProvince;
    		return this;
    	}


        public Builder country(String country) {
    		this.country = country;
    		return this;
    	}
        
        
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
    

}
