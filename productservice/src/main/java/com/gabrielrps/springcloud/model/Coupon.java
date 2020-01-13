package com.gabrielrps.springcloud.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coupon {
	
	private Long id;
	private String code;
	private BigDecimal discount;
	private String expDate;

}
