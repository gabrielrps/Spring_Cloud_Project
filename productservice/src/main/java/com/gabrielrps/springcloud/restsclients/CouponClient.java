package com.gabrielrps.springcloud.restsclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gabrielrps.springcloud.model.Coupon;

//Same name on application.properties in Coupon project and obviously the same on Eureka Service
@FeignClient("zuul-api-gateway")
//Zuul uses Ribbon internaly
//@RibbonClient("COUPON-SERVICE")
public interface CouponClient {

	@GetMapping(value = "/coupon-service/couponapi/coupons/{code}")
	public Coupon getCoupon(@PathVariable("code") String code);
	
}
