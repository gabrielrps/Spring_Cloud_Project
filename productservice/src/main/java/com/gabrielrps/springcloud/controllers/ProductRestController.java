package com.gabrielrps.springcloud.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gabrielrps.springcloud.model.Coupon;
import com.gabrielrps.springcloud.model.Product;
import com.gabrielrps.springcloud.repos.ProductRepository;
import com.gabrielrps.springcloud.restsclients.CouponClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CouponClient couponClient;
	
//  Example using RestTemplate	
//	@Autowired
//	@Lazy
//	private EurekaClient discoveryClient;
//	
//	public String serviceUrl() {
//		InstanceInfo instance = discoveryClient.getNextServerFromEureka("COUPON-SERVICE", false);
//		return instance.getHomePageUrl();
//	}
//	
//	@Bean
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//	
//	@Autowired
//	RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "sendResponseError")
	@PostMapping(value = "/products")
	public Product create(@RequestBody Product product) {
//  	Example using RestTemplate			
//		this.restTemplate.getForObject(URI.create(serviceUrl() + "couponapi/coupons/SUPERSALE"), String.class);
//		
//		ResponseEntity<Coupon> rateResponse =
//		        restTemplate.exchange((URI.create(serviceUrl() + "couponapi/coupons/SUPERSALE")),
//		                    HttpMethod.GET, null, new ParameterizedTypeReference<Coupon>() {
//		            });
//		Coupon rates = rateResponse.getBody();
		
		//Using Feign
		Coupon coupon = couponClient.getCoupon(product.getCouponCode());
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return productRepository.save(product);
	}
	
	public Product sendResponseError(Product product) {
		return product;
	}
	
}
