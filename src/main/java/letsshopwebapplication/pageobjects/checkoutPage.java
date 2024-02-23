package letsshopwebapplication.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import letsshopwebapplication.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent{
	
	//To give the knowledge of driver to this class, we will use constructor.
	//We will create object of this class in parent class
	
	
WebDriver driver;	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class, 'ta-item')]) [2]")
	WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By results = By.cssSelector("[class*='ta-results']");
	
public checkoutPage(WebDriver driver)
  {
    super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);//It will trigger all the methods initialized in the class by passing driver object.

	
  }

public void selectCountry(String countryName)

{
	Actions a = new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	waitForElementToAppear(results);
	selectCountry.click();
	
	
	}

public ConfirmPage submitOrder()
{
	scrollToElement(submit);
	submit.click();
	ConfirmPage confirm = new ConfirmPage(driver);
	return confirm;
	}

}