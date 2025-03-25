package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement txtConfirmPassword;

	@FindBy(xpath="//input[@name='agree']")
	private WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement btnContinueButton;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	private WebElement radYes;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	private WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPhoneNumber(String phone) {
		txtTelephone.sendKeys(phone);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setConfirmPassword(String confPass) {
		txtConfirmPassword.sendKeys(confPass);
	}
	
	public void checkPrivacyPolicy() {
		chkPrivacyPolicy.click();
	}
	
	public void selectSubscriptionYes() {
		radYes.click();
	}
	
	public void clickContinueButton() {
		btnContinueButton.click();
	}
	
	public String getMessageConfirmation() {
		try {
			return(msgConfirmation.getText());			
		}
		catch(Exception e) {
			return (e.getMessage());
		}
		
	}
	

}
