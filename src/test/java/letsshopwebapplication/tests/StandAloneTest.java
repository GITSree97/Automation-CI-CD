package letsshopwebapplication.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import letsshopwebapplication.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args){
		// TODO Auto-generated method stub
String productName = "IPHONE 13 PRO";
String actualCountry = "India";
//chromedriver will be automatically downloaded in to the system based on chrome browser version
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
		//open the URL
		driver.get("https://rahulshettyacademy.com/client/");
		//login 
		
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("anshikareddy@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Anshika@24");
		driver.findElement(By.id("login")).click();
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));//explicit wait
		//wait until all the items on the page gets loaded
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-md-0")));
		//get all the products present on the webpage and store them in a list
		List<WebElement>products=driver.findElements(By.cssSelector(".offset-md-0"));
			
		//check whether ZARA COAT is present in the list
	//now we cannot directly apply getText() method since we don't have access to the product text
		
		//products.stream().filter(product->product.getText().equalsIgnoreCase("ZARA COAT 3")).collect(Collectors.toList());
	
		WebElement prod= products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		//click on add to cart for ZARA
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
	
	
	//wait until the "product is added to cart" message is displayed
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
//wait until the loading is finished(you can check with developer for that locator since it disappears very quick
	
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	//Click on cart
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
	//verify whether the product which we added is displayed in cart
	
	//locator to find the name of the item
	List<WebElement>cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
	
	//check if there is any item is cart matches to product name
	//anymatch method will return boolean value
	boolean match = cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
	
	//If match variable has true then the test will pass
	Assert.assertTrue(match);
	
	JavascriptExecutor js =  (JavascriptExecutor) driver;
	
    js.executeScript("window.scrollBy(0,500)");
    
    
    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']"))));
	//click on checkout
	driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
	
	//enter country using send keys
	//driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ind");
	
	
	//enter country using Action class
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='ta-results']")));
	
	//handling the autosuggestive dropdown using for loop
	List<WebElement> countries = driver.findElements(By.cssSelector("[class*='ng-star']"));
	
	for(WebElement country: countries)
	{
		if(country.getText().equalsIgnoreCase(actualCountry))
		{
			country.click();
			break;
		}
	}
	
	//Click on Place Order
	js.executeScript("window.scrollBy(0,500)");
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".action__submit"))));
	driver.findElement(By.cssSelector(".action__submit")).click();
	
	//get the text of confirmation message
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	//check if the confirmation message displayed is matching with the actual text
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	driver.close();
	

	}
	

}
