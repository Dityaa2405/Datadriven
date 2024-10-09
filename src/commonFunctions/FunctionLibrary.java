package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	public static boolean adminLogin(String user, String pass) throws Throwable
	{
		driver.get(conpro.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath(conpro.getProperty("ObjReset"))).click();
		driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(user);
		driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
		driver.findElement(By.xpath(conpro.getProperty("ObjLogin"))).click();
		Thread.sleep(3000);
		String Expected = "dashboard";
		String Actual = driver.getCurrentUrl();
		if(Actual.contains(Expected))
		{
			Reporter.log("valid user and password;; " +Expected+"------------"+Actual,true);
			//click logout link
			driver.findElement(By.xpath(conpro.getProperty("objLogout"))).click();
			return true;
			}
		else {
			//capture error message
			String Message = driver.findElement(By.xpath(conpro.getProperty("objerror"))).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath(conpro.getProperty("objok"))).click();
			Reporter.log(Message+"-----------------------"+Expected+"--------------"+Actual,true);
			return false;
			
		}
		
		
		
	}

}
