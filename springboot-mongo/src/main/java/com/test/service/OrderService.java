package com.test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.bo.Accounts;
import com.test.bo.Promotions;
import com.test.repo.DBRepositoryImpl;

@Service
public class OrderService  {

	DBRepositoryImpl dbRepositoryImpl;	

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	public OrderService(DBRepositoryImpl dbRepositoryImpl) {
		this.dbRepositoryImpl = dbRepositoryImpl;
	}
	
	/*
	 * Annotate with Transactional if we need to enable atomicity
	 */
	@Transactional
	public void saveOrder(Map<String,Object> inputObject) {	
		Accounts accounts = new Accounts();
		Promotions promotions = new Promotions();
		accounts.setOrderNumber(inputObject.get("orderNumber").toString());
		promotions.setOrderNumber(inputObject.get("orderNumber").toString());
		
		accounts.any().putAll((Map)inputObject.get("accounts"));
		promotions.any().putAll((Map)inputObject.get("promotions"));
		
		dbRepositoryImpl.save(promotions);
		
		//Enabled to validate Transactions
		/*try {
			System.out.println("Going to Sleep..");
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		dbRepositoryImpl.save(accounts);
	}
	
	public Map<String,Object> getOrder(String orderNumber) {	
		Map<String,Object> orderObject = new HashMap<String,Object>();
		List<Accounts> accountsList = dbRepositoryImpl.findByField(orderNumber, "orderNumber",Accounts.class);
		
		List<Promotions> promotionList = dbRepositoryImpl.findByField(orderNumber, "orderNumber",Promotions.class);
		
		orderObject.put("orderNumber", orderNumber);
		orderObject.put("accounts", accountsList);
		orderObject.put("promotions", promotionList);
		
		return orderObject;
	}
	
	
	public Map<String,Object> searchOrder(Map<String,Object> searchStringMap) {	
		Map<String,Object> orderObject = new HashMap<String,Object>();
		List<Accounts> accountsList = dbRepositoryImpl.findBySearch(searchStringMap,Accounts.class);
		
		List<Promotions> promotionList = dbRepositoryImpl.findBySearch(searchStringMap,Promotions.class);
		
		orderObject.put("accounts", accountsList);
		orderObject.put("promotions", promotionList);
		
		return orderObject;
	}
	
}
