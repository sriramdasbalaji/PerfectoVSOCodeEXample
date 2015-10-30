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



public class MSiOS extends basicTest{


	@Test (dataProvider="PerfectoParams") 
	public void test1(PerfectoTestParams params) {
		System.out.println("Test1 in Test"+params._platform);
		System.out.println("Test1 in Test"+params._repKey);

	//	RemoteWebDriver d = util.getAppiumDriver(params._device,params._activityBandle,params._platform,params._cloud,params._user,params._password,params._repKey);
		try {
	//		this.execTest(d);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
	//		this.endTest(d);

		}


	}

	@Override
	public void execTest(RemoteWebDriver driver) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[@text='Access your SPG Account']")).click();

		driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("perfectomobile");
		driver.findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys("Perfecto1");
		driver.findElement(By.xpath("//android.widget.Button")).click();

		driver.findElement(By.xpath("//android.widget.TextView[@text='Preferred']")).click();

		WebElement mNum = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Starpoints')]"));
		System.out.println("****** Account number :" +mNum.getText());

		driver.findElement(By.xpath("//android.widget.Button[@text='Sign Out']")).click();

		driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();



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
