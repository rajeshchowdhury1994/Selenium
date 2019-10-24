package com.stock.nasdaq;

import org.openqa.selenium.By;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Nasdaq extends ProjectBase {
	int i=1;
	int j=1;
	int k=1;
	int l=1;
	int m=1;

	public String DateXpath="//*[@id='table-saw']/tbody/tr["+i+"]/td[1]/span/span/span[2]";
	public String CashXpath="//*[@id='table-saw']/tbody/tr["+j+"]/td[2]/span";
	public String DeclarationDateXpath="//*[@id='table-saw']/tbody/tr["+k+"]/td[3]/span/span/span[2]";
	public String RecordDateXpath="//*[@id='table-saw']/tbody/tr["+l+"]/td[4]/span/span/span[2]";
	public String PaymentXpath="//*[@id='table-saw']/tbody/tr["+m+"]/td[5]/span/span/span[2]";
    
	public Nasdaq()
	{
		
	}

	public void DateXpath(String text)
	{
		getElementText(By.xpath(DateXpath), text);
	}
	public void CashXpath(String text)
	{
		getElementText(By.xpath(CashXpath),text);
	}
	public void DeclarationDateXpath(String text)
	{
		getElementText(By.xpath(DeclarationDateXpath),text);
	}
	public void RecordDateXpath(String text)
	{
		getElementText(By.xpath(RecordDateXpath),text);
	}
	 
	public void PaymentXpath(String text)
	{
		getElementText(By.xpath(PaymentXpath),text);
	}
}