package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends StartupPage{
	
	//Web elements
	By pageTitleText = By.className("title");
	By cartIcon = By.className("shopping_cart_link");
	By continueShoppingButton = By.id("continue-shopping");
	By checkoutButton = By.id("checkout");
		
	//Getting the page name
	String pageName = this.getClass().getSimpleName();

	public CartPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(checkoutButton, 120)
					.waitTillElementVisible(checkoutButton, 30);
	}
	
	private void clickCheckout() {
		commonEvents.waitTillElementLocated(checkoutButton, 30)
					.waitTillElementVisible(checkoutButton, 30)
					.click(checkoutButton);
	}
	
	public YourInformationPage checkout_proceedToYourInformationPage() {
		try {
			clickCheckout();
			return new YourInformationPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
