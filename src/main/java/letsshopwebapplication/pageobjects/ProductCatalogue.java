package letsshopwebapplication.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import letsshopwebapplication.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	
		
WebDriver driver;	
//To give the knowledge of driver to this class, we will use constructor.	
public ProductCatalogue(WebDriver driver)
  {
	super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);//It will trigger all the methods initialized in the class by passing driver object.
	
  }

	@FindBy(css=".offset-md-0")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy= By.cssSelector(".offset-md-0");
	By addToCart= By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	
	//Action Method to wait until all the products are loaded and return the list of products.
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsBy);
		return products;

	}
	
	//Action method to find whether the desired product is there in product list
	 public WebElement getProductByName(String productName)
	 {
		 WebElement prod = getProductList().stream()
				 .filter(product->product.findElement(By.cssSelector("b"))
					.getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	 }
	 
/*method to click on add to cart for the filtered product and wait until toastmessage appears
	 and loading disappears*/
	 public void addProductToCart(String productName)
	 
	 {
         WebElement prod = getProductByName(productName);
		 prod.findElement(addToCart).click();
		 waitForElementToAppear(toastMessage);
		 waitforElementToDisappear(spinner);
		 
 
	 }
	 

}
