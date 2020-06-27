package com.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.TestPropertySource;

import com.test.model.OrderModel;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class OrderServiceImplTest {

		
	@Mock
	RedisTemplate redisTemplate;
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;
	
	@Test
	public void save() {
		OrderModel orderModel = new OrderModel();
		
		for(int i=1;i<100;i++) {
			orderModel.setCustomerOrderNumber(""+i);
			orderServiceImpl.save(orderModel);
		}
	}
}
