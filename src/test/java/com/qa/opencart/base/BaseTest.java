package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.Loginpage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	DriverFactory df;
	public Properties prop;
	private WebDriver driver;
	public Loginpage loginpage;    //--making it as public to available in other packages
	public AccountsPage accpage;
	public SearchResultsPage searchresultpage;
	public ProductInfoPage ProductinfoPage;
	public RegistrationPage registrationPage;
	
	@BeforeTest
	public void setUp()
	{    
		df=new DriverFactory();
		prop=df.init_prop();
		driver=df.init_driver(prop);
		loginpage=new Loginpage(driver);
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
