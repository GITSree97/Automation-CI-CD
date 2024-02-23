package letsshopwebapplication.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import letsshopwebapplication.pageobjects.LandingPage;


public class BaseTest {
	public WebDriver driver;
	public LandingPage landingpage;
	
public WebDriver initializeDriver() throws IOException
    {
	
/*We will use global properties file to store in which browser we want to execute the app
and based on that value we will set the property to open the respective browser*/
//Properties class in Selenium allows loading and retrieving values from properties files
//To load properties file we need to create Properties object and use load() method with a FileInputStream		
//Values can be retrieved from the properties file using the getProperty() method based on the associated keys.
	
//create object for Properties class
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\letsshopwebapplication\\resources\\GlobalData.properties");
	prop.load(fis);
	
	//If we pass the browser value using maven, then the test will run in that browser or else
	//test will be executed in the browser which is passed in properties file
String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	//String browserName = prop.getProperty("browser");
	
	
	
	if(browserName.contains		("chrome"))
	{
		
		
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", "C:/Users/maddi/OneDrive/Documents/chromedriver.exe");
	    
		if(browserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900));//full screen
	}
	else if(browserName.equalsIgnoreCase("firefox"))
	{
	//firefox	
	}
	else if(browserName.equalsIgnoreCase("edge"))
	{
		 System.setProperty("webdriver.edge.driver" ,"C:/Users/maddi/OneDrive/Documents/msedgedriver.exe");
		  driver = new EdgeDriver();
	}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	return driver;

	}

public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
	
	//To read the data from json to string
	
	String jsonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
   //To convert string to hashmap, we need to get new depenedency Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	
	List<HashMap<String,String>>data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data; //{map,map}
	}
	
public String getScreenshot(String testCaseName, WebDriver driver) throws IOException

{
	TakesScreenshot ts = (TakesScreenshot)driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	File file = new File(System.getProperty("user.dir")+ "//reports//"+ testCaseName+ ".png");
    FileUtils.copyFile(source, file);
    return System.getProperty("user.dir")+ "//reports//"+ testCaseName+ ".png";

}

//Since we need to open the browser and hit url for all test case, we will use @BeforeMethod

@BeforeMethod(alwaysRun=true)
public LandingPage launchApplication() throws IOException
{
	 driver= initializeDriver();
	  landingpage = new LandingPage(driver);
	 landingpage.goTo();
	 return landingpage;
}

@AfterMethod(alwaysRun=true)
public void closeBrowser()
{
	 driver.close();	
}

}