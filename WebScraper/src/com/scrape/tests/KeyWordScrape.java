package com.scrape.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.scrape.scraper.MyDriver;
import com.scrape.scraper.WebScraper;

public class KeyWordScrape {

	// Grabs word by word for future looping of keywords
	private ArrayList<String> productTitleWords = new ArrayList<String>();

	// ArrayList for changing amount of keywords
	private ArrayList<String> keyWords = new ArrayList<String>();
	
	private ArrayList<String> allResultTitles= new ArrayList<String>();

	// Input type accepted by driver for search
	private CharSequence finalSearchInput;
	private WebDriver driver;
	private WebElement referenceElement;
	
	private String fullName = "";

	public KeyWordScrape(ChromeDriver chromeDriver){
		this.driver = chromeDriver;
		
	}
	
	public KeyWordScrape(){
		
	}
	
	public void feedDriver(MyDriver drive){
		this.driver = drive;
	}
	
	
	/**
	 * Gets text from product title and splits into words
	 * stored into the `ArrayList` productTitleWords
	 */
	@Test
	public void getTextAndSplit(){
		//Grab text of product title
		fullName = driver.findElement(By.id(WebScraper.PRODUCT_ID)).getText();
		
		
		//Splits all to remove spaces
		String[] textDelimit = fullName.split(" ");
		for(int i = 0; i < textDelimit.length; i++){
			//removes all symbols to check for dictionary match
			textDelimit[i] = textDelimit[i].replaceAll("[^\\p{L}\\p{Nd}]+", "");
			//Adds cleaned word to list
			productTitleWords.add(textDelimit[i]);
		}
		
		//Prints all cleaned words
		for(int i = 0; i < productTitleWords.size(); i++){
			System.out.println(productTitleWords.get(i));
		}
		
	}
	
	/**
	 * Loops through a dictionary and matches product title words
	 * from `productTitleWords` `ArrayList<String>` and looks for matches,
	 * then stores matches in `keyWords` `ArrayList<String>`
	 * 
	 * After looping, stores all keywords in `CharSequence` `finalSearchInput`
	 * to be searched for
	 */
	@Test
	public void sortForKeyWords(){
		try {
			//Gives scanner access to dictionary
			Scanner dictScan = new Scanner(new File("src/dictionary.txt"));
			
			//loops while there are still lines in dictionary
			while(dictScan.hasNextLine()){
				//assigns tmp to avoid line skip
				String tmp = dictScan.next();
				
				//Checks for matches
				for(int i = 0; i < productTitleWords.size(); i++){
					if(dictScan.hasNext())
					if(productTitleWords.get(i).equalsIgnoreCase(tmp)){
						//If matches with dictionary, populate ArrayList<String>
						keyWords.add(productTitleWords.get(i));
					}
					
				}
			}
			//close dictScan to avoid exception
			dictScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Temporary String variable to be converted to `CharSequence`
		String tmpFinal = "";
		for(int i = 0; i < keyWords.size(); i++){
			tmpFinal += keyWords.get(i) + " ";
		}
		
		//Converts to necessary type
		finalSearchInput = tmpFinal.toString();
			
	}
	
	/**
	 * 
	 * searches for search bar and sends
	 * the keywords into it then clicks sumbit
	 * to search
	 * 
	 */
	@Test
	public void searchElements(){
		//Finds search bar
		WebElement element = driver.findElement(By.id(WebScraper.SEARCH_NAV));
		//Clicks search bar
		element.click();
		//Sends `keyWords` into search bar
		element.sendKeys(finalSearchInput);
		//Hits enter to search
		element.submit();
		
		
	}
	
	/**
	 * Searches through suggestions
	 */
	@Test
	public void searchAndChoose(){
		WebElement element;
		for(int i = 0; i < 10; i++){
			element = driver.findElement(By.xpath("//li[@id='result_" + i + "']//h2"));
			String tmpTitle = element.getAttribute("data-attribute");
			if(fullName.equals(tmpTitle)){
				System.out.println(""+tmpTitle);
				element.click();
				break;
			}else
				System.out.println("" + tmpTitle + " not foud");
		}
	}
	
	public void execute(){
		getTextAndSplit();
		sortForKeyWords();
		searchElements();
		searchAndChoose();
	}
	

}
