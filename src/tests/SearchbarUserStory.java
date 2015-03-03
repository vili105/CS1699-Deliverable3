import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SearchbarUserStory {
	
	private WebDriver driver = new FirefoxDriver();
	private String URL = "http://www.linkedin.com";
		
	
	// Scenario 1
	// Tests that searching for "Jiayan Zhang" using the search bar will return a user
	// named "Jiayan Zhang" that users can click a button to connect
	@Test
	public void searchPeople() throws InterruptedException {
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
		
		driver.findElement(By.id("main-search-box")).sendKeys("Jiayan Zhang");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Connect")).size()!=0);		 
		driver.quit();		
	}
	
	
	// Scenario 2
	// Tests that searching for "Google" using the search bar will return a company named "Google"
	// that users can click a button to join
	@Test
	public void searchCompany() throws InterruptedException {
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
		
		driver.findElement(By.id("main-search-box")).sendKeys("Google");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Follow")).size()!=0);		 
		driver.quit();		
	}
	
	// Scenario 3
	// Tests that searching for "Software Developer" using the search bar will return a job post
	// related to "Software Developer" that users can click a button to view
	@Test
	public void searchJob() throws InterruptedException {
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
		
		driver.findElement(By.id("main-search-box")).sendKeys("Software Developer");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("View")).size()!=0);		 
		driver.quit();		
	}
	
	
	// Scenario 4
	// Tests that searching for "Java User Group" using the search bar will return a group
	// related to "Java User Group" that users can click a button to join
	@Test
	public void searchGroup() throws InterruptedException {
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
		
		driver.findElement(By.id("main-search-box")).sendKeys("Java User Group");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Join")).size()!=0);		 
		driver.quit();		
	}
	
	
	// Scenario 5
	// Tests that searching for "iPhone 5s" using the search bar will return a post
	// related to "iPhone 5s"" that users can click a button to share
	@Test
	public void searchPost() throws InterruptedException {
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
		
		driver.findElement(By.id("main-search-box")).sendKeys("iPhone 5s");		
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("Posts")).click();
		assertTrue(driver.findElements(By.linkText("Share")).size()!=0);		 
		driver.quit();		
	}
}