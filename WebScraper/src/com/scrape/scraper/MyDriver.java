package com.scrape.scraper;

import org.openqa.selenium.chrome.ChromeDriver;

public class MyDriver extends ChromeDriver {

	public enum DriverState {
		At_Rest, Logged_In, Checking_Out, Selected_Payment, Completed

	}

	
	// Used for comparing in stack
	private int index = 0;
	// Controller
	private DriverState currentState = DriverState.At_Rest;

	private String nameOfDriver;
	public MyDriver(String name){
		super();
		this.nameOfDriver = name;
	}
	
	// Return state
	public DriverState getState() {
		return currentState;
	}

	/**
	 * Sets state status to condition
	 * 
	 * @param state
	 */
	public void setState(DriverState state) {
		currentState = state;

	}

	public int getIndex() { return this.index; }
	public void incrementIndex(){
		index += 1;
	}
	public String getName(){
		return nameOfDriver;
		
	}
}
