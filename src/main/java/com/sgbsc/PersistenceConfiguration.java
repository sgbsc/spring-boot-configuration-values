package com.sgbsc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * This class just demonstrates another way to deal with dev/test/prod configurations.
 * Here we have two files that contain our persistence information, and some made-up 
 * keys and values. We can determine which file is picked up and used, and is an alternative to
 * using @Profile or spring.profiles.active and application-XXX.properties
 * 
 * @PropertySource({ "classpath:persistence-${envTarget:dev}.properties" })
 * 
 *  Assuming that "envTarget" is present in one of the property sources already registered,
 *  e.g. system properties or environment variables, the placeholder will be resolved to the corresponding value. 
 *  If not, then "dev" will be used as a default. Expressing a default value (delimited by colon ":") is optional. 
 *  If no default is specified and a property cannot be resolved, an IllegalArgumentException will be thrown.
 * 
 * We will set the envTarget variable and thus select the instance we want.
 * The envTarget variable can be set in the OS/environment or as a parameter to the JVM command line:
 * 
 * -DenvTarget=staging
 * 
 * persistence-dev.properties
 * persistence-staging.properties
 * 
 */

@Configuration
@PropertySource({ "classpath:persistence-${envTarget}.properties" })
public class PersistenceConfiguration {
	
	@Bean
	public DBValues persistenceValues(@Value("${my.db.username}") String username, 
							 		  @Value("${my.db.password}") String password) {
		DBValues dBValues = new DBValues();
		dBValues.setUsername(username);
		dBValues.setPassword(password);
		return dBValues;
	}

}
