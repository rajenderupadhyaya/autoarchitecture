package com.rajender.selenium.autoarchitecture.testutils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ 
	"file:src/test/resources/propertyFiles/GeneralConfig.properties" // mention the property file name
})
public interface GeneralConfigProperty extends Config {
	
	@Key("server")
	String getServer();
	
	@Key("from")
	String getFrom();
	
	@Key("password")
	String getPassword();
	
	@Key("to")
	String[] getTo();
	
	@Key("subject")
	String getSubject();
	
	@Key("subjectScreenShot")
	String getSubjectScreenShot();
	
	@Key("messageBody")
	String getMessageBody();
	
	@Key("messageBodyScreenShot")
	String getMessageBodyScreenShot();
	
	@Key("messageBodyTestNGReport")
	String getMessageBodyTestNGReport();
	
	/*
	 * @Key("attachmentPath") String getAttachmentPath();
	 */
	
	@Key("attachmentName")
	String getAttachmentName();
	
	@Key("attachmentNameNG")
	String getAttachmentNameNG();
	
	@Key("reportPath")
	String getReportPath(); 
	
	/*
	 * @Key("ngReportPath") String getNgReportPath();
	 */
	
	@Key("testDataSheetName")
	String getTestDataSheetName();
	 
	@Key("attachmentNameScreenShot")
	String getAttachmentNameScreenShot();
	
	@Key("testReportPath")
	String getTestReportPath();
	
	@Key("testReportName")
	String getTestReportName();
}
