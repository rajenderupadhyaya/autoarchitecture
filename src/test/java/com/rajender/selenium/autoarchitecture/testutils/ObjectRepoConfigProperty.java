package com.rajender.selenium.autoarchitecture.testutils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/test/resources/propertyFiles/ObjectRepoConfig.properties" // mention the property file name
})
public interface ObjectRepoConfigProperty extends Config {
	/*
	 * We are not using Object Repository concept through reading locator elements using properties file as object repo
	 * but we are using Selenium in built Page Factory design pattern which is basically created to ease
	 * development of Page Object Model.
	 */
}
