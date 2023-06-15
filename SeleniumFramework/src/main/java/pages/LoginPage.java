package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends StartupPage {
	
	//Web elements
	By usernameField = By.id("user-name");
	By passwordField = By.id("password");
	By loginButton = By.id("login-button");
	
	//Getting the page name
	String pageName = this.getClass().getSimpleName();
			

	public LoginPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(usernameField, 120)
					.waitTillElementVisible(usernameField, 30);
	}
	
	private void enterUsername(String username) {
		commonEvents.waitTillElementLocated(usernameField, 30)
					.waitTillElementVisible(usernameField, 30)
					.sendKeys(usernameField, username);
	}
	
	private void enterPassword(String password) {
		commonEvents.waitTillElementLocated(passwordField, 30)
					.waitTillElementVisible(passwordField, 30)
					.sendKeys(passwordField, password);
	}
	
	private void clickLogin() {
		commonEvents.waitTillElementLocated(loginButton, 30)
					.waitTillElementVisible(loginButton, 30)
					.click(loginButton);
	}
	
	public ProductPage login_proceedToProductsPage(Map<String, String> map) {
		try {
			enterUsername(map.get("Username"));
			enterPassword(map.get("Password"));
			clickLogin();
			return new ProductPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
