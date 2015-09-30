package test.java;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import salesforce.util;


public class test1 extends basicTest{

	@Test (dataProvider="Devices") 
	public void SPG(String DeviceId) {
		System.out.println("Test1 in Test"+DeviceId);
		RemoteWebDriver d = util.getRWD(DeviceId);
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

		driver.findElement(By.xpath("//*[@text='Access your SPG Account']")).click();
			 
		driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("tgolani");
		driver.findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys("spg2011");
		driver.findElement(By.xpath("//android.widget.Button")).click();
		 
		driver.findElement(By.xpath("//android.widget.TextView[@text='Preferred']")).click();

			WebElement mNum = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Starpoints')]"));
		System.out.println("****** Account number :" +mNum.getText());
		
		util.swipe("50%,80%",  "50%,20%" , driver);
		driver.findElement(By.xpath("//android.widget.Button[@text='Sign Out']")).click();

		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();

		driver.close();
		

	}

	@Override
	public void beforeTest() throws Exception {
		// TODO Auto-generated method stub

	}




	@Override
	public void endTest(RemoteWebDriver driver) {
		driver.quit();
	
	}


}
