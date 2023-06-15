package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage extends StartupPage {
	
	//Web elements
	By confirmationMessage = By.className("complete-header");
	By backToHomeButton = By.id("back-to-products");

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(confirmationMessage, 120)
					.waitTillElementVisible(confirmationMessage, 30);
	}
	
	public String getConfirmationMessage() {
		try {
			return commonEvents.waitTillElementLocated(confirmationMessage, 30)
								.waitTillElementVisible(confirmationMessage, 30)
								.getText(confirmationMessage);
		}catch(Exception e) {
			throw e;
		}
	}

}
