package com.example.spring.cloud.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Configuration
public class ZuulHystrixConfig implements FallbackProvider {
	
	@Override
	public String getRoute() {
		return null;
	}
	
	@Override
	public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
		System.out.println("Inside fallbackResponse");
		if (cause instanceof HystrixTimeoutException) {
			return response(HttpStatus.GATEWAY_TIMEOUT);
		} else {
			return response(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	public ClientHttpResponse response(final HttpStatus status) {
		return new ClientHttpResponse() {
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);
				return headers;
			}
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("Sorry. Something went wrong".getBytes());
			}
			public String getStatusText() throws IOException {
				return "OK";
			}
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}
			public int getRawStatusCode() throws IOException {
				return 200;
			}
			public void close() {
			}
		};
	}
}
