package com.rajender.selenium.autoarchitecture.testutils;

//import java.net.InetAddress;
//import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extentmanager {
	
	private static final Logger log = LogManager.getLogger(Extentmanager.class);
	
	// There will be only a single object of this class
	// in whole project.
	private static ExtentReports extent;
	//private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	//private static String filePath = "./extentreport.html";
	//private static InetAddress ip;

	public static ExtentReports GetExtent(String filePath) {
		if (extent != null) {
			return extent;
		} else {
			extent = new ExtentReports();
			extent.attachReporter(getHtmlReporter(filePath));
			/*
			 * This we will use to get Host name when application deployed in server
			try {
				ip = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}
			extent.setSystemInfo("Host Name", System.getProperty(ip.getHostName()));
			*/
			extent.setSystemInfo("Host OS", System.getProperty("os.name"));
			extent.setAnalysisStrategy(AnalysisStrategy.CLASS);
			log.info("Extent Report has been created");
			return extent;
		}
	}

	public static ExtentHtmlReporter getHtmlReporter(String filePath) {

			htmlReporter = new ExtentHtmlReporter(filePath);
			/*htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle(" Automation Report");
			htmlReporter.config().setReportName("Automation");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);*/
			
		//	htmlReporter.setAppendExisting(false); 
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/test/resources/configXMLFiles/ReportsConfig.xml");
	
			return htmlReporter;
	}

}
