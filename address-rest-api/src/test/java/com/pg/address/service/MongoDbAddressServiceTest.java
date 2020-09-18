package com.pg.address.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pg.address.AddressBO;
import com.pg.address.service.MongoDBAddressService;

public class MongoDbAddressServiceTest extends AppConfigTest {

	@Autowired
	MongoDBAddressService service;

	@Test
	public void testcreate() throws Exception {
		AddressBO addressBO = new AddressBO();
		service.create(addressBO);
	}
}
