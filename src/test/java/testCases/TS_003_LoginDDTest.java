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
import utilities.DataProviders;

public class TS_003_LoginDDTest extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups= {"master","regression"})
	@Description("Validate the Login Functionality By Giving Valid and Credentials Using DDT")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("OpenCart Ecommerce Application")
    @Story("Validate the Login Functionality in OpenCart Using DDT")
	public void loginDDT(String username, String password, String res) {
		
		logger.info("*****Starting Login Account Test Using DataDriven*****");
		
		Allure.step("*****Starting Account Registration Test*****");
		
		try {

			HomePage h = new HomePage(driver);
			h.clickMyAccount();
			h.clickLogin();	

			LoginPage lp = new LoginPage(driver);
			lp.setEmail(username);
			lp.setPassword(password);
			lp.clickLoginButton();

			logger.info("*****Validate the Presence of My Account*****");	
			
			Allure.step("*****Validate the Presence of My Account*****");
			
			MyAccountPage m = new MyAccountPage(driver);
			boolean targetPage = m.isMyAccountDisplayed();

			if(res.equalsIgnoreCase("valid")) {
				if(targetPage==true) {
					m.clickLogout();
					logger.info("*****Test Passed*****");
					Allure.step("*****Test Passed*****");
					Assert.assertTrue(true);					
				}
				else {
					logger.info("*****Test Failed*****");
					Allure.step("*****Test Failed*****");
					Assert.assertTrue(false);

				}
			}
			if(res.equalsIgnoreCase("invalid")) {
				if(targetPage==true) {
					m.clickLogout();
					logger.info("*****Test Failed*****");
					Allure.step("*****Test Failed*****");
					Assert.assertTrue(false);				
				}
				else {
					logger.info("*****Test Passed*****");
					Allure.step("*****Test Passed*****");
					Assert.assertTrue(true);
				}
			}		


		}
		catch(Exception e) {
			System.out.println(e);
			logger.info("*****Test Failed*****");
			Assert.fail();				
		}
		
		logger.info("*****Finished Execution of Login DDT Test*****");
		
		
	}
	

}
