package com.test.practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNgParallel {
	@Test(groups={"regression"})
	@Parameters("Browser")
	public void testBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			WebDriver driver = new ChromeDriver();driver.get("https://www.google.com");
		}
	}
}
