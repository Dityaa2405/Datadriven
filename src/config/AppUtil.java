package config;

import java.io.FileInputStream;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setup() throws Throwable
	{
	
conpro = new Properties();
	//load property file
conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
{
	driver = new ChromeDriver();
	
}
else if(conpro.getProperty("Browser").equalsIgnoreCase("Firefox"))
{
	driver = new FirefoxDriver();
}
else {
	Reporter.log("browser value is not matching",true);
	
}
} 
	@AfterTest
	public static void tearDown() {
		driver.quit();
	}
}