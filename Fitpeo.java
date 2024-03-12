package program;

import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;
import java.util.Set;


public class Fitpeo{
	public static void main(String args[]) throws InterruptedException{
		
		//Initializing  the Web Driver
		WebDriver driver = null; 
		
		String URL = "https://www.flipkart.com"; //The URL to be launched
		Scanner scan = new Scanner(System.in); //Initialization of Scanner class
		System.out.print("Enter the Browser name you want to execute: ");
		String Browser = scan.nextLine(); //Getting the Browser name from the Console
		
		//Checking the Browser name entered by the user if it was chrome if block will execute
		if(Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\manoj\\Software\\ChromeDriver\\chromedriver.exe");
			driver = new ChromeDriver();  //Chrome Driver Initialization
			driver.manage().window().maximize(); //Maximizing the Browser
			driver.get(URL); //launching the Flipkart URL
		}
		
		//if the browser name is Firefox the below block will execute
		else if(Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","C:\\Users\\manoj\\Software\\FirefoxDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(URL);
		}
		//if the browser name is Edge the below block will execute
		else if(Browser.equalsIgnoreCase("edge")){
			System.setProperty("webdriver.edge.driver","C:\\Users\\manoj\\Software\\EdgeDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.get(URL);
		}
		else {
		    System.out.println("name entered by you is not appropriate browser name");
		}
		
		try {
		driver.findElement(By.xpath("//picture[@title='Flipkart']")).isDisplayed(); //Verifying the homepage picture after loading
		System.out.println("Home page is displayed");
		}
		catch(Exception e) {
			System.out.println("Home page is not displayed");
		}
		//Finding the search box
		WebElement searchBox= driver.findElement(By.xpath("//input[@name='q']"));
		searchBox.sendKeys("laptop"); //Sending the text to search box
		searchBox.sendKeys(Keys.ENTER); //After text clicking enter to search the product
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()='Price -- High to Low']")).click(); //Sorting the product by price
		Thread.sleep(3000);
		WebElement Laptop = driver.findElement(By.xpath("//div[@data-id='COMGVGEHR5KYTGDD']")); //selecting a product
		String ParentWindow =  driver.getWindowHandle(); //Getting the parent window handle
		System.out.println(ParentWindow); 
		Laptop.click(); // Clicking on the product we selected
		Set<String> Windows = driver.getWindowHandles(); //Getting the window Handles of the two tabs opened 
		
		for (String windowHandle : Windows) { 
			System.out.println(windowHandle);
			
			if(!windowHandle.equals(ParentWindow)) {
				driver.switchTo().window(windowHandle);  //switching to the child window
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[text()='Add to cart']")).click();  //Clicking on Add to cart button
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button/span[text()='Place Order']")).click(); //Clicking on the Place order Button
				
				driver.findElement(By.xpath("//form/div/input")).sendKeys("Manojkorwar.mk@gmail.com"); //Sending an email address to the field
				driver.findElement(By.xpath("//button/span[text()='CONTINUE']")).click();  //Clicking on the Continue button
				driver.findElement(By.xpath("(//form//input)[2]")).click(); 
				driver.close();
			}
		}
		driver.switchTo().window(ParentWindow); //Switching back to the Parent window
		System.out.println("Test is successfull");
		driver.quit(); //Closing the driver
	}
}

