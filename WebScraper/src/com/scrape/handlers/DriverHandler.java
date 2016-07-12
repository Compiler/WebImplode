package com.scrape.handlers;

import java.util.ArrayList;

import com.scrape.scraper.MyDriver;
import com.scrape.scraper.MyDriver.DriverState;

public class DriverHandler extends Thread {

	private ArrayList<MyDriver> drivers = new ArrayList<MyDriver>();
	private MyDriver currentDriver;
	//Try and just do two by checking index
	public DriverHandler(MyDriver driver, ArrayList<MyDriver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void run() {
		int i = 0;
		if (i < drivers.size() - 1) {
			DriverState tmpState = drivers.get(i).getState();

			if (drivers.get(i + 1).getState() == DriverState.At_Rest) {

			}
		}

	}

}
