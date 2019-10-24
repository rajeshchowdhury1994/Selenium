package com.stock.nasdaq;

import java.io.IOException;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Reusables.Generics;


public class NasdaqTest extends Nasdaq {

	@Test
	
	public void testcase_1() throws IOException
	
	{
	
		typeText(By.xpath("//*[@id=\"stock-symbol\"]"),"element","aapl");
		clickButton(By.xpath("//*[@id=\"stock-submit\"]/span"), "search");
		//getText(By.xpath("//div[@class='last-sale padded-bottom displayIB paddingR5p']"),"");
		//getText(By.xpath("//span[@class='net-change green']"),"");
		//getText(By.xpath("//span[@class='pct-change green']"), "");
		clickButton(By.xpath("//a[contains(text(),'AAPL Dividend History')]"),"aapl dividend history");
		
		
		
     for (i=1;i<=10;i++) {
			DateXpath="//*[@id='table-saw']/tbody/tr["+i+"]/td[1]/span/span/span[2]";
			getElementText(By.xpath(DateXpath), "Date");
		}
		for (j=1;j<=10;j++) {
			CashXpath="//*[@id='table-saw']/tbody/tr["+j+"]/td[2]/span";
			getElementText(By.xpath(CashXpath),"Cash");
		}
		
		for(k=1;k<=10;k++) {
			DeclarationDateXpath="//*[@id='table-saw']/tbody/tr["+k+"]/td[3]/span/span/span[2]";
			getElementText(By.xpath(DeclarationDateXpath), "Declaration");
		}
		
		for(l=1;l<=10;l++) {
			RecordDateXpath="//*[@id='table-saw']/tbody/tr["+l+"]/td[4]/span/span/span[2]";
		getElementText(By.xpath(RecordDateXpath), "Record");
		}
	
		
		for(m=1;m<=10;m++) {
			PaymentXpath="//*[@id='table-saw']/tbody/tr["+m+"]/td[5]/span/span/span[2]";
		getElementText(By.xpath(PaymentXpath), "Record");
		}
		
	
	}


		 
}		 
		
	

	

	
