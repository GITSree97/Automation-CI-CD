package letsshopwebapplication.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import letsshopwebapplication.TestComponents.BaseTest;
import letsshopwebapplication.pageobjects.ConfirmPage;
import letsshopwebapplication.pageobjects.LandingPage;
import letsshopwebapplication.pageobjects.OrderPage;
import letsshopwebapplication.pageobjects.ProductCatalogue;
import letsshopwebapplication.pageobjects.cartPage;
import letsshopwebapplication.pageobjects.checkoutPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	//Test case to place order
	@Test(dataProvider="getData",groups="Purchase")

	public void submitOrder(HashMap<String,String> input) throws IOException
	{
        ProductCatalogue productcatalogue= landingpage.loginApplication(input.get("email"),input.get("password"));
	    //List<WebElement>products= productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));
        cartPage cartpage = productcatalogue.goToCart();
     	Boolean match = cartpage.verifyProductDisplayed(input.get("productName"));
		Assert.assertTrue(match);
        checkoutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmPage confirmationpage= checkoutpage.submitOrder();
		String confirmMessage= confirmationpage.getConfirmMessage();
		System.out.println(confirmMessage);
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	//Test case to verify whether the product is there in the Orders page
	
	
//To check whether the product is there in Orders, first we need to run the submitOrder() method
	
	@Test(dependsOnMethods= {"submitOrder"}) 
     public void orderHistoryTest()
	{
	  ProductCatalogue productcatalogue= landingpage.loginApplication("anshikareddy@gmail.com", "Anshika@24");
	 OrderPage orderPage =  productcatalogue.goToOrderPage();
	 Assert.assertTrue(orderPage.verifyOrderDisplayed(productName));
		
	}
	
	
	
	//using @DataProvider we will pass the data to test cases
	@DataProvider
	public Object[][] getData() throws IOException
	
	
	{
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//letsshopwebapplication//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	
	}
	
/*If we pass the data using the below method, we need to pass so many parameters to the test method to catch them which will increase the code complexity;
 //to avoid that we can use hash map
 * @DataProvider
	public Object[][] getData()
	{
	  return new Object[][] {{"email","anshikareddy@gmail.com","Anshika@24",""IPHONE 13 PRO"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	 
	  }
 */
	
	//Hashmap is a class which stores the data in (Key,Value)pairs
			//1st set of data
//			HashMap<String,String> map = new HashMap<String,String>();
//			map.put("email","anshikareddy@gmail.com");
//			map.put("password", "Anshika@24");
//			map.put("productName", "IPHONE 13 PRO");
//			
//			//2nd set of data
//			
//			HashMap<String,String> map1 = new HashMap<String,String>();
//			map1.put("email","shetty@gmail.com");
//			map1.put("password", "Iamking@000");
//			map1.put("productName", "ADIDAS ORIGINAL");
}
