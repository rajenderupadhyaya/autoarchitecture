package com.rajender.selenium.autoarchitecture.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// See site >> https://www.toolsqa.com/selenium-webdriver/retry-failed-tests-testng/
public class RetryListeners implements IRetryAnalyzer{
	
	int count=0;
	int maxRetry=3; // Retry the failed method twice

	public boolean retry(ITestResult arg0) {
		if(count<maxRetry)
		{
			count++;
			return true;
		}
		else
			return false;
	}

}