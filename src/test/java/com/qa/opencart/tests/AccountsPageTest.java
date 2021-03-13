package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

public class AccountsPageTest extends BaseTest {
	
	SoftAssert softassert=new SoftAssert();
	
	@BeforeClass
	public void AccSetup()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Test(priority = 1,enabled = true)
	public void homePagetTitletest()
	{
		String title = accpage.getHomePageTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test(priority = 2,enabled = true)
	public void accPageLogoTest()
	{
		Assert.assertTrue(accpage.isLogoExists());
	}
	
	@Test(priority = 3,enabled = true)
	public void accountHeaderCountTest()
	{
		int headercount = accpage.getAccountPageHeaderCount();
		Assert.assertEquals(headercount, Constants.ACCOUNT_PAGE_SECTION_COUNT,Error.ACC_PAGE_SECTION_ERROR);
	}
	
	@Test(priority = 4,enabled = true)
	public void accPageSecTest()
	{
		List<String> accList = accpage.getAccountPageHeaderList();
		Assert.assertEquals(accList, Constants.expectedAccSecList());
	}
	
	@Test(priority = 5,enabled = true)
	public void SearchTest()
	{
		searchresultpage=accpage.doSearch("MacBook");
		Assert.assertTrue(searchresultpage.getproductResultsCount()>0,Error.SEARCH_NOT_SUCCESSFULL);
	}
	@Test(priority = 6)
	public void selectProductTest()
	{
		//When First assert fails then next assert will not be checked.>>we can overcome this by softassertion.
		//searchresultpage=accpage.doSearch("MacBook");
		ProductinfoPage=searchresultpage.SelectProductFromResults("MacBook");
		String ActualHeader = ProductinfoPage.getProductHeadreText();
		softassert.assertEquals(ActualHeader, "MacBook",Error.PRODUCT_HEADER_NOT_FOUND);
		softassert.assertEquals(ProductinfoPage.getProductImagescount(), Constants.IMAGES_COUNT);
		softassert.assertAll();
	}
	
	
	
	

}
