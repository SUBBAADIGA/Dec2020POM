package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Error;

public class ProductInfoTest extends BaseTest {
	SoftAssert softassert=new SoftAssert();
	
	@BeforeClass
	public void AccSetup()
	{
		accpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		searchresultpage=accpage.doSearch("MacBook");
		ProductinfoPage=searchresultpage.SelectProductFromResults("MacBook Pro");
	}
	@Test(priority = 1)
	public void selectProductTest()
	{
		String ActualHeader=ProductinfoPage.getProductHeadreText();
		Assert.assertEquals(ActualHeader, "MacBook Pro");
	}
	
	@Test(priority = 2)
	public void	productinfoDataTest()
	{
		Map<String, String> actProductInfoMap = ProductinfoPage.getProductInformation();
		
		softassert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softassert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softassert.assertEquals(actProductInfoMap.get("Reward Points"), "800");
		softassert.assertEquals(actProductInfoMap.get("Availability"), "Out Of Stock");
		softassert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softassert.assertEquals(actProductInfoMap.get("exTaxPrice"), "$2,000.00");
		softassert.assertAll();
	}
	
	@Test(priority = 3)
	public void ImageTest()
	{
		Assert.assertEquals(ProductinfoPage.getProductImagescount(), 4);
	}
	
	@Test(priority = 4)
	public void SelectQuantityTest()
	{
		ProductinfoPage.selectQuantity("2");
	}
	@Test(priority = 5)
	public void AddtoCarttest()
	{
		ProductinfoPage.addtoCart();
		String Actual=ProductinfoPage.getSuccsessMsg();
		Assert.assertTrue(Actual.contains("Success: You have added MacBook Pro to your shopping cart!"));
		
	}
	

}
