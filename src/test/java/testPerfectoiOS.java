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
import org.testng.Assert;
import org.testng.annotations.Test;



public class testPerfectoiOS extends basicTest{


	@Test (dataProvider="PerfectoParams") 
	public void SPG(PerfectoTestParams params) {
		System.out.println("Test1 in Test"+params._platform);
		System.out.println("Test1 in Test"+params._repKey);

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

		 
		driver.findElement(By.xpath("//UIATextField[1]")).sendKeys("uzi");
		driver.findElement(By.xpath("//UIASecureTextField")).sendKeys("Perfecto1");
		driver.findElement(By.xpath("//UIAButton")).click();

		driver.findElement(By.xpath("//*[@label='Invalid credentials']"));
		driver.findElement(By.xpath("//*[@label='Close']")).click();



	}

	@Override
	public void beforeTest() throws Exception {



	}




	@Override
	public void endTest(RemoteWebDriver driver) {



		String devId = (String) driver.getCapabilities().getCapability("deviceName");

		//close the test  
		try{
			driver.close();				
			util.downloadReport(driver, "pdf","VSO_SPG_"+devId);	

		} catch (IOException e) {

			e.printStackTrace();

		}finally
		{

			try{
				driver.quit();

			}catch(Exception e)
			{
				//  driver closed 
			}


		} 


	}


}
