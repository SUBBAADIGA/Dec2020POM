package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void ForgotPasswordTest()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExists());
	}
	
	@Test(priority = 3)
	public void doLoginTest()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getHomePageTitle(), Constants.ACCOUNT_PAGE_TITLE);
		
	}

}
