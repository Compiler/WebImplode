package com.scrape.scraper;

import java.util.ArrayList;

import com.scrape.tests.CartManaging;
import com.scrape.tests.KeyWordScrape;
import com.scrape.tests.SignIn;

public class DriverThread extends Thread {

	private SignIn signInOperator;
	private CartManaging cartOperator;
	private boolean isLocked;
	private MyDriver driver;
	private ArrayList<MyDriver> driverList;
	private String URLPath = "";
	private ArrayList<String> driverHandles = new ArrayList<String>();
	private KeyWordScrape scraper;
	public DriverThread(KeyWordScrape scrape, CartManaging cart, SignIn exec, String threadName, ArrayList<MyDriver> driverList) {
		super(threadName);
		this.scraper = scrape;
		this.signInOperator = exec;
		this.driverList = driverList;
		this.cartOperator = cart;
	}

	@Override
	public void start() {
		super.start();

		for(int i = 0; i < driverList.size(); i++){
			driverList.get(i).navigate().to(URLPath);
			scraper.feedDriver(driverList.get(i));
			scraper.execute();
		}
		for (int i = 0; i < driverList.size(); i++) {
			signInOperator.feedCurrentDriver(driverList.get(i));
			signInOperator.executeSignInModule();
			
			driverHandles.add(driverList.get(i).getWindowHandle());
		}
		
		for(int i = 0; i< driverList.size(); i++){
			cartOperator.feedCurrentDriver(driverList.get(i));
			cartOperator.executeCart();
		}
		driver = driverList.get(0);
	}

	private int methodExecuteCount = 0;

	@Override
	public void run() {

		for (int i = 0; i < driverList.size(); i++) {
			synchornizeOperations(i);

			try{
			checkIndexAndExecute(driver.getIndex());
		
			}catch(Exception e){
				System.out.println("error");
			}
		}
	}

	public void checkIndexAndExecute(int i) {
		switch (i) {

		case 0: {

			signInOperator.feedCurrentDriver(driver);
			signInOperator.clickSignIn();
			System.out.println(driver.getIndex() + " is clicking sign in");
			break;
		}
		case 1: {
			signInOperator.feedCurrentDriver(driver);
			signInOperator.signIn();
			System.out.println(driver.getIndex() + " is typing login info");
			break;
		}
		case 2: {
			cartOperator.feedCurrentDriver(driver);
			cartOperator.addProductToCart();
			System.out.println(driver.getIndex() + " adding to cart");
			break;
		}
		case 3: {
			cartOperator.checkForDeals();
			break;
		}

		}
	}

	public void setStartingURL(String URLPath) {
		this.URLPath = URLPath;
	}

	public void synchornizeOperations(int index) {
		try{
		if (driver.getIndex() <= driverList.get(index).getIndex()) {
			signInOperator.feedCurrentDriver(driver);
		} else {
			driver = driverList.get(index);
			driver.switchTo().window(driverList.get(index).getWindowHandle());
			signInOperator.feedCurrentDriver(driver);
		}
		}catch(Exception e){
			
		}
	}

}
