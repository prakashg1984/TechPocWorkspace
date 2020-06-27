package com.test.repo;

import java.util.List;
import java.util.Map;

public interface DBRepository<T> {

	String save(T t);
	
	List<Object> findByField(String data,String field,Class obj);
	
	List<Object> findBySearch(Map<String,Object> data,Class obj);
}
