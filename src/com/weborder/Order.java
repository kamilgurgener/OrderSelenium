package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {

	public String selectCard(int randomCardAsInt) {
		String[] card = { "#ctl00_MainContent_fmwOrder_cardList_0", "#ctl00_MainContent_fmwOrder_cardList_1",
				"#ctl00_MainContent_fmwOrder_cardList_2" };

		return card[randomCardAsInt];
	}

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"/Users/mac/Documents/selenium dependencies/drivers/chromedriver");

		WebDriver driver = new ChromeDriver();
		driver.get(" http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx ");

		driver.findElement(By.cssSelector("#ctl00_MainContent_username")).sendKeys("Tester");

		driver.findElement(By.cssSelector("#ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.cssSelector("#ctl00_MainContent_login_button")).click();
		driver.findElement(By.cssSelector("#ctl00_menu > li:nth-child(3) > a")).click();

		Random r = new Random();
		int random = r.nextInt(100);
		String str = "" + random;

		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(str);

		String[] middleName = { "Olsen", "Kamil", "Yusuf", "Osman", "Kemal", "Hamit", "Hasan", "Oguzhan", "Omer",
				"Mehmet" };

		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtName"))
				.sendKeys("John " + middleName[r.nextInt(middleName.length)] + " Adam");
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("3 Timberwood Dr.");
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Goffstown");
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("NH");

		String zipCode = "";
		for (int i = 0; i < 5; i++) {
			zipCode += "" + r.nextInt(9);

		}
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipCode);

		Order newCard = new Order();
		int cardType = r.nextInt(3);
		String creditCard = newCard.selectCard(cardType);

		driver.findElement(By.cssSelector(creditCard)).click();

		String card = "";

		if (cardType == 0) {
			card = "4";
			for (int i = 0; i < 15; i++) {
				card += "" + r.nextInt(9);
			}
		} else if (cardType == 1) {
			card = "5";
			for (int i = 0; i < 15; i++) {
				card += "" + r.nextInt(9);
			}
		} else if (cardType == 2) {
			card = "3";
			for (int i = 0; i < 14; i++) {
				card += "" + r.nextInt(9);
			}
		}

		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(card);
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("01/08");
		driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_InsertButton")).click();

		String expected = "New order has been successfully added.";
		String text = driver.findElement(By.tagName("body")).getText();
		if (text.contains(expected)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
			System.out.println("Expected:\t" + expected);
		}
	}

}
