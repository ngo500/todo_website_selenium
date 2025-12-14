package todo_website_testing;
import org.openqa.selenium.WebDriver;
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
		
		//confirm the website is correct
		//get the title of the page using getPageTitle method
		String title = getPageTitle(driver);
		//print the title
		System.out.println("The title of this page is: " + title + ".");
		//check if the page title is correct
		if(title.contains("To-Do List Website")){
			//the title is correct
			System.out.println("Correct Website.");
		}//if
		else {
			//the title is incorrect
			System.out.println("ERROR- Wrong Website.");
		}//else
		
		//confirm default tab shown in "All"
		//check "There are currently no tasks." is shown
		//check "0 active items left" is shown
		
		//click active tab
		//check "There are currently no active tasks."
		
		//click completed tab
		//check "There are currently no completed tasks."
		
		//click all tab
		
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
		
		
		//end the session ALWAYS NEEDED
		endSession(driver);
		
	}//main

}//Testing
