package com.pg.samplespringboot;

import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class SpringCloudAsyncService {

	@Autowired
	RestTemplate restTemplate;
	
	@HystrixCommand
	public Future<Map> getClientResponse(int requestId) {
		return new AsyncResult<Map>() {
			@Override
			public Map invoke() {
				return restTemplate.getForObject("http://EUREKACLIENT/client/hystrix/"+requestId, Map.class);
			}
		};
		
	}
}
