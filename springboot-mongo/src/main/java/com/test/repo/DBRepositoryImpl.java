package com.test.repo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DBRepositoryImpl<T> implements DBRepository<T>  {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public String save(T t) {
		mongoTemplate.save(t);
		return "Succes";
	}

	@Override
	public List<Object> findByField(String data,String field,Class obj) {
		Query query = new Query();
		query.addCriteria(Criteria.where(field).is(data));
		List response = mongoTemplate.find(query, obj);
		return response;
	}

	@Override
	public List<Object> findBySearch(Map<String, Object> data, Class obj) {
		Query query = new Query();
		
		for(Map.Entry<String, Object> mapElement : data.entrySet()) {
			String key = (String) mapElement.getKey();
			String value = (String) mapElement.getValue();
			
			query.addCriteria(Criteria.where(key).is(value));
		}
		System.out.println("Final Query "+query);
		List response = mongoTemplate.find(query, obj);
		return response;
	}

}
