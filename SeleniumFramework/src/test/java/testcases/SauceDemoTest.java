package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.CartPage;
import pages.ConfirmationPage;
import pages.LoginPage;
import pages.OverViewPage;
import pages.ProductPage;
import pages.StartupPage;
import pages.YourInformationPage;
import testBase.AppTestBase;

public class SauceDemoTest extends AppTestBase {
	
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	
	StartupPage startupPage;
	
	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readJson(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);
		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}
	
	@Test(priority = 1, groups = {"sanity"})
	public void buyProductTest() throws Exception {
		softAssert = new SoftAssert();
		
		String loginDataFilePath = testDataFilePath+"Login.json";
		Map<String, String> loginData = new FileOperations().readJson(loginDataFilePath, "credentials");
		String buyProductDataFilePath = testDataFilePath+"BuyProduct.json";
		Map<String, String> testData = new FileOperations().readJson(buyProductDataFilePath, "testdata");
		Map<String, String> expectedData = new FileOperations().readJson(buyProductDataFilePath, "expecteddata");
		
		LoginPage loginPage = startupPage.navigateToLoginPage();
		ProductPage productPage = loginPage.login_proceedToProductsPage(loginData);
		CartPage cartPage = productPage.selectProduct_proceedToCartPage(testData);
		YourInformationPage yourInformationPage = cartPage.checkout_proceedToYourInformationPage();
		OverViewPage overViewPage = yourInformationPage.continue_proceedToConfirmationPage(testData);
		ConfirmationPage confirmationPage = overViewPage.finish_proceedToConfirmationPage();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		
		softAssert.assertEquals(confirmationMessage, expectedData.get("Confirmation Message"), 
				"Actual confirmation message is not matching with the expected confirmation message");
		
		softAssert.assertAll();
	}
	
	@AfterClass
	public void tearDown() {
		browserTearDown();
	}

}
