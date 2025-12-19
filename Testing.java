package todo_website_testing;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Testing {
	
	/**
	 * This method creates options for a new firefox session, and returns the created driver.
	 * @return The WebDriver created to used for this session.
	 */
	public static WebDriver startSession() {
		//create options for firefox
		FirefoxOptions options = new FirefoxOptions();
		//set up a new driver to open firefox
		WebDriver driver = new FirefoxDriver(options);
		return driver;
	}//startSession
	
	/**
	 * This method takes a given WebDriver and ends its session.
	 * @param driver The WebDriver with a current session open.
	 */
	public static void endSession(WebDriver driver) {
		//end the WebDriver session
		driver.quit();
	}//endSession
	
	/**
	 * This method takes a given WebDriver and opens a web page url given in String format.
	 * @param driver The WebDriver being used for the automation.
	 * @param page The url of the web page to be navigated to in String format.
	 */
	public static void openWebPage(WebDriver driver, String page) {
		//open the page using given url in String format
		driver.get(page);
	}//openWebPage
	
	/**
	 * This method takes a given WebDriver and returns the title of the web page in String format.
	 * @param driver The WebDriver being used for the automation.
	 * @return The title of the found web page in String format.
	 */
	public static String getPageTitle(WebDriver driver) {
		//get the title of the given driver page
		return driver.getTitle();
	}//getPageTitle
	
	/**
	 * This method takes a given WebDriver and returns a List<WebElement> of all tabs.
	 * @param driver The WebDriver being used for the automation.
	 * @return The List<WebElement> of all tabs.
	 */
	public static List<WebElement> getFilterTabList(WebDriver driver){
		//set path of filters
		String elementPath = "html/body/div/div[2]/span";
		//save filters in a List of WebElements
		List<WebElement> tabList = driver.findElements(By.xpath(elementPath));
		return tabList;
	}//getFilterTabList
	
	/**
	 * This method takes a given WebDriver and returns the name of the active tab from a list of all tabs.
	 * @param driver The WebDriver being used for the automation.
	 * @return The name of the active tab in String format. If no active filter is found, ERROR is returned in String format.
	 */
	public static String getActiveFilterTab(WebDriver driver) {
		//call getFilterList to get the filters
		List<WebElement> tabList = getFilterTabList(driver);
		
		//iterators to go through list
		Iterator<WebElement> a1 = tabList.iterator();
		Iterator<WebElement> a2 = tabList.iterator();
		
		//while there are still tabs to check,
		while(a1.hasNext() && a2.hasNext()) {
			//check if the current tab is the active tab
			if(a2.next().getAttribute("class").equals("filter active")) {
				//active filter, return tab name
				return a1.next().getText();
			}//if
			else {
				//else, not the active filter, keep going
				a1.next();
			}//else
		}//while
		
		//didn't find the active filter, return error string
		return "ERROR";
	}//getActiveFilterTab
	
	/**
	 * This method takes a given WebDriver and returns the WebElement of the tab from being searched for.
	 * @param driver The WebDriver being used for the automation.
	 * @param tab The String of the given name of the tab being searched for.
	 * @return The WebElement of the tab. If no matching tab filter is found, a null WebElement is returned.
	 */
	public static WebElement getSpecificFilterTab(WebDriver driver, String tab) {
		//call getFilterList to get the filters
		List<WebElement> tabList = getFilterTabList(driver);
		
		//iterators to go through list
		Iterator<WebElement> a1 = tabList.iterator();
		Iterator<WebElement> a2 = tabList.iterator();
				
		//while there are still tabs to check,
		while(a1.hasNext() && a2.hasNext()) {
			//check if the current tab is matching tab being searched for
			if(a2.next().getAttribute("data-filter").equals(tab)) {
				//matching tab, return tab
				return a1.next();
			}//if
			else {
				//else, not the matching tab filter, keep going
				a1.next();
			}//else
		}//while
				
		//didn't find the active filter, return error
		WebElement error = null;
		return error;
	}//getSpecificFilterTab
	
	/**
	 * This method takes a given WebDriver and given String of an element ID, and finds and returns the matching WebElement.
	 * @param driver The WebDriver being used for automation.
	 * @param elementId The String of the ID being searched for.
	 * @return The WebElement that matches the given ID.
	 */
	public static WebElement setElementById(WebDriver driver, String elementId) {
		return driver.findElement(By.id(elementId));
	}//setElementById
	
	/**
	 * This method takes a given WebDriver and given String of a class name, and finds and returns the matching WebElement.
	 * @param driver The WebDriver being used for automation.
	 * @param elementId The String of the class being searched for.
	 * @return The WebElement that matches the given class.
	 */
	public static WebElement setElementByClass(WebDriver driver, String className) {
		return driver.findElement(By.className(className));
	}//setElementByClass
	
	/**
	 * This method takes a given WebDriver and given String of an element's XPath, and finds and returns the matching WebElement.
	 * @param driver  The WebDriver being used for automation.
	 * @param pathText The String of the XPath being searched for.
	 * @return The WebElement that matches the given XPath.
	 */
	public static WebElement setElementByXPath(WebDriver driver, String pathText) {
		return driver.findElement(By.xpath(pathText));
	}//setElementByXPath
	
	/**
	 * This method takes a given WebElement and clicks on it.
	 * @param givenElement The WebElement being clicked on.
	 */
	public static void clickElement(WebElement givenElement) {
		//click the on the given element
		givenElement.click();
	}//clickElement
	
	/**
	 * This method takes the String name of the current WebElement, and checks if it matches the String elementPath.
	 * @param currentEle The name of the tab in String form.
	 * @param elementPath The name the tab should be in String form.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given names do not match.
	 */
	public static int confirmTabName(String currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.equals(elementPath))) {
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmTabName
	
	/**
	 * This method takes the given WebElement, and compares its class attribute with the given String elementPath.
	 * @param currentEle The given WebElement that will have its class compared.
	 * @param elementPath The given String to compare with.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given names do not match.
	 */
	public static int confirmElementByClass(WebElement currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.getAttribute("class").equals(elementPath))){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmTabName
	
	/**
	 * This method takes the given WebElement, and compares its ID attribute with the given String elementPath.
	 * @param currentEle The given WebElement that will have its ID compared.
	 * @param elementPath The given String to compare with.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given names do not match.
	 */
	public static int confirmElementById(WebElement currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.getAttribute("id").equals(elementPath))){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmElementById
	
	/**
	 * This method takes the String text of the current WebElement, and checks if it matches the String elementPath.
	 * @param currentEle The text in String form.
	 * @param elementPath The text it should match in String form.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given text does not match.
	 */
	public static int confirmMessageText(String currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.equals(elementPath))){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmMessageText
	
	public static void main(String[] args) throws InterruptedException {
		
		//set up a new driver to open up firefox using startSession()
		WebDriver driver = startSession();
		//wait
		Thread.sleep(2000);
		
		//open a specific web page using firefox
		String page = "https://ngo500.github.io/todo_website/";
		//call the openWebPage method
		openWebPage(driver, page);
		//wait
		Thread.sleep(2000);
		
		//start ERROR count
		int errorCtr = 0;
		
		//confirm the website is correct
		//get the title of the page using getPageTitle method
		String title = getPageTitle(driver);
		String matching = "To-Do List Website";
		//print the title
		System.out.println("The title of this page is: " + title + ".");
		//check if the page title is correct
		if(!(title.equals(matching))){
			//the title is incorrect
			errorCtr++;
			System.out.println("ERROR- Wrong Website.");
		}//if
		else {}//else
		
		//confirm default tab shown in "All"
		//call getActiveFilterTab to get the name of the current active filter tab
		String elementPath = "All";
		String currentTab = getActiveFilterTab(driver);
		if(confirmTabName(currentTab, elementPath) > 0) {
			errorCtr++;
			System.out.println("ERROR- Default tab is not correct.");
		}//if
		else {}//else
		
		//check "There are currently no tasks." is shown
		//call setElementById to find the element that displays the empty state
		elementPath = "empty-state";
		WebElement currentEle = setElementByClass(driver, elementPath);
		if(confirmElementByClass(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty state is not shown. " + currentEle.getAttribute("class") + " is shown.");
		}//if
		else {}//else
		
		//call setElementByXPath to find the element that displays the clipboard picture
		elementPath = "/html/body/div/div[3]/div/i";
		currentEle = setElementByXPath(driver, elementPath);
		elementPath = "fas fa-clipboard-list";
		if(confirmElementByClass(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Clipboard art is not shown. " + currentEle.getAttribute("class") + " is shown.");
		}//if
		else {}//else
		
		//call setElementById to find the element that displays the no tasks message
		elementPath = "empty-task-message";
		currentEle = setElementById(driver, elementPath);
		if(confirmElementById(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty tasks message is not shown. " + currentEle.getAttribute("id") + " is shown.");
		}//if
		else {
			//check if the correct message is displayed
			elementPath = "There are currently no tasks.";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
			}//if
			else {}//else
		}//else
		
		//check "0 active items left" is shown
		elementPath = "items-left";
		currentEle = setElementById(driver, elementPath);
		elementPath = "0 active items left";
		if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
			errorCtr++;
			System.out.println("ERROR- 0 active items left message is not shown.");
		}//if
		else {}//else
		
		//click active tab
		elementPath = "active";
		currentEle = getSpecificFilterTab(driver, elementPath);
		elementPath = "Active";
		if(confirmTabName(currentEle.getText(), elementPath) > 0) {
			errorCtr++;
			System.out.println("ERROR- Found tab is not correct.");
		}//if
		else {
			clickElement(currentEle);
			if(confirmTabName(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Clicked tab is not correct.");
			}//if
			else {}//else
		}//else
		
		//check "There are currently no active tasks."
		//call setElementById to find the element that displays the empty state
		elementPath = "empty-state";
		currentEle = setElementByClass(driver, elementPath);
		if(confirmElementByClass(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty state is not shown. " + currentEle.getAttribute("class") + " is shown.");
		}//if
		else {}//else
		
		//call setElementById to find the element that displays the no tasks message
		elementPath = "empty-task-message";
		currentEle = setElementById(driver, elementPath);
		if(confirmElementById(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty tasks message is not shown. " + currentEle.getAttribute("id") + " is shown.");
		}//if
		else {
			//check if the correct message is displayed
			elementPath = "There are currently no active tasks.";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
			}//if
			else {}//else
		}//else

		//click completed tab
		elementPath = "completed";
		currentEle = getSpecificFilterTab(driver, elementPath);
		elementPath = "Completed";
		if(confirmTabName(currentEle.getText(), elementPath) > 0) {
			errorCtr++;
			System.out.println("ERROR- Found tab is not correct.");
		}//if
		else {
			clickElement(currentEle);
			if(confirmTabName(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Clicked tab is not correct.");
			}//if
			else {}//else
		}//else
		
		//check "There are currently no completed tasks."
		elementPath = "empty-state";
		currentEle = setElementByClass(driver, elementPath);
		if(confirmElementByClass(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty state is not shown. " + currentEle.getAttribute("class") + " is shown.");
		}//if
		else {}//else
		
		//call setElementById to find the element that displays the no tasks message
		elementPath = "empty-task-message";
		currentEle = setElementById(driver, elementPath);
		if(confirmElementById(currentEle, elementPath) > 0){
			errorCtr++;
			System.out.println("ERROR- Empty tasks message is not shown. " + currentEle.getAttribute("id") + " is shown.");
		}//if
		else {
			//check if the correct message is displayed
			elementPath = "There are currently no completed tasks.";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
			}//if
			else {}//else
		}//else
		
		//click all tab
		elementPath = "all";
		currentEle = getSpecificFilterTab(driver, elementPath);
		elementPath = "All";
		if(confirmTabName(currentEle.getText(), elementPath) > 0) {
			errorCtr++;
			System.out.println("ERROR- Found tab is not correct.");
		}//if
		else {
			clickElement(currentEle);
			if(confirmTabName(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Clicked tab is not correct.");
			}//if
			else {}//else
		}//else
		
		//click text bar
		//input "wash the car" into text bar
		//press add button
		//confirm "wash the car" is shown
		//confirm all tab is still clicked
		//confirm "1 active item left" is shown
		
		//click active tab
		//confirm "wash the car" is still shown
		
		//click completed tab
		//confirm "There are currently no completed tasks." is still shown
		
		//click all tab
		//click the checkbox next to "wash the car"
		//confirm the tab is still all tab
		//confirm "wash the car" is marked as completed
		//confirm "0 active items left" is shown
		
		//click active tab
		//confirm "There are currently no active tasks." is shown
		
		//click completed tab
		//confirm "wash the car" completed task is shown
		
		//click text bar
		//input "change kitchen light" into text bar
		//press add button
		//confirm "wash the car" is shown completed
		//confirm "change kitchen light" is not shown
		//confirm "1 active item left" is shown
		
		//etc................
		
		//relay error count
		System.out.print("Testing complete. Found " + errorCtr + " error" + ((errorCtr != 1) ? "s" : "") + " in the test run.");
		
		//end the session ALWAYS NEEDED
		endSession(driver);
		
	}//main

}//Testing
