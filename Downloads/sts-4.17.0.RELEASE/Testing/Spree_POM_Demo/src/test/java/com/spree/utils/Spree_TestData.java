package com.spree.utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.IllegalFormatException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class Spree_TestData {
	
	@DataProvider(name = "Login")
	public Object[][] Login(){
		return new Object[][] {
			{"andrew@spree.com", "password"},
		};
	}
	
	@DataProvider(name = "addAddress")
	public Object[][] dataForAddingUsers() {
		return new Object[][] {
			{"newAddy", "andrew", "velasquez", "123 Main st", "Washington", "District of Columbia", "20010", "123456789"}
		};
	}
	


}