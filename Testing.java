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
	 * This method takes a given WebDriver and returns a List<WebElement> of all the list items in a list
	 * @param driver The WebDriver being used for the automation.
	 * @return The List<WebElement> of all list items.
	 */
	public static List<WebElement> getTodoItemList(WebDriver driver){
		//set path of todo items
		String elementPath = "//*[@id=\"todos-list\"]/li";
		//save filters in a List of WebElements
		List<WebElement> tabList = driver.findElements(By.xpath(elementPath));
		return tabList;
	}//getTodoItemList
	
	/**
	 * This method takes a given WebDriver and returns the last item added to a List<WebElement> as a WebElement.
	 * @param driver The given WebDriver being used for the automation.
	 * @return The WebElement that was last added to the List<WebElement>. 
	 * If the List<WebElement> is empty, returns a null WebElement.
	 */
	public static WebElement getLastTodoListItem(WebDriver driver) {
		//call getTodoItemList to get the items
		List<WebElement> itemList = getTodoItemList(driver);
		WebElement lastTodoListItem;
		if(itemList.size() > 0) {
			lastTodoListItem = itemList.get(itemList.size() - 1);
		}//if
		else {
			lastTodoListItem = null;
		}//else
		return lastTodoListItem;
	}//getLastTodoListItem
	
	/**
	 * This method takes a given WebDriver and a given String of the element to find in the Todo list, and returns the item.
	 * @param driver The given WebDriver being used for the automation.
	 * @param givenItem The String format text of the WebElement being searched for in the Todo list.
	 * @return The WebElement of the matching item in the Todo list.
	 * If the WebElement is not found, returns a null WebElement.
	 */
	public static WebElement getSpecificTodoListItem(WebDriver driver, String givenItem) {
		//call getTodoItemList to get the items
		List<WebElement> itemList = getTodoItemList(driver);
		WebElement getTodoListItem;
		if(itemList.size() > 0) {
			//iterators to go through list
			Iterator<WebElement> a1 = itemList.iterator();
			Iterator<WebElement> a2 = itemList.iterator();
			
			//while there are still items to check,
			while(a1.hasNext() && a2.hasNext()) {
				//check if the current tab is the active tab
				if(a2.next().getText().equals(givenItem)) {
					//active filter, return tab name
					getTodoListItem = a1.next();
					return getTodoListItem;
				}//if
				else {
					//else, not the active filter, keep going
					a1.next();
				}//else
			}//while
		}//if
		else {}//else
		getTodoListItem = null;
		return getTodoListItem;
	}//getSpecificTodoListItem
	
	/**
	 * This method takes a given WebElement list item and returns its corresponding checkmark.
	 * @param currentEle The given WebElement that is a todo list item.
	 * @return The corresponding checkmark of the todo list item in WebElement format.
	 */
	public static WebElement getTodoListItemCheckmark(WebElement currentEle) {
		return currentEle.findElement(By.className("checkmark"));
	}//getTodoListItemCheckbox
	
	/**
	 * This method takes a given WebDriver and returns the current size of the task list.
	 * @param driver The given WebDriver being used for automation.
	 * @return The size of the task list in int format. Returns 0 if the list is empty.
	 */
	public static int getTaskListSize(WebDriver driver) {
		int size = 0;
		//call getTodoItemList to get the items
		List<WebElement> itemList = getTodoItemList(driver);
		size = itemList.size();
		return size;
	}//getTaskListSize
	
	/**
	 * This method takes a given WebDriver and returns the current size of the active tasks in the task list.
	 * @param driver The given WebDriver being used for automation.
	 * @return The size of the active task list in int format. Returns 0 if the active list is empty.
	 */
	public static int getActiveTaskListSize(WebDriver driver) {
		int size = 0;
		
		//call getTodoItemList to get the items
		List<WebElement> itemList = getTodoItemList(driver);
		
		if(itemList.size() > 0) {
			//iterators to go through list
			Iterator<WebElement> a1 = itemList.iterator();
			Iterator<WebElement> a2 = itemList.iterator();
			
			//while there are still items to check,
			while(a1.hasNext() && a2.hasNext()) {
				//check if the current item is active
				//if active, add to new list
				String elementPath = a1.next().getText();
				WebElement temp = getSpecificTodoListItem(driver, elementPath);
				if(temp.findElement(By.className("todo-checkbox")) != null) {
					temp = temp.findElement(By.className("todo-checkbox"));
					if(confirmCheckmarkNotClicked(temp) == 0) {
						size++;
					}//if
					else {}//else not active, skip
				}//if
				else {}//else
			}//while
		}//if
		else {}//else	
		
		return size;
	}//getActiveTaskListSize
	
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
	
	/**
	 * This method takes a given WebElement that can receive input and sends it the given String input.
	 * @param currentEle The WebElement being sent input.
	 * @param text The String being input into the given WebElement.
	 */
	public static void sendText(WebElement currentEle, String text) {
		currentEle.sendKeys(text);
	}//sendText
	
	/**
	 * This method takes a given WebElement that has a placeholder text and confirms it matches the given String.
	 * @param currentEle The given WebElement containing placeholder text to check.
	 * @param elementPath The given String to use to check the placeholder text against.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given text does not match.
	 */
	public static int confirmPlaceholderText(WebElement currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.getAttribute("placeholder").equals(elementPath))){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmPlaceholderText
	
	/**
	 * This method takes a given WebElement that can receive input text and confirms it matches the given String.
	 * @param currentEle The given WebElement containing input text to check.
	 * @param elementPath The given String to use to check the input text against.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there there is a match, and a 1 if the given text does not match.
	 */
	public static int confirmInputBarText(WebElement currentEle, String elementPath) {
		int errorCount = 0;
		if(!(currentEle.getAttribute("value").equals(elementPath))){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmInputBarText
	
	/**
	 * This method takes a given WebElement that can receive input text and confirms it is null.
	 * @param currentEle The given WebElement containing input text to check.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there is a match, and a 1 if the given text does not match.
	 */
	public static int confirmInputBarTextEmpty(WebElement currentEle) {
		int errorCount = 0;
		if(!(currentEle.getAttribute("value") == null || currentEle.getAttribute("value").isEmpty())){
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmInputBarText
	
	/**
	 * This method takes a given WebElement that is a todo list item from the todo list and returns the text in String format.
	 * @param currentEle The given WebElement to get the text from.
	 * @return The text for the given WebElement todo item in String format.
	 */
	public static String getTodoItemText(WebElement currentEle) {
		return currentEle.getText();
	}//getTodoItemText
	
	/**
	 * This method takes a given WebElement that is a todo list checkmark from the todolist and confirms it is clicked.
	 * @param currentEle The given WebElement containing the checkmark to check.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there the checkmark is clicked, and a 1 if it is not clicked.
	 */
	public static int confirmCheckmarkClicked(WebElement currentEle) {
		int errorCount = 0;
		if(!(currentEle.isSelected())){
			System.out.println("ERROR- Checkbox is not clicked, but should be clicked.");
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmCheckmarkClicked
	
	/**
	 * This method takes a given WebElement that is a todo list checkmark from the todolist and confirms it is not clicked.
	 * @param currentEle The given WebElement containing the checkmark to check.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if there the checkmark is not clicked, and a 1 if it is clicked.
	 */
	public static int confirmCheckmarkNotClicked(WebElement currentEle) {
		int errorCount = 0;
		if(currentEle.isSelected()){
			System.out.println("ERROR- Checkbox is clicked, but should not be clicked.");
			errorCount++;
		}//if
		else {}//else
		return errorCount;
	}//confirmCheckmarkNotClicked
	
	/**
	 * This method takes a given WebDriver and confirms the active tab matches the given String.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The String being used to confirm the current tab.
	 * @return The int that shows the result of the comparison. 
	 * The int is 0 if the active tab is correct, and a 1 if it does not match.
	 */
	public static int confirmActiveTab(WebDriver driver, String elementPath) {
		String currentTab = getActiveFilterTab(driver);
		if(confirmTabName(currentTab, elementPath) > 0) {
			System.out.println("ERROR- Default tab is not correct.");
			return 1;
		}//if
		else {
			return 0;
		}//else
	}//confirmActiveTab
	
	/**
	 * This method takes a given WebDriver and clicks the tab that matches the given String.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The String being used to click the correct tab.
	 * @return The int that shoes the result of the click.
	 * The int is 0 if the tab was clicked correctly, and a 1 if the correct tab was not clicked.
	 */
	public static int clickTab(WebDriver driver, String elementPath) {
		WebElement currentEle = getSpecificFilterTab(driver, elementPath);
		elementPath = elementPath.substring(0, 1).toUpperCase() + elementPath.substring(1);
		if(confirmTabName(currentEle.getText(), elementPath) > 0) {
			System.out.println("ERROR- Found tab is not correct.");
			return 1;
		}//if
		else {
			clickElement(currentEle);
			if(confirmTabName(currentEle.getText(), elementPath) > 0) {
				System.out.println("ERROR- Clicked tab is not correct.");
				return 1;
			}//if
			else {
				return 0;
			}//else
		}//else
	}//clickTab
	
	/**
	 * This method takes a given WebDriver and String name of the active tab and confirms the tab shown gives an empty state.
	 * @param driver The WebDriver being used for the automation.
	 * @param tabName The name of the current tab being checked empty state, in String format.
	 * @return The int is 0 if the empty state is shown correctly, and greater than 0 if there were errors in the empty state.
	 */
	public static int confirmEmptyState(WebDriver driver, String tabName) {
		//set error counter
		int errorCtr = 0;
		//call setElementById to find the element that displays the empty state
		String elementPath = "empty-state";
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
			elementPath = tabName;
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
			}//if
			else {}//else
		}//else

		//check "active items left" is shown
		errorCtr += confirmItemsLeft(driver);
		
		return errorCtr;
	}//confirmEmptyState
	
	/**
	 * This method takes a given WebDriver and confirms the "active item left" message is shown correctly.
	 * @param driver The WebDriver being used for the automation.
	 * @return The int is 0 if the message is displayed correctly, and 1 if the message is not displayed correctly.
	 */
	public static int confirmItemsLeft(WebDriver driver) {
		int errorCtr = 0;
		int size = getActiveTaskListSize(driver);
		String elementPath = "items-left";
		WebElement currentEle = setElementById(driver, elementPath);
		if(size == 1) {
			//check "1 active item left" is shown
			elementPath = "1 active item left";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- 1 active item left message is not shown.");
			}//if
			else {}//else
		}//if
		else if(size > 1) {
			//check ">1 active items left" is shown
			elementPath = size + " active items left";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- " + size + " active items left message is not shown.");
			}//if
			else {}//else
		}//else if
		else{
			//check "0 active items left" is shown
			elementPath = "0 active items left";
			if(confirmMessageText(currentEle.getText(), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- 0 active items left message is not shown.");
			}//if
			else {}//else
		}//else
		return errorCtr;
	}//confirmItemsLeft
	
	/**
	 * This method takes a given WebDriver and adds a task to the todo list using the given String.
	 * @param driver The WebDriver being used for the automation.
	 * @param taskMsg The task to be added to the todo list in String format.
	 * @return The int is 0 if the message is added correctly, and 1 if the message is not added correctly.
	 */
	public static int addTask(WebDriver driver, String taskMsg) {
		//set int to store errors
		int errorCtr = 0;
		//click text bar
		String elementPath = "task-input";
		WebElement currentEle = setElementById(driver, elementPath);

		//confirm there is nothing in the input bar before clicking
		if(confirmInputBarTextEmpty(currentEle) > 0) {
			errorCtr++;
			System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
		}//if
		else {
			//confirm placeholder text is correct before clicking
			elementPath = "What task would you like to add to your to-do list?";
			if(confirmPlaceholderText(currentEle, elementPath)  > 0) {
				errorCtr++;
				System.out.println("ERROR- Placeholder text is not correct.");
			}//if
			else {}//else
			
			clickElement(currentEle);
			//make sure placeholder text is still correct after clicking but before input
			if(confirmPlaceholderText(currentEle, elementPath)  > 0) {
				errorCtr++;
				System.out.println("ERROR- Placeholder text is not correct.");
			}//if
			else {}//else
			//make sure input bar text is still empty after clicking but before input
			if(confirmInputBarTextEmpty(currentEle) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
			}//if
			else {
				//input "wash the car" into text bar
				elementPath = taskMsg;
				sendText(currentEle, elementPath);
				//check the input text is in the bar
				if(confirmInputBarText(currentEle, elementPath) > 0) {
					errorCtr++;
					System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
				}//if
				else {
					//press add button
					elementPath = "button-add-task";
					currentEle = setElementById(driver, elementPath);
					clickElement(currentEle);
					
					//check that the input bar is cleared after add button is pressed
					elementPath = "task-input";
					currentEle = setElementById(driver, elementPath);
					if(confirmInputBarTextEmpty(currentEle) > 0) {
						errorCtr++;
						System.out.println("ERROR- Incorrect message shown. \"" + currentEle.getText() + "\" is the incorrect message.");
					}//if
					else {}//else
					
					//confirm placeholder text is correct again
					elementPath = "What task would you like to add to your to-do list?";
					if(confirmPlaceholderText(currentEle, elementPath)  > 0) {
						errorCtr++;
						System.out.println("ERROR- Placeholder text is not correct.");
					}//if
					else {}//else
					
					//confirm "wash the car" is shown
					currentEle = getLastTodoListItem(driver);
					if(currentEle == null) {
						errorCtr++;
						System.out.println("ERROR- Todo list is empty.");
					}//if
					else {
						elementPath = taskMsg;
						if(confirmMessageText(getTodoItemText(currentEle), elementPath) > 0) {
							errorCtr++;
							System.out.println("ERROR- Incorrect todo item shown. \"" + getTodoItemText(currentEle) + "\" is the incorrect message.");
						}//if
						else {}//else
					}//else
				}//else
			}//else
		}//else
		
		return errorCtr;
	}//addTask
	
	/**
	 * This method takes a given WebDriver and given todo item text in String format and confirms the last item added to the list is correct.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the last item added to the todo list in String format.
	 * @return The int returns 0 if the todo item was added correctly, and 1 if the todo item was not added correctly.
	 */
	public static int confirmLastTodoListItem(WebDriver driver, String elementPath) {
		int errorCtr = 0;
		WebElement currentEle = getLastTodoListItem(driver);
		if(currentEle == null) {
			errorCtr++;
			System.out.println("ERROR- Todo list is empty.");
		}//if
		else {
			if(confirmMessageText(getTodoItemText(currentEle), elementPath) > 0) {
				errorCtr++;
				System.out.println("ERROR- Incorrect todo item shown. \"" + getTodoItemText(currentEle) + "\" is the incorrect message.");
			}//if
			else {}//else
		}//else
		return errorCtr;
	}//confirmLastTodoListItem
	
	/**
	 * This method takes a given WebDriver and a given todo item in String format and selects the checkbox for the list item.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the todo item in String format.
	 * @return The int returns 0 if the checkbox was selected correctly, and 1 if the checkbox was not selected correctly.
	 */
	public static WebElement selectCheckbox(WebDriver driver, String elementPath) {
		WebElement currentEle = getSpecificTodoListItem(driver, elementPath);
		if(currentEle == null) {
			System.out.println("ERROR- Todo list item \" elementPath \" is not found.");
			return null;
		}//if
		else {
			if(confirmMessageText(getTodoItemText(currentEle), elementPath) > 0) {
				System.out.println("ERROR- Incorrect todo item shown. \"" + getTodoItemText(currentEle) + "\" is the incorrect message.");
				System.out.println("\"" + elementPath + "\" should be shown instead.");
			
				return null;
			}//if
			else {}//else
		}//else
		
		//get the current todo item checkmark
		currentEle = getTodoListItemCheckmark(currentEle);
		return currentEle;
	}//selectCheckbox
	
	/**
	 * This method takes a given WebDriver and clicks on the checkbox next to a todolist item with given text in String format.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the todo item in String format.
	 * @return The int returns 0 if the checkbox is clicked correctly, and 1 if the checkbox is not clicked correctly.
	 */
	public static int clickCheckbox(WebDriver driver, String elementPath) {
		int errorCtr = 0;
		//get the correct list checkbox
		WebElement currentEle = selectCheckbox(driver, elementPath);
		
		//confirm the checkbox is not checked yet
		if(confirmCheckmarkNotClicked(currentEle) > 0) {
			errorCtr++;
			System.out.println("ERROR- Checkmark is already clicked.");
		}//if
		else {
			clickElement(currentEle);
			//confirm the checkbox is now checked
			elementPath = "wash the car";
			currentEle = getSpecificTodoListItem(driver, elementPath);
			currentEle = currentEle.findElement(By.className("todo-checkbox"));
			if(confirmCheckmarkClicked(currentEle) > 0) {
				errorCtr++;
				System.out.println("ERROR- Checkmark is not clicked.");
			}//if
			else {}//else
		}//else
		return errorCtr;
	}//clickCheckbox
	
	/**
	 * This method takes a given WebDriver and confirms if the given todo task, found with given String, is complete.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the todo item being checked in String format.
	 * @return The int returns 0 if the given task is confirmed to be checked, and 1 if the task is unchecked.
	 */
	public static int confirmChecked(WebDriver driver, String elementPath) {
		int errorCtr = 0;
		WebElement currentEle = getSpecificTodoListItem(driver, elementPath);
		if(currentEle.findElement(By.className("todo-checkbox")) != null) {
			errorCtr += confirmCheckmarkClicked(currentEle);
		}//if
		else {
			errorCtr++;
			System.out.println("ERROR- Todo item could not be found to check completion status.");
		}//else
		return errorCtr;
	}//confirmChecked
	
	/**
	 * This method takes a given WebDriver and confirms if the given todo task, found with given String, is incomplete.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the todo item being checked in String format.
	 * @return The int returns 0 if the given task is confirmed to be unchecked, and 1 if the task is checked.
	 */
	public static int confirmUnchecked(WebDriver driver, String elementPath) {
		int errorCtr = 0;
		WebElement currentEle = getSpecificTodoListItem(driver, elementPath);
		if(currentEle.findElement(By.className("todo-checkbox")) != null) {
			errorCtr += confirmCheckmarkNotClicked(currentEle);
		}//if
		else {
			errorCtr++;
			System.out.println("ERROR- Todo item could not be found to check completion status.");
		}//else
		return errorCtr;
	}//confirmUnchecked
	
	/**
	 * This method takes a given WebDriver and given String that is the text for the given todo task, and deletes the given task.
	 * @param driver The WebDriver being used for the automation.
	 * @param elementPath The text of the todo item being deleted in String format.
	 * @return The int returns a 0 if the given item is deleted, and 1 if the item is not deleted.
	 */
	public static int deleteItem(WebDriver driver, String elementPath) {
		int errorCtr = 0;
		WebElement currentEle = getSpecificTodoListItem(driver, elementPath);
		if(currentEle != null) {
			currentEle = currentEle.findElement(By.className("button-delete"));
			if(currentEle != null) {
				clickElement(currentEle);
			}//if
			else {
				errorCtr++;
				System.out.println("ERROR- Could not get delete button for specific todo item.");
			}//else
		}//if
		else {
			errorCtr++;
			System.out.println("ERROR- Could not get specific todo item to delete.");
		}//else
		return errorCtr;
	}//deleteItem
	
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
		String allTabMsg = "There are currently no tasks.";
		errorCtr += confirmActiveTab(driver, elementPath);
		
		//check "There are currently no tasks." is shown
		errorCtr += confirmEmptyState(driver, allTabMsg);
		
		//click active tab
		elementPath = "active";
		errorCtr += clickTab(driver, elementPath);
		
		//check "There are currently no active tasks."
		String activeTabMsg = "There are currently no active tasks.";
		errorCtr += confirmEmptyState(driver, activeTabMsg);

		//click completed tab
		elementPath = "completed";
		errorCtr += clickTab(driver, elementPath);
		
		//check "There are currently no completed tasks."
		String completedTabMsg = "There are currently no completed tasks.";
		errorCtr += confirmEmptyState(driver, completedTabMsg);
		
		//click all tab
		elementPath = "all";
		errorCtr += clickTab(driver, elementPath);
		
		//put input in text bar and add
		elementPath = "wash the car";
		errorCtr += addTask(driver, elementPath);
		
		//confirm all tab is still clicked
		elementPath = "All";
		errorCtr += confirmActiveTab(driver, elementPath);
		
		//confirm "1 active item left" is shown
		errorCtr += confirmItemsLeft(driver);
		
		//click active tab
		elementPath = "active";
		errorCtr += clickTab(driver, elementPath);
		
		//confirm "wash the car" is still shown
		elementPath = "wash the car";
		errorCtr += confirmLastTodoListItem(driver, elementPath);
		
		//confirm "1 active item left" is shown
		errorCtr += confirmItemsLeft(driver);
		
		//click completed tab
		elementPath = "completed";
		errorCtr += clickTab(driver, elementPath);
		
		//click all tab
		elementPath = "all";
		errorCtr += clickTab(driver, elementPath);
		
		//click the checkbox next to "wash the car"
		elementPath = "wash the car";
		errorCtr += clickCheckbox(driver, elementPath);
		
		//confirm the tab is still all tab
		elementPath = "All";
		errorCtr += confirmActiveTab(driver, elementPath);
		
		//confirm "0 active items left" is shown
		errorCtr += confirmItemsLeft(driver);
		
		//click active tab
		elementPath = "active";
		errorCtr += clickTab(driver, elementPath);
		
		//confirm "There are currently no active tasks." is shown
		errorCtr += confirmEmptyState(driver, activeTabMsg);
		
		//click completed tab
		elementPath = "completed";
		errorCtr += clickTab(driver, elementPath);
		
		//confirm "wash the car" completed task is shown
		elementPath = "wash the car";
		errorCtr += confirmLastTodoListItem(driver, elementPath);
		
		//click all tab
		elementPath = "all";
		errorCtr += clickTab(driver, elementPath);
		
		//input "change kitchen light" into text bar
		elementPath = "change kitchen light";
		errorCtr += addTask(driver, elementPath);
		
		//confirm all tab is still clicked
		elementPath = "All";
		errorCtr += confirmActiveTab(driver, elementPath);
		
		//confirm "1 active item left" is shown
		errorCtr += confirmItemsLeft(driver);
		
		//confirm "change kitchen light" is shown
		elementPath = "change kitchen light";
		errorCtr += confirmLastTodoListItem(driver, elementPath);
		
		//confirm "wash the car" is shown completed
		elementPath = "wash the car";
		errorCtr += confirmChecked(driver, elementPath);
		
		//confirm "change kitchen light" is not completed
		elementPath = "change kitchen light";
		errorCtr += confirmUnchecked(driver, elementPath);
		
		//delete "wash the car" task
		elementPath = "wash the car";
		errorCtr += deleteItem(driver, elementPath);
		
		//confirm correct task was clicked and deleted
		
		//relay error count
		System.out.print("Testing complete. Found " + errorCtr + " error" + ((errorCtr != 1) ? "s" : "") + " in the test run.");
		
		//end the session ALWAYS NEEDED
		endSession(driver);
		
	}//main

}//Testing
