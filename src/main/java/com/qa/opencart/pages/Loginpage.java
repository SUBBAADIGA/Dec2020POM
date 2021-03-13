package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class Loginpage {
	private WebDriver driver;

	private ElementUtil elementutil;
	//Page Objects--By locators
	private By username=By.id("input-email");
	private By password=By.id("input-password");
	private By loginButton=By.xpath("//input[@value='Login']");
	private By forgotpassword=By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By Register=By.xpath("//div[@class='list-group']/a[text()='Register']");
	
	//constructor
	public Loginpage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	
	//page actions
	
	public String getLoginPageTitle()
	{
		return elementutil.waitForTitleIs(10, Constants.LOGIN_PAGE_TITLE);
	}
	
	public boolean isForgotPwdLinkExists()
	{
		return elementutil.doIsDisplayed(forgotpassword);
	}
	
	public AccountsPage doLogin(String un,String pw)
	{
		elementutil.doSendKeys(username, un);
		elementutil.doSendKeys(password, pw);
		elementutil.doClick(loginButton);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage doRegister()
	{
		elementutil.doClick(Register);
		
		return new RegistrationPage(driver);
	}
	

}
