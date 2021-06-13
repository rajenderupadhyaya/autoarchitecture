package com.rajender.selenium.autoarchitecture.testutils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/test/resources/propertyFiles/EnvDevConfig.properties" // mention the property file name
})
public interface EnvDevConfigProperty extends Config {
	
	@Key("testsiteurl")
	String getTestsiteurl();
	
	@Key("browser")
	String getBrowser();
	
	@Key("env")
	String getEnv();	
}
