package test.java;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;


public class util {

	//public static String REPORT_LIB = "../../TestResults/";
	public static String REPORT_LIB = ".."+File.separator;

	public static String SCREENSHOTS_LIB = "/Users/uzie/Documents/PMRepos/reports/";


	public static void closeTest(RemoteWebDriver driver)
	{
		System.out.println("CloseTest");
		driver.quit();
	}





	public static RemoteWebDriver getRWD(String deviceId)   {


		RemoteWebDriver webdriver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities("mobileOS", "", Platform.ANY);

		capabilities.setCapability("user", "uzie@perfectomobile.com");
		capabilities.setCapability("password", "@Perfecto1");
		capabilities.setCapability("deviceName",  deviceId);


		//capabilities.setCapability("takesScreenShot", false);
		//capabilities.setCapability("automationName", "PerfectoMobile");
		try {
			webdriver = new RemoteWebDriver(new URL("https://demo.perfectomobile.com/nexperience/perfectomobile/wd/hub") , capabilities);
		} catch (Exception e) {
			String ErrToRep = e.getMessage().substring(0,e.getMessage().indexOf("Command duration")-1);
			System.out.println(ErrToRep);
			return (null);



		}
		return webdriver;

	}

	 
	 
	public static AppiumDriver getAppiumDriver(String deviceId,String app,String platform,String Cloud,String user,String password,String appLocationToInstall)   {

		AppiumDriver webdriver= null;


		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);

		if (platform.equalsIgnoreCase("ios"))
		{
			capabilities.setCapability("bundleId", app);
			capabilities.setCapability("automationName", "appium");


		}else
		{
			capabilities.setCapability("app-activity",app);
			capabilities.setCapability("appPackage",app);

		}

		if (appLocationToInstall!= null)
		{
			capabilities.setCapability("app",appLocationToInstall );

		}
		capabilities.setCapability("user", "uzie@perfectomobile.com");
		capabilities.setCapability("password", password);
		capabilities.setCapability("deviceName",  deviceId);
		//capabilities.setCapability("platformName",  platform);


		//capabilities.setCapability("takesScreenShot", false);
		//capabilities.setCapability("automationName", "PerfectoMobile");
		try {
			webdriver = new AndroidDriver(new URL(Cloud+"/nexperience/perfectomobile/wd/hub") , capabilities);
		} catch (Exception e) {
			String ErrToRep = e.getMessage().substring(0,e.getMessage().indexOf("Command duration")-1);
			System.out.println(ErrToRep);
			return (null);


		}

