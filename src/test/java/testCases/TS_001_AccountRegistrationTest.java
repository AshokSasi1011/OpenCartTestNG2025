package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups= {"master","sanity","regression"})
	@Description("Validate the Register Functionality By Giving Valid Credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("OpenCart Ecommerce Application")
    @Story("Validate the Register Functionality in OpenCart")
	public void registerAccountTest() {		
		
		//All < Trace < Debug < Info < Warn < Error < Fatal < Off 
		
		logger.info("*****Starting Account Registration Test*****");
		
		Allure.step("*****Starting Account Registration Test*****");
		
		try {
		HomePage h = new HomePage(driver);
		h.clickMyAccount();
		h.clickRegister();		
		
		AccountRegistrationPage a = new AccountRegistrationPage(driver);		
		a.setFirstName(randomString(5).toUpperCase());
		a.setLastName(randomString(5).toUpperCase());
		a.setEmail(randomString(6)+randomNumber(4)+"@gmail.com");
		a.setPhoneNumber(randomNumber(10));
		a.selectSubscriptionYes();
		
		String pass = randomAlphaNumeric(5);
		a.setPassword(pass);
		a.setConfirmPassword(pass);
		a.checkPrivacyPolicy();
		a.clickContinueButton();
		
		logger.info("*****Validate the account creation confirmation message*****");
		
		Allure.step("*****Validate the account creation confirmation message*****");
		
		String messageConfirmation = a.getMessageConfirmation();	
		
			try {
				Assert.assertEquals(messageConfirmation,"Your Account Has Been Created!");				
				logger.info("*****Test Passed*****");				
				Allure.step("*****Test Passed*****");
				
			} catch (AssertionError e) {
				logger.info(e);				
				logger.info("*****Test Failed*****");	
				
				Allure.step(e.toString());
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
