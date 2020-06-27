package com.pg.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class ElasticSearchConfiguration extends AbstractFactoryBean{

    private RestHighLevelClient restHighLevelClient;

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http"),
                            new HttpHost("localhost", 9201, "http")));
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return restHighLevelClient;
    }

	@Override
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return RestHighLevelClient.class;
	}
}