		return webdriver;

	}

	public static String getScreenShot(RemoteWebDriver driver,String name,String deviceID )
	{
		String screenShotName = SCREENSHOTS_LIB+name+"_"+deviceID+".png";
		driver   = (RemoteWebDriver) new Augmenter().augment( driver );
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenShotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screenShotName;
	}

	public static void startApp(String appName,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", appName);
		d.executeScript("mobile:application:open", params);
	}


	public static void stoptApp(String appName,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", appName);
		d.executeScript("mobile:application:close", params);
	}

	public static void setLocation(String address,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("address", address);
		d.executeScript("mobile:location:set", params);
	}
	public static void setLocationCoordinates(String latlong,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("coordinates", latlong);
		d.executeScript("mobile:location:set", params);
	}

	public static void pressKey(String key,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("keySequence", key);
		d.executeScript("mobile:presskey:", params);
	}

	public static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}
	public static void swipe(String start,String end,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("start", start);  //50%,50%
		params.put("end", end);  //50%,50%

		d.executeScript("mobile:touch:swipe", params);

	}



	public static List <String> getDevieList()
	{
		List<String> devices = new ArrayList();
		System.out.println("EXECUET TEST BUILD THE LIST FROM THE FILE v2");
		BufferedReader br;
		try {
			File f = new File("..\\..\\..\\testConfigFiles\\config1.txt");

			br = new BufferedReader(new FileReader(f));
			String line = null;  
			while ((line = br.readLine()) != null)  
			{  
				line = line.trim();

				if (line.length() >2)
				{
					String cleanC = line.substring(1,2);
					line = line.replace(cleanC, "");
				}

				if (line.startsWith("device"))
				{ 
					String id = line.substring(7);
					System.out.println("ID >>"+id);
					devices.add(id);
				} 				
			} 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return devices;

	}

	public static List <PerfectoTestParams> getVSOExecParam()
	{
		List<PerfectoTestParams> params = new ArrayList();
		List<String> devices = new ArrayList();
		System.out.println("EXECUET TEST BUILD THE LIST FROM THE FILE v1");
		BufferedReader br;
		try {
			String current = new java.io.File( "." ).getCanonicalPath();
        		System.out.println("Current dir:"+current);
        		System.out.println("file:"+".."+File.separator +".."+File.separator +"config1.txt");
			// on OS the file will be on folder app on Win two so i check if file exist 
	
			
			
			File f = new File(".."+File.separator +"config1.txt");
		//	File f1 = new File(".."+File.separator +".."+File.separator +"config1.txt");

		//	if(f.exists() && !f.isDirectory()) { 
    	 	//	 	f = f1;
		//	}
			
			//File f = new File("C:\\aaa\\MS\\BuildAgent\\_work\\testConfigFiles\\config1.txt");
			br = new BufferedReader(new FileReader(f));
			String line = null;  
			String platform = "Android"; // Android or IOS
			String PerfectoRepKeyForAll = "";
			// first loop read all the parameters and create a device List 
			boolean first=true;
			boolean clean=true;

			while ((line = br.readLine()) != null)  
			{  
				System.out.println("Line:"+line);
				if (first)
				{
				 	if (line.startsWith("JS"))
					{ 
						// from JS no need to clean
						clean = false;
					} 
					 line = line.substring(2);
					first=false;
				}
				line = line.trim();

				if (line.length() >2 && clean)
				{
					String cleanC = line.substring(1,2);
					line = line.replace(cleanC, "");
				}
				System.out.println("afterclean:"+line);

				if (line.startsWith("device"))
				{ 
					String id = line.substring(7);
					System.out.println("ID >>"+id);
					devices.add(id);
				} 
				if (line.startsWith("Perfecto Repository"))
				{ 
					PerfectoRepKeyForAll = line.substring(line.indexOf("=")+1);
					System.out.println("RepD >>"+ PerfectoRepKeyForAll);
				} 

			} 	
			if (PerfectoRepKeyForAll.toLowerCase().contains(".apk"))
			{
				 platform = "Android";
			}
			else
			{
				 platform = "ios";
			}

			// LOOP II - go over all the devices and create a parameters object to the test 
			for (String device : devices) {
				PerfectoTestParams p = new PerfectoTestParams(device, PerfectoRepKeyForAll, platform,"com.starwood.spg");
				params.add(p);
 			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return params;

	}
	public static void downloadReport(RemoteWebDriver driver, String type, String fileName) throws IOException {
		try { 
			String command = "mobile:report:download"; 
			Map<String, Object> params = new HashMap(); 
			params.put("type", "html"); 
			String report = (String)driver.executeScript(command, params); 
			File reportFile = new File(getVSOReportLib(fileName) ); 
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile)); 
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report); 
			output.write(reportBytes); output.close(); 
		} catch (Exception ex) { 
			System.out.println("Got exception " + ex); }
	}

	public static String getVSOReportLib(String repID) {
		String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
			System.out.println("Current dir:"+current);
			return current+File.separator+"Report"+"/rep_"+repID+".html";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return File.separator;
	}
	
	public static String getReprtName(String repID,boolean withPath) {
		if (withPath)
		{
			return REPORT_LIB+"/rep_"+repID+".html";
		}
		else
		{
			return  "/rep_"+repID+".html";
		}

	}
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
