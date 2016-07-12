package com.scrape.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.util.concurrent.Service.State;
import com.scrape.scraper.MyDriver;
import com.scrape.scraper.MyDriver.DriverState;

public class CartManaging {

	private MyDriver driver;
	private WebElement referenceElement;

	/**
	 * Adds item to cart
	 */
	public void addProductToCart() {

		referenceElement = driver.findElement(By.id("add-to-cart-button"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Checks for any offers at adding
	 */
	public void checkForDeals() {
		try {
			referenceElement = driver.findElement(By.id("sbbop-no-button"));
			referenceElement.click();
		} catch (Exception e) {
			System.out.println("No offer");
		}
		driver.incrementIndex();

	}

	/**
	 * Goes into cart to checkout
	 */
	public void proceedToCheckout() {
		referenceElement = driver.findElement(By.className("a-button-inner"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Checks out the cart
	 */
	public void checkoutCart() {
		referenceElement = driver.findElement(By.id("sc-buy-box-ptc-button"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Checks first address in options
	 */
	public void checkForMultipleAddresses() {
		referenceElement = driver.findElement(By.className("a-button-inner"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Selects first shipping
	 */
	public void shippingElement() {
		referenceElement = driver.findElement(By.className("a-button-inner"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Selects first payment type
	 */
	public void selectPayment() {
		referenceElement = driver.findElement(By.id("continue-top"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Skips any offers of faster shipping
	 */
	public void checkAndSkipOffers() {
		try {
			referenceElement = driver.findElement(By.className("a-link-normal"));
			referenceElement.click();
		} catch (Exception e) {
			System.out.println("No offers");
		}
		driver.incrementIndex();
	}

	/**
	 * Selects `place your order`
	 */
	public void placeOrder() {
		referenceElement = driver.findElement(By.name("placeYourOrder1"));
		referenceElement.click();
		driver.incrementIndex();
	}

	/**
	 * Computes all actions
	 */
	public void executeCart() {
			addProductToCart();
			checkForDeals();
			proceedToCheckout();
			checkoutCart();
			checkForMultipleAddresses();
			shippingElement();
			selectPayment();
			checkAndSkipOffers();
			placeOrder();
	}
	
	/**
	 * Gives driver to control
	 * @param driver
	 */
	public void feedCurrentDriver(MyDriver driver){
		this.driver = driver;
	}

}
