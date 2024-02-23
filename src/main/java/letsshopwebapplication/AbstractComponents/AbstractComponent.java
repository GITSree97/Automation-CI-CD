package letsshopwebapplication.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import letsshopwebapplication.pageobjects.OrderPage;
import letsshopwebapplication.pageobjects.cartPage;

public class AbstractComponent {
	

	//will place reusable components such as wait
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//since page object classes already have the knowledge of driver, we will use inheritance 
	//and get the driver object here
	@FindBy(css="[routerlink*='cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orders;

	
	public void waitForElementToAppear(By findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//explicit wait
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//explicit wait
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitforElementToDisappear(WebElement webele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(webele));
	}
	
	public void scrollToElement(WebElement element)
	{

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
	}
	
	public OrderPage goToOrderPage()
	 {
		 orders.click();
		 OrderPage orderpage = new OrderPage(driver);
		 return orderpage;
	 }
		
	
	 public cartPage goToCart()
	 {
		 cart.click();
		 cartPage cartpage = new cartPage(driver);
		 return cartpage;
	 }
		
	


}
