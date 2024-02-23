package letsshopwebapplication.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import letsshopwebapplication.TestComponents.BaseTest;
import letsshopwebapplication.TestComponents.Retry;
import letsshopwebapplication.pageobjects.ConfirmPage;
import letsshopwebapplication.pageobjects.ProductCatalogue;
import letsshopwebapplication.pageobjects.cartPage;
import letsshopwebapplication.pageobjects.checkoutPage;

public class ErrorvalidationsTest extends BaseTest{
	@Test(groups= {"ErrorHandling"}, retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
      
//negative scenario- invalid password
      landingpage.loginApplication("anshikareddy@gmail.com", "Ansha@24");
      Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}
	//negative test case by giving the incorrect product name which is not on the cart page
	
	@Test
	public void ProductErrorValidation() throws IOException
	{
		// TODO Auto-generated method stub
      
		
		String productName = "ZARA COAT 3";
        ProductCatalogue productcatalogue= landingpage.loginApplication("anshikareddy@gmail.com", "Anshika@24");
	    productcatalogue.addProductToCart(productName);
        cartPage cartpage = productcatalogue.goToCart();
     	Boolean match = cartpage.verifyProductDisplayed("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	

}
