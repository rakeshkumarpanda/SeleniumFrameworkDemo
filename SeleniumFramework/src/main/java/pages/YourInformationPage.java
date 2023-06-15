package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YourInformationPage extends StartupPage {
	
	//Web elements
	By firstnameField = By.id("first-name");
	By lastnameField = By.id("last-name");
	By zipcodeField = By.id("postal-code");
	By cancelButton = By.id("cancel");
	By continueButton = By.id("continue");
	
	//Getting the page name
	String pageName = this.getClass().getSimpleName();

	public YourInformationPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(firstnameField, 120)
					.waitTillElementVisible(firstnameField, 30);
	}
	
	private void enterFirstname(String firstname) {
		if(firstname!=null)
			commonEvents.waitTillElementLocated(firstnameField, 30)
						.waitTillElementVisible(firstnameField, 30)
						.sendKeys(firstnameField, firstname);
	}
	
	private void enterLastname(String lastname) {
		if(lastname!=null)
			commonEvents.waitTillElementLocated(lastnameField, 30)
						.waitTillElementVisible(lastnameField, 30)
						.sendKeys(lastnameField, lastname);
	}
	
	private void enterZipcode(String zipcode) {
		if(zipcode!=null)
			commonEvents.waitTillElementLocated(zipcodeField, 30)
						.waitTillElementVisible(zipcodeField, 30)
						.sendKeys(zipcodeField, zipcode);
	}
	
//	private void clickCancel() {
//		commonEvents.waitTillElementLocated(cancelButton, 30)
//					.waitTillElementVisible(cancelButton, 30)
//					.click(cancelButton);
//	}
	
	private void clickContinue() {
		commonEvents.waitTillElementLocated(continueButton, 30)
					.waitTillElementVisible(continueButton, 30)
					.click(continueButton);
	}
	
	public OverViewPage continue_proceedToConfirmationPage(Map<String, String> map) {
		try {
			enterFirstname(map.get("Firstname"));
			enterLastname(map.get("Lastname"));
			enterZipcode(map.get("Zipcode"));
			clickContinue();
			return new OverViewPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
