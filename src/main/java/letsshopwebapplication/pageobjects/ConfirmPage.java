package letsshopwebapplication.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import letsshopwebapplication.AbstractComponents.AbstractComponent;

public class ConfirmPage extends AbstractComponent{
	
	WebDriver driver;	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
public ConfirmPage(WebDriver driver)
  {
    super(driver);
	this.driver= driver;
	PageFactory.initElements(driver, this);//It will trigger all the methods initialized in the class by passing driver object.

  }

public String getConfirmMessage()
{
	return confirmMessage.getText();

}

}
