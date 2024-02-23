package letsshopwebapplication.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import letsshopwebapplication.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent{
	
	//To give the knowledge of driver to this class, we will use constructor.
	//We will create object of this class in parent class
	
	
WebDriver driver;	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css="li[class='totalRow'] button[type='button']")
	WebElement checkout;
	
public cartPage(WebDriver driver)
  {
    super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);//It will trigger all the methods initialized in the class by passing driver object.

	
  }

public boolean verifyProductDisplayed(String productName)
{
	//anymatch method will return boolean value
	boolean match = cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
	return match;

}

public checkoutPage goToCheckout()
{
	scrollToElement(checkout);
	checkout.click();
	checkoutPage checkoutpage=  new checkoutPage(driver);
	return checkoutpage;

	
	
	}



}
	