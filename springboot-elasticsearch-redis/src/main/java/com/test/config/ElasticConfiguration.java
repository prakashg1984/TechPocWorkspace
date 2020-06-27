package com.test.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class ElasticConfiguration extends AbstractFactoryBean {

    private RestHighLevelClient restHighLevelClient;

	@Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(EsHost, EsPort, "http")));
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
