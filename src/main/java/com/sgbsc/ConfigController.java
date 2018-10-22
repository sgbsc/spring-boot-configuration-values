package com.sgbsc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

	/*
	 * MyValues is a class annotated with @Component, and so is automatically
	 * picked up by Spring. It uses @Value to get values from application.properties.
	 */
	@Autowired
	private MyValues values;
	
	/*
	 * MyMessage is a POJO. It is created and its value is set by Spring in a @Configuration
	 * class called MyMessageConfiguration. It can also get values from application.properties
	 * using @Value, or we can set hard-coded values. In this class we provide both ways, and use 
	 * @Profile to determine what to do.
	 * We can set the Profile via spring.profiles.active in application.properties, or via
	 * a program argument in Run Configurations. e.g. --spring.profiles.active=dev
	 * The program arguments override the settings in application.properties.
	 */
	@Autowired
	private MyMessage message;
	
	/*
	 * Another way to get values is to create separate property files (-dev,-test etc), but to do this
	 * for a specific set of properties. For example, if we wanted to put all the database connection
	 * configuration values in a separate properties file, and have one for dev,test,prod, then we could.
	 * We would use a @Configuration class with a @Bean. Then we could use:
	 * @PropertySource({ "classpath:persistence-${envTarget:dev}.properties" }) to set which properties 
	 * file to use. The -DenvTarget=XXX can be set in JVM arguments.
	 * This can be done alongside the --spring.profiles.active we are using above in the program arguments.
	 */
	@Autowired
	private DBValues dbValues;
	
	
	@GetMapping("/value1")
	public String config1() {
		return "Hello, configuration 1 is: " + values.getValue1();
	}
	
	@GetMapping("/randomValue")
	public String config2() {
		return "Hello, configuration 2 is a random number: " + values.getRandomValue();
	}
	
	@GetMapping("/message")
	public String message() {
		return "Hello, the message is: " + message.getMessage();
	}
	
	@GetMapping("/persistence")
	public String persistence() {
		return "Hello, the database details are: username: " + dbValues.getUsername() + " password: " + dbValues.getPassword();
	}
	
}
