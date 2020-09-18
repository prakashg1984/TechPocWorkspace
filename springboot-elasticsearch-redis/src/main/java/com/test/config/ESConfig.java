package com.test.config;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
public class ESConfig {

	@Value("${elasticsearch.host}")
	private String EsHost;

	@Value("${elasticsearch.transport.port}")
	private int EsPort;

	@Value("${elasticsearch.clustername}")
	private String EsClusterName;

	// Below required if using Springdata ElasticSearch

	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.builder()
				.put("cluster.name", EsClusterName)
	            //.put("transport.type", "local")
				.build();

		// https://www.elastic.co/guide/en/elasticsearch/guide/current/_transport_client_versus_node_client.html
		/*
		 * return TransportClient. .settings(esSettings) .build() .addTransportAddress(
		 * new InetSocketTransportAddress(InetAddress.getByName(EsHost), EsPort));
		 */

		TransportClient client = new PreBuiltTransportClient(esSettings)
				.addTransportAddress(new TransportAddress(InetAddress.getByName(EsHost), EsPort));

		return client;
	}

	/*@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}*/
}
