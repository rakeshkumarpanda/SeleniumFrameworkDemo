package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends StartupPage {
	
	//Web elements
	By pageTitleText = By.className("title");
	By cartIcon = By.className("shopping_cart_link");
	By productNamePaths = By.className("inventory_item_name");
	
	//Locators with Dynamic valued input
	String desiredProduct_addToCartButtonPath = "//div[text()='value']/../../../div[@class='pricebar']/button";
	
	//Getting the page name
	String pageName = this.getClass().getSimpleName();

	public ProductPage(WebDriver driver) {
		super(driver);
		commonEvents.waitTillElementLocated(pageTitleText, 120)
					.waitTillElementVisible(pageTitleText, 30);
	}
	
	public String getPageTitle() {
		try {
			return commonEvents.waitTillElementLocated(pageTitleText, 30)
								.waitTillElementVisible(pageTitleText, 30)
								.getText(pageTitleText);
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void addProductToCart(String product) {
		if(product!=null) {
			String[] productNames = product.split("\\|");
			for(int index=0; index<productNames.length; index++) {
				By finalProduct = By.xpath(desiredProduct_addToCartButtonPath.replace("value", productNames[index].trim()));
				commonEvents.waitTillElementLocated(finalProduct, 30)
							.waitTillElementVisible(finalProduct, 30)
							.click(finalProduct);
			}
		}
	}
	
	private void clickCartIcon() {
		commonEvents.waitTillElementLocated(cartIcon, 30)
					.waitTillElementVisible(cartIcon, 30)
					.click(cartIcon);
	}
	
	public CartPage selectProduct_proceedToCartPage(Map<String, String> map) {
		try {
			addProductToCart(map.get("Product Name"));
			clickCartIcon();
			return new CartPage(driver);
		}catch(Exception e) {
			throw e;
		}
	}

}
