package com.scrape.factory;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.scrape.scraper.MyDriver;

public class SwitchCommandFactory {
	
	public static void openNewTab(){
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_CONTROL); 
			r.keyPress(KeyEvent.VK_T); 
			r.keyRelease(KeyEvent.VK_CONTROL); 
			r.keyRelease(KeyEvent.VK_T);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static void switchWindow(MyDriver main, MyDriver newWindow){
		main.switchTo().window(newWindow.getWindowHandle());
	}

}
