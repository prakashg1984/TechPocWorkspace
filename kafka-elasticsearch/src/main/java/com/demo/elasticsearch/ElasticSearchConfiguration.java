package com.demo.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Prakash_G01
 *
 */
@Configuration
public class ElasticSearchConfiguration extends AbstractFactoryBean{

	@Value("${elastic.search.host}")
	private String elasticSearchHost;
	
	@Value("${elastic.search.port}")
	private int elasticSearchPort;
	
    private RestHighLevelClient restHighLevelClient;

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(elasticSearchHost, elasticSearchPort, "http"),
                            new HttpHost(elasticSearchHost, 9201, "http")));
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
