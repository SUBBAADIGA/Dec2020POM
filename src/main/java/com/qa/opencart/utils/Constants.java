package com.qa.opencart.utils;

import java.util.ArrayList;

public class Constants {
	
	public final static String LOGIN_PAGE_TITLE="Account Login";
	
	public final static String ACCOUNT_PAGE_TITLE="My Account";
	
	public final static int ACCOUNT_PAGE_SECTION_COUNT=5;
	
	public final static int IMAGES_COUNT=5;
	
	public final static String ACCOUNT_CREATION_SUCCCESS_MESSAGE="Your Account Has Been Created!";
	
	public static final String REGISTER_SHEET_NAME = "register";
	
	
	public final static ArrayList<String> expectedAccSecList()
	{
		ArrayList<String> expEcList = new ArrayList<String>();
		expEcList.add("My Account");
		expEcList.add("My Orders");
		expEcList.add("My Affiliate Account");
		expEcList.add("Newsletter");
		return expEcList;
	
	}

}
