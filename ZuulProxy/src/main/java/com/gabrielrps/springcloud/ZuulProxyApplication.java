package com.gabrielrps.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.gabrielrps.springcloud.filter.CustomFilter;

@SpringBootApplication
@EnableZuulProxy
//Poderia usar o @EnableDiscoveryClient também, tanto faz nesse caso
@EnableDiscoveryClient
public class ZuulProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxyApplication.class, args);
	}

	@Bean
	public CustomFilter getFilter() {
		return new CustomFilter();
	}
	
}
