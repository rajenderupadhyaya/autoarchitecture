package com.rajender.selenium.autoarchitecture.base;

import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.aeonbits.owner.ConfigFactory;

// Using Log4j2
//import org.apache.logging.log4j.Logger;

//Not Using Log4j
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.rajender.selenium.autoarchitecture.mail.MonitoringMail;
import com.rajender.selenium.autoarchitecture.utilities.CaptureScreenShot;
import com.rajender.selenium.autoarchitecture.utilities.ExcelReader;
import com.rajender.selenium.autoarchitecture.testutils.EnvDevConfigProperty;
import com.rajender.selenium.autoarchitecture.testutils.Extentmanager;
import com.rajender.selenium.autoarchitecture.testutils.GeneralConfigProperty;
import com.rajender.selenium.autoarchitecture.testutils.ObjectRepoConfigProperty;
import com.rajender.selenium.autoarchitecture.testutils.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {	
	
	/*
	 * 
	 * Logs
	 * Excel
	 * Properties - OR, Config
	 * Java Mail
	 * WebDriver - browser
	 * ConfigProperty
	 */
	protected static GeneralConfigProperty generalConfigProperty;
	protected static EnvDevConfigProperty envDevConfigProperty;
	protected static ObjectRepoConfigProperty objectRepoConfigProperty;
	public static WebDriver driver;
	//public static Logger log = Logger.getLogger("devpinoyLogger");	
	public static MonitoringMail mail = new MonitoringMail();
	
    /* 
     * Best way to arrange test data and reading it, as well as using in Java is easy as we avoid number of parameters in method when number
	   of parameters are growing.	  
	 */
	public static ExcelReader excel1 = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/testData/TestData1.xlsx");
	//Initializing Logger File
	
	public static FileInputStream fis;
	public static WebDriverWait webDriverWait;
			
	public static ChromeOptions chromeOptions;
	public static FirefoxOptions firefoxOptions;
	public static EdgeOptions edgeOptions;	
	
	//Setting up a Extent report
	public static ExtentReports extentReport;
	public static ThreadLocal<ExtentTest> classLevelLog = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testLevelLog = new ThreadLocal<ExtentTest>();
	public static ExtentTest test=null;
	
	@BeforeSuite
	public void setUp(){			
		
		if(driver==null){			
			
			// Logger Initialization
			//PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/test/resources/loggerFile/loggerConfigFile.properties");
			envDevConfigProperty =  ConfigFactory.create(EnvDevConfigProperty.class);			
			generalConfigProperty = ConfigFactory.create(GeneralConfigProperty.class);
		//	objectRepoConfigProperty = ConfigFactory.create(ObjectRepoConfigProperty.class);
			
			if(envDevConfigProperty.getBrowser().equals("firefox")){
				firefoxOptions = new FirefoxOptions();				
				WebDriverManager.firefoxdriver().driverVersion("0.26.0").setup();
				driver = new FirefoxDriver(firefoxOptions);
		
				//log.debug("Firefox Launched");
			}else if(envDevConfigProperty.getBrowser().equals("chrome")){				
				chromeOptions = new ChromeOptions();				
				//WebDriverManager.chromedriver().driverVersion("89.0.4389.23").setup();
				WebDriverManager.chromedriver().driverVersion("91.0.4472.106").setup();
				driver = new ChromeDriver(chromeOptions);
				
				//log.debug("Chrome Launched");
				
			}else if(envDevConfigProperty.getBrowser().equals("edge")){
				edgeOptions = new EdgeOptions();
				WebDriverManager.edgedriver().driverVersion("88.0.705.68").setup();
				driver = new EdgeDriver(edgeOptions);
						
			//	log.debug("Edge Launched");				
			}			
			
			    // Archiving the previous test report if it exist
			    TestUtil.archiveTestReport();
			    
			    // Extent Report
				extentReport = Extentmanager.GetExtent(generalConfigProperty.getTestReportPath() + generalConfigProperty.getTestReportName());
			    
				driver.navigate().to(envDevConfigProperty.getTestsiteurl());			
			    driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
					   
			    webDriverWait = new WebDriverWait(driver, 15L);		 
		}			
	}
	
	@BeforeClass
	public void beforeClass() {
		// ExtentTest test = new ExtentTest(getClass().getSimpleName());
		ExtentTest classLevelTest = extentReport.createTest(getClass().getSimpleName());
		classLevelLog.set(classLevelTest);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		/*
		//Below lines moved to method "onTestStart()" of "CustomListeners"
		ExtentTest test = classLevelLog.get().createNode(method.getName());
		testLevelLog.set(test);
		// Logging using Extent logs and will be seen in Extent Report against each method
		testLevelLog.get().info("Test Case :-  " + method.getName() + "   execution started");
        */
		//System.out.println("Test Case :-  " + method.getName() + "   execution started");	
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		/* No need of below code as "onTestFailure", "onTestSkipped", "onTestSuccess" method of
		 * "CustomListeners" are handling this.
		 * 
		if(result.getStatus()==ITestResult.SUCCESS) {
			testLevelLog.get().pass("Test Case passed");					
		} else if(result.getStatus()==ITestResult.FAILURE) {
			testLevelLog.get().fail("This test case failed");					
		} else if(result.getStatus()==ITestResult.SKIP) {
			testLevelLog.get().skip("Test case skipped");						
		}
		
		extentReport.flush();
		*/
	}
	
	/*
	 * 	 To check element is present or not.
	 *   This method will return true even if element is hidden (i.e. invisible) but its present on page
	 *   This method will return false if element is not present on page. 
	 */
	public static boolean isElementPresent(String xpath){			
		try{			
			driver.findElement(By.xpath(xpath));
			return true;
			
		}catch(Throwable t){			
			return false;
		}		
	}
	
	/*
	 * Email captured Screen Shot. This will also generate screenshot in "screenshot" folder in project.	
	 */
	public void emailCaptureScreenShot(WebDriver webdriver) throws AddressException, IOException, MessagingException {		
		
		eMail(generalConfigProperty.getSubjectScreenShot(),CaptureScreenShot.captureScreenshot(webdriver), generalConfigProperty.getMessageBodyScreenShot(), generalConfigProperty.getAttachmentNameScreenShot());		
	}
	
	
	/*
	 *  Email NGReport, once whole suite has been run
	 *  Quit the browser
	 */
	
	@AfterSuite
	public void emailTestReport() throws AddressException, MessagingException  {	
		
		String attachmentPath = System.getProperty("user.dir")+"/test-output/"+"emailable-report.html";
		//eMail(generalConfigProperty.getSubject(),attachmentPath, generalConfigProperty.getMessageBodyTestNGReport(), generalConfigProperty.getAttachmentNameNG());
	
        // Quit the browser
		quitBrowser();
	}
	
	/*
	 *  Email any attachment
	 */
	public void eMail(String subject, String attachmentPath, String messageBody, String attachmentName) throws AddressException, MessagingException {		
	
	  //  mail.sendMail(generalConfigProperty.getServer(), generalConfigProperty.getFrom(), generalConfigProperty.getPassword(), generalConfigProperty.getTo(), subject, messageBody, attachmentPath, attachmentName);
	}
	
	/*
	 *  Wait for page loading
	 */
	public static void waitForPageLoaded(WebDriver driver) {
		ExpectedCondition<Boolean> expectation = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return (((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
					    }
				};
		try {
				WebDriverWait waitForLoad = new WebDriverWait(driver,5);
				waitForLoad.until(expectation);				
		} catch (TimeoutException toe) {
			throw toe;			
		}
    } 
	
	private void quitBrowser() {
		
		// Quit the browser
           driver.quit();
	}
	
	public void assignAuthorTestCategory(String author, String testCategory ) {		
		testLevelLog.get().assignAuthor(author);
		testLevelLog.get().assignCategory(testCategory);		
	}

}
