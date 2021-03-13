package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil elementutil;
	private By ProductHeader=By.cssSelector("div#content h1");
	private By ProductImages=By.cssSelector("ul.thumbnails li img");
	private By ProductMetadata=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By ProductPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By Quantity=By.id("input-quantity");
	private By AddtoCart=By.id("button-cart");
	private By SuccssMsg=By.xpath("//div[@class='alert alert-success alert-dismissible']");
	
	
	public ProductInfoPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	
	public String getProductHeadreText()
	{
		return elementutil.doGetElementText(ProductHeader);
	}
	
	public int getProductImagescount()
	{
		return elementutil.getElements(ProductImages).size();
	}
	
	public Map<String, String> getProductInformation()
	{
		Map<String,String> ProductinfoMap=new HashMap<String,String>();
		
		ProductinfoMap.put("name", getProductHeadreText());
		
		List<WebElement> productMetaDataList = elementutil.getElements(ProductMetadata);
		
		for(WebElement e:productMetaDataList)
		{
			String Meta[] = e.getText().split(":");
			String metaKey=Meta[0].trim();
			String metaValue=Meta[1].trim();
			ProductinfoMap.put(metaKey, metaValue);
		}
		
		//Price
		
		List<WebElement> productPriceList = elementutil.getElements(ProductPriceData);
		ProductinfoMap.put("price", productPriceList.get(0).getText().trim());
		ProductinfoMap.put("exTaxPrice", productPriceList.get(1).getText().split(":")[1].trim());
		
		return ProductinfoMap;
		
	}
	
	public void selectQuantity(String qty)
	{
		elementutil.getElement(Quantity).clear();
		elementutil.doSendKeys(Quantity, qty);
	}
	
	public void addtoCart()
	{
		elementutil.doClick(AddtoCart);
	}
	
	public String getSuccsessMsg()
	{
		return elementutil.waitForPresenceOfElement(SuccssMsg, 15).getText();
		
	}
	

}
