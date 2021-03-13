package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private ElementUtil elementutil;
	private WebDriver driver;
	
	private By Logo=By.cssSelector("#logo");
	private By AccHeaders=By.xpath("//div[@id='content']/h2");
	private By SearchField=By.xpath("//input[@name='search']");
	private By SearchButton=By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}
	
	public String getHomePageTitle()
	{
		return elementutil.waitForTitleIs(10, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	public int getAccountPageHeaderCount()
	{
		return elementutil.getElements(AccHeaders).size();
	}
	
	public List<String> getAccountPageHeaderList()
	{
		List<WebElement> accList = elementutil.getElements(AccHeaders);
	     List<String> accSecList=new ArrayList<String>();
		
		for(WebElement e:accList)
		{
			accSecList.add(e.getText());
		}
		return accSecList;
	}
	
	public SearchResultsPage doSearch(String productName)
	{
		elementutil.doSendKeys(SearchField, productName);
		elementutil.doClick(SearchButton);
		return new SearchResultsPage(driver);
	}
	public boolean isLogoExists()
	{
		return elementutil.doIsDisplayed(Logo);
	}
	
	
	

}
