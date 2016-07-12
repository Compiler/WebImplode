package com.scrape.tests;

import java.util.ArrayList;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.scrape.scraper.MyDriver;
import com.scrape.scraper.MyDriver.DriverState;

public class SignIn {

	//reference driver
	private MyDriver driver;

	/**
	 * Finds never-changing sign in position id
	 */
	@Test
	public void clickSignIn() {
		WebElement element = driver.findElement(By.id("nav-link-yourAccount"));
		element.click();
		driver.incrementIndex();
	}

	/**
	 * finds never-changing id for email and password input
	 */
	@Test
	public void signIn() {
		WebElement element = driver.findElement(By.id("ap_email"));

		// To be replaced by populated database
		element.sendKeys("ljuektes@gmail.com".toString());

		element = driver.findElement(By.id("ap_password"));
		// To be replaced by populated database
		element.sendKeys("Luke1998".toString());
		element.submit();
		driver.incrementIndex();
	}

	/**
	 * Executes all sign in operations
	 */
	public void executeSignInModule() {
		clickSignIn();
		signIn();
		// Switches drivers
		driver.setState(DriverState.Logged_In);
	}

	
	/**
	 * Gives driver to control
	 * @param driver
	 */
	public void feedCurrentDriver(MyDriver driver){
		this.driver = driver;
	}

}
