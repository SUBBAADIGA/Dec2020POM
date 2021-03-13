package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	ElementUtil elementutil;
	private WebDriver driver;
	
	By SearchResult=By.cssSelector("div.product-layout div.product-thumb");
	By ResultItems=By.cssSelector("div.product-thumb h4 a");
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	
	public ProductInfoPage SelectProductFromResults(String product)
	{
		List<WebElement> Reultsitemlist = elementutil.getElements(ResultItems);
		for(WebElement e:Reultsitemlist)
		{
			if(e.getText().equals(product))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	public int getproductResultsCount()
	{
		return elementutil.getElements(SearchResult).size();
	}

}
