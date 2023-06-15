package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverViewPage extends StartupPage {
	
	//Web Elements
	By finishButton = By.id("finish");
	
	//Getting the page name
	String pageName = this.getClass().getSimpleName();

	public OverViewPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(finishButton, 120)
					.waitTillElementVisible(finishButton, 30);
	}
	
	private void clickFinish() {
		commonEvents.waitTillElementLocated(finishButton, 30)
					.waitTillElementVisible(finishButton, 30)
					.click(finishButton);
	}
	
	public ConfirmationPage finish_proceedToConfirmationPage() {
		try {
			clickFinish();
			return new ConfirmationPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
