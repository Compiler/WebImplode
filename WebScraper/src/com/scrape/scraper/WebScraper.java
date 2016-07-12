package com.scrape.scraper;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.scrape.tests.CartManaging;
import com.scrape.tests.SignIn;

/**
 * 
 * Web Scraper to find key words, and research to find similar products
 * 
 * @author Luke Roche
 *
 */
public class WebScraper {

	/** Value - {@value}, key for getting to main site */
	public static final String DIR_MAIN = "https://www.amazon.com";

	/** Value - {@value}, key for getting search bar element by ID */
	public static final String SEARCH_NAV = "twotabsearchtextbox";

	/** Value - {@value}, key for getting product title of any product */
	public static final String PRODUCT_ID = "productTitle";
	// Chrome Driver
	public WebDriver driver;

	// For sign in operations
	private SignIn loginTool;

	// For all Cart related tests
	private CartManaging cart;

	// Amount of drivers working
	private int driverCount;

	// Used for storing all drivers
	private ArrayList<MyDriver> drivers = new ArrayList<MyDriver>();

	public WebScraper(int size) {
		this.driverCount = size;

	}

	/**
	 * Feeds driver to all supporting classes
	 */
	@Before
	public void initialize() {
		// init
		for (int i = 0; i < driverCount; i++) {
			drivers.add(new MyDriver("Driver " + i));
		}
		loginTool = new SignIn();
		cart = new CartManaging();

	}

	/**
	 * Opens web site to specified URL
	 * 
	 * @param URL
	 *            Path for driver
	 */
	@Test
	public void openTestSite(String URLPath) {
		// tmp directory
		for (int i = 0; i < drivers.size(); i++) {
			drivers.get(i).navigate().to(URLPath);
			System.out.println("ttrueee");
		}
	}

	/**
	 * Opens web site to URl for multiple drivers
	 * 
	 * @param URL
	 *            Path for driver
	 */
	@Test
	public void openTestSite(ArrayList<String> URLs) {
		// tmp directory
		if (URLs.size() == drivers.size()) {
			for (int i = 0; i < drivers.size(); i++) {
				// Store urls

			}
		}
	}

	public SignIn getLoginTool() {
		return this.loginTool;
	}

	public CartManaging getCartManager() {
		return this.cart;
	}
	
	public ArrayList<MyDriver> getDrivers(){ return this.drivers; }

}
