package com.test.springexpr;

import com.test.springexpr.ServiceImpl;
import junit.framework.TestCase;

public class ServiceImplTests extends TestCase {

	private ServiceImpl service = new ServiceImpl();
	
	public void testReadOnce() throws Exception {
		assertEquals("Hello world!", service.getMessage());
	}

}
