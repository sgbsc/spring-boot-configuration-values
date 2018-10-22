package com.sgbsc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/*
 * Get's the configurations from application.properties
 */

@Getter @Setter
@Component
public class MyValues {

	@Value("${my.value1}")
	private String value1;
	
	@Value("${my.value.random}")
	private String randomValue;
	
}
