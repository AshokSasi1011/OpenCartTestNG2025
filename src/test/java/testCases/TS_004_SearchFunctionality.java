package testCases;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TS_004_SearchFunctionality extends BaseClass{
	
	@Test(groups= {"master","sanity","regression"})
	@Description("Validate the Search Product Functionality By Giving Available Products")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("OpenCart Ecommerce Application")
    @Story("Validate the Search Product Functionality in OpenCart")
	public void searchProductTest() {
		
		try {
			logger.info("****Search Product Test is Started*****");
			Allure.step("****Search Product Test is Started*****");
			
			SearchPage s = new SearchPage(driver);
			s.setProductName(p.getProperty("searchProductName"));
			s.clickSearchButton();
			boolean isProductAvailable = s.isProductLinkAvailable();
			Thread.sleep(3000);
			
			try {
				Assert.assertTrue(isProductAvailable);
				logger.info("****Search Product Test is Passed*****");
				Allure.step("****Search Product Test is Passed*****");
			} catch (AssertionError e) {
				System.out.println(e);
				logger.info("****Search Product Test is Failed*****");
				Allure.step("****Search Product Test is Failed*****");
				Assert.fail();
			}
		
		}
		catch(Exception e){
			System.out.println(e);
			logger.info("****Search Product Test is Failed*****");
			Allure.step("****Search Product Test is Failed*****");
			Assert.fail();
		}
		
		
	}

}
