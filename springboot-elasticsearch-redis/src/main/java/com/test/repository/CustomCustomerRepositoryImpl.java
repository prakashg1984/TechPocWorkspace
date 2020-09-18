package com.test.repository;

import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.test.model.CustomerModel;

@Repository
public class CustomCustomerRepositoryImpl implements CustomCustomerRepository  {
	private static Logger logger = LoggerFactory.getLogger(CustomCustomerRepositoryImpl.class);

	@Autowired
    private ElasticsearchTemplate esTemplate;
	
	@Override
	public List<CustomerModel> searchCustomer(Map<String,String> searchField) {
		
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		for (Map.Entry<String, String> entry : searchField.entrySet()){
		    boolQuery.must(QueryBuilders.matchQuery("customerData."+entry.getKey(), entry.getValue()));
		}

        SearchQuery getAllQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build();
        
        logger.info("searchCustomer getAllQuery : "+getAllQuery.getQuery().toString());
        return esTemplate.queryForList(getAllQuery, CustomerModel.class);

	}


}
