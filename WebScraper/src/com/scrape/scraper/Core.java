package com.scrape.scraper;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Core {

	public static ChromeDriver d;
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("-incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		for(int i = 0; i < 70; i++){
			d = new ChromeDriver(capabilities);
			
			d.navigate().to("url");
			Thread.sleep(80000);
			d.close();
		}
		
		WebScraper scraper = new WebScraper(2);
		scraper.initialize();
		//scraper.openTestSite("https://www.amazon.com/gp/product/B00CXI1EI4/");

		DriverThread threads = 
				new DriverThread(new KeyWordScrape(), scraper.getCartManager(), scraper.getLoginTool(), "driver", scraper.getDrivers());
		threads.setStartingURL("https://www.amazon.com/gp/product/B00CXI1EI4/");
		threads.start();
		

	}

}
