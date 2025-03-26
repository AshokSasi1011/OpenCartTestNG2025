package testBase;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.RandomAccessFileMode;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import utilities.AllureReportManager;

@Listeners(AllureReportManager.class)
public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"master","sanity","regression"})
	@Parameters({"os","browser"})
	@Step("Opening URL: {0}")
	
	public void setUp(String os, String br) throws IOException {
		
		//getting values from config.properties file
		FileReader file = new FileReader("src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		
		//loading log4j file
		logger = LogManager.getLogger(this.getClass()); 
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			//os
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Incorrect OS Selection");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
				case "firefox": capabilities.setBrowserName("firefox");break;
				default: System.out.println("Invalid Browser Selection"); return;
			}	
			
			//initiating remote driver
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}

		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();break;
			case "edge":driver = new EdgeDriver();break;
			case "firefox":driver = new FirefoxDriver();break;
			default:System.out.println("No Matching Browser");return;
			}		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}	
	
	@AfterClass(groups= {"master","sanity","regression"})
	public void tearDown() {
				
		if(driver!=null) {
			driver.close();
		}
	}	
	
	public String randomString(int count) {
		String generatedString = RandomStringUtils.randomAlphabetic(count);
		return generatedString;
	}
	
	public String randomNumber(int count) {
		String generatedNumber = RandomStringUtils.randomNumeric(count);
		return generatedNumber;
	}
	
	public String randomAlphaNumeric(int count) {
		String generatedString = RandomStringUtils.randomAlphabetic(count);
		String generatedNum = RandomStringUtils.randomNumeric(2);
		String generatedAlphaNum = generatedString + "@" + generatedNum;
		return generatedAlphaNum;
	}
	
	public String captureScreen(String tname) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot tk = (TakesScreenshot) driver;
		File sourceFile = tk.getScreenshotAs(OutputType.FILE);
		
		String targetPath = System.getProperty("user.dir")+"\\screenshots\\"+tname+" "+timeStamp+".png";
		File targetFile = new File(targetPath);
		
		sourceFile.renameTo(targetFile);
		
		return targetPath;
	}
	
	
	public void attachScreenshot() {
		Allure.addAttachment("Screenshot For Failure", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
		
	}
	
	



}
