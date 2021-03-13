package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	public static String highlight;
	private OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	/**
	 * This method is used to initialize the driver based on the given browser value.
	 * @param Browser
	 * @return
	 */
	
	public WebDriver init_driver(Properties prop)
	{
		String browserName=prop.getProperty("browser").trim();
		highlight=prop.getProperty("heighlight").trim();
		optionsManager=new OptionsManager(prop);
		switch (browserName) {
		case "chrome":
			
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		//	driver=new ChromeDriver(optionsManager.getChromeOptions());
			break;
			
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		//	driver=new FirefoxDriver(optionsManager.getFirefoxOptions());		
			break;
			
		case "Edge":
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		//	driver=new EdgeDriver(optionsManager.getEdgeOptions());
			break;

		default:	
			System.err.println("Browser is not found"+browserName);
			break;
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	
	public static synchronized WebDriver getDriver()
	{
		return tlDriver.get();
	}
	
	public Properties init_prop()
	{
		prop=new Properties();
		FileInputStream ip=null;
		String env = System.getProperty("env");
		if(env==null)
		{
			 try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
			switch (env) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
				
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;

			default:
				break;
			}
		}
			
		catch(FileNotFoundException e)
			{
			 e.printStackTrace();
			}
			
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		return prop;
}
	
	public static String getScreenshot()
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile=new File(src);
		
		String path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
}
