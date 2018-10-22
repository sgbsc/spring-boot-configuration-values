package com.sgbsc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/*
 * @Configuration indicates that a class declares one or more @Bean methods and may be processed by the 
 * Spring container to generate bean definitions and service requests for those beans at 
 * runtime.
 * 
 * Here we are exploring using profiles in Spring. We declare the beans with the @Profile of "prod" and "dev"
 * and give them different names and functionality. One will be chosen depending on the environment in
 * which the beans run. We can set the Profile via spring.profiles.active in application.properties, or via
 * a program argument in Run Configurations. e.g. --spring.profiles.active=dev
 * The program arguments override the settings in application.properties.
 */

@Configuration
public class MyMessageConfiguration {

	/*
	 * my.configuration1 will be read from application.properties file and injected into
	 * message() method below, then it will be used to set the message value on the MyMessage object
	 * and returned as a bean.
	 */
	@Bean
	@Profile("prod")
	public MyMessage message(@Value("${my.value1}") String messageValue) {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessage(messageValue);
		return myMessage;
	}
	
	@Bean
	@Profile("dev")
	public MyMessage messageDev() {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessage("This is the dev method");
		return myMessage;
	}
	
	@Bean
	@Profile("test")
	public MyMessage messageTest() {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessage("This is the test method");
		return myMessage;
	}
}
