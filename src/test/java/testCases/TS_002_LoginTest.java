package testCases;

import org.junit.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TS_002_LoginTest extends BaseClass{
	
	@Test(groups= {"master","sanity","regression"})
	@Description("Validate the Login Functionality By Giving Valid Credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("OpenCart Ecommerce Application")
    @Story("Validate the Login Functionality in OpenCart")
	public void loginTest() {
		
		logger.info("*****Starting Login Account Test*****");
		
		Allure.step("*****Starting Login Account Test*****");
		
		try {
		
		HomePage h = new HomePage(driver);
		h.clickMyAccount();
		h.clickLogin();	
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginButton();
		
		MyAccountPage m = new MyAccountPage(driver);
		
		logger.info("*****Validate the Presence of My Account*****");
		
		Allure.step("*****Validate the Presence of My Account*****");
		
			try {
				Assert.assertFalse(m.isMyAccountDisplayed());
				logger.info("*****Test Passed*****");
				Allure.step("*****Test Passed*****");
				
			} catch (AssertionError e) {
				logger.info(e);
				Allure.step(e.toString());
				m.clickLogout();
				logger.info("*****Test Failed*****");
				Allure.step("*****Test Failed*****");
				Assert.fail();
			}

		
		}
		catch(Exception e) {
			System.out.println(e);
			logger.info("*****Test Failed*****");
			Allure.step("*****Test Failed*****");
			Assert.fail();
			
		}		

	}

}
