package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilites.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath = "./FileInput/Login.xlsx";
	String outputpath = "./FileOutput/DataDrivenResults.xlsx";
	ExtentReports reports;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		//define path of html
	reports = new ExtentReports("./Reports/Login.html");
		//create object for ExcelfileUtil class
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("LoginData");
		Reporter.log("no of rows are::"+rc,true);
		//iterate all rows in a sheet
		for(int i=1;i<=rc;i++)
		{
		logger = reports.startTest("validate Login");
			//call UserName and password cell
			String username = xl.getCellData("LoginData", i,0);
			String password = xl.getCellData("LoginData", i,1);
			logger.log(LogStatus.INFO,username+"-----------"+ password);
			boolean res = FunctionLibrary.adminLogin(username, password);
			if(res)
			{
				//if res is true write as valid UserName password
				xl.setCelldata("LoginData", i, 2, "valid username and passwords", outputpath);
				//write as pass into status cell
				xl.setCelldata("LoginData", i, 3, "pass", outputpath);
				logger.log(LogStatus.PASS,"Login is sucess");
			}
			else
			{
				//if res is false write as valid UserName and password into result cell
				xl.setCelldata("LoginData", i, 2, "invalidusername and password", outputpath);
				//write as fail into status cell
				xl.setCelldata("LoginData", i, 3, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "login is fail");
			}
			reports.endTest(logger);
			reports.flush();
		}
	}

}
