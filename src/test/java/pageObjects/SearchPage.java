package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SearchPage extends BasePage {
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement txtSearch;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement btnSearch;
	
	@FindBy(xpath="//div[@class='product-thumb']")
	private WebElement lnkProductLink;
	
	
	
	public void setProductName(String searchProduct) {
		txtSearch.click();
		txtSearch.sendKeys(searchProduct);
	}
	
	public void clickSearchButton() {
		btnSearch.click();
	}
	
	public boolean isProductLinkAvailable() {
		return lnkProductLink.isDisplayed();
	}
	

}
