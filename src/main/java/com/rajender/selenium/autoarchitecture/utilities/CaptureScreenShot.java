package com.rajender.selenium.autoarchitecture.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class CaptureScreenShot {	  
	   
	   public static String captureScreenshot(WebDriver webdriver) throws IOException, AddressException, MessagingException {
			
			Calendar cal = new GregorianCalendar();
			int month = cal.get(Calendar.MONTH);
			int date = cal.get(Calendar.DATE);
			int year = cal.get(Calendar.YEAR);
			int hour = cal.get(Calendar.HOUR);
			int minute = cal.get(Calendar.MINUTE);
			int second = cal.get(Calendar.SECOND);
			
			File screenshotFile = ((TakesScreenshot ) webdriver).getScreenshotAs(OutputType.FILE);
			String attachmentPath = System.getProperty("user.dir")+"\\screenshot\\" + date + "_" + (month+1) + "_" + year + "_" + hour + "_" + minute + "_" + second + ".jpg";
		    FileUtils.copyFile(screenshotFile, new File(attachmentPath));
				    	    
		    return attachmentPath;
	   }
}
