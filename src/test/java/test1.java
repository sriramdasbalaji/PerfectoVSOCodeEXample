package test.java;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;



public class test1 extends basicTest{

	@Test (dataProvider="PerfectoParams") 
	public void SPG(PerfectoTestParams params) {
		System.out.println("Test1 in Test"+params._platform);
		 RemoteWebDriver d = util.getAppiumDriver(params._device,params._activityBandle,params._platform,params._cloud,params._user,params._password,params._repKey);
		try {
		 	this.execTest(d);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
		 	this.endTest(d);

		}


	}

	@Override
	public void execTest(RemoteWebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		 
	}

	@Override
	public void beforeTest() throws Exception {
		// TODO Auto-generated method stub

	}




	@Override
	public void endTest(RemoteWebDriver driver) {
		driver.quit();
		try {
			util.downloadReport(driver, "html", "VSO_REP");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();

	
	}


}
