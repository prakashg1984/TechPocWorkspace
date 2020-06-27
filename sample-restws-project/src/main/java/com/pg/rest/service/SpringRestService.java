package com.pg.rest.service;

import org.springframework.stereotype.Service;

import com.pg.rest.data.InputAnyJsonData;
import com.pg.rest.data.InputJaxbData;

@Service
public class SpringRestService {

	public void postSampleRequest(InputAnyJsonData inputData) {
		inputData.any().put("Response", "Success");
		inputData.any().put("ResponseCode", 200);
	}
	
	public void postSampleRequest(InputJaxbData inputJaxbData) {
		inputJaxbData.setResponseCode(200);
	}
	
}
