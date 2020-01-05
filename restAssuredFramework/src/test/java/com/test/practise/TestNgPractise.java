package com.test.practise;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgPractise {
	
	@Test(groups={"sanity","regression","positive"})
	public void demo1()
	{
		Reporter.log("Sanity Regression");
	}
	@Test(groups={"smoke","regression"})
	public void demo2()
	{
		Reporter.log("Smoke Regression");
	}
	@Test(groups={"regression"})
	public void demo3()
	{
		Reporter.log("Regression");
	}
}
