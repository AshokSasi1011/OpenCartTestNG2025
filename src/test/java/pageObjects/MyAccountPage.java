package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	
	public MyAccountPage(WebDriver driver) {		
		super(driver);
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	private WebElement lnkLogout;
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	private WebElement dispMyAccount;
	
	@FindBy(xpath="//h1[normalize-space()='Account Logout']")
	private WebElement dispLogout;
	
	public void clickLogout() {
		lnkLogout.click();
	}
	
	public boolean isMyAccountDisplayed() {
		try {
			return(dispMyAccount.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public boolean isDipLogout() {
		try {
			return(dispLogout.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	
}
