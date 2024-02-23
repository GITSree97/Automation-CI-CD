package letsshopwebapplication.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import letsshopwebapplication.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	//To give the knowledge of driver to this class, we will use constructor.
	//We will create object of this class in parent class
	
	
WebDriver driver;	
	
public LandingPage(WebDriver driver)
  {
    super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);//It will trigger all the methods initialized in the class by passing driver object.
	
  }

	//WebElement userEmail= driver.findElement(By.id("userEmail"));
	
	//Pagefactory- it is a design pattern where we can use it to reduce the syntax of webelement
	//to give the knowledge of driver to @FindBy annotated webelement, we have method initElements

	@FindBy(id="userEmail")
	WebElement useremail;
	@FindBy(id="userPassword")
	WebElement userpassword;
	@FindBy(id="login")
	WebElement loginSubmit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	//action method to login 
	
	public ProductCatalogue loginApplication(String email, String password)

	{
		useremail.sendKeys(email);
		userpassword.sendKeys(password);
		loginSubmit.click();
		//since we know that after click on login button it will go to prdouctcatalogue page; 
		//we will create object of ProductCatalogue class in this method itself and
		//return the object productcatalogue
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	//action method to open URL
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
}
