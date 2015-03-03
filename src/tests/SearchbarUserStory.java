import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SearchbarUserStory {
	
	private WebDriver driver;
	private String URL = "http://www.linkedin.com";
		
	@Before
	public void setUp() throws Exception
	{
		driver = new FirefoxDriver();		
		driver.get(URL);
		
		// Login to LinkedIn first
		driver.findElement(By.id("session_key-login")).sendKeys("testaccount@pitt.edu");
		driver.findElement(By.id("session_password-login")).sendKeys("testing");
		driver.findElement(By.id("signin")).click();
	}
	
	
	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
	
	// Tests Scenario 1 of User Story 2
	// Tests that searching for "Jiayan Zhang" using the search bar will return a user
	// named "Jiayan Zhang" that users can click a button to connect
	@Test
	public void Scenario1() throws InterruptedException {			
		driver.findElement(By.id("main-search-box")).sendKeys("Jiayan Zhang");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Connect")).size()!=0);		 
	}
	
	
    // Tests Scenario 2 of User Story 2
	// Tests that searching for "Google" using the search bar will return a company named "Google"
	// that users can click a button to join
	@Test
	public void Scenario2() throws InterruptedException {		
		driver.findElement(By.id("main-search-box")).sendKeys("Google");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Follow")).size()!=0);		 
	}
	
    
    // Tests Scenario 3 of User Story 2
	// Tests that searching for "Software Developer" using the search bar will return a job post
	// related to "Software Developer" that users can click a button to view
	@Test
	public void Scenario3() throws InterruptedException {		
		driver.findElement(By.id("main-search-box")).sendKeys("Software Developer");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("View")).size()!=0);		 
	}
	
	
    // Tests Scenario 4 of User Story 2
	// Tests that searching for "Java User Group" using the search bar will return a group
	// related to "Java User Group" that users can click a button to join
	@Test
	public void Scenario4() throws InterruptedException {		
		driver.findElement(By.id("main-search-box")).sendKeys("Java User Group");
		driver.findElement(By.name("search")).click();	
		assertTrue(driver.findElements(By.linkText("Join")).size()!=0);		 
	}
	
    // Tests Scenario 5 of User Story 2
	// Tests that searching for "iPhone 5s" using the search bar will return a post
	// related to "iPhone 5s"" that users can click a button to share
	@Test
	public void Scenario5() throws InterruptedException {		
		driver.findElement(By.id("main-search-box")).sendKeys("iPhone 5s");		
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText("Posts")).click();
		assertTrue(driver.findElements(By.linkText("Share")).size()!=0);		 
	}
}
