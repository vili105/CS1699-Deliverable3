package tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * User story: 
 * As a user
 * I want to login to the system
 * So that I can access my personal data
 */
public class LoginUserStory
{
	private WebDriver driver;
	
	/**
	 * Prepare the Firefox driver
	 */
	@Before
	public void setUp()
	{
		driver = new FirefoxDriver();
	}
	
	/**
	 * Free up the used resources
	 */
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	/**
	 * Short password under 6 characters should not be accepted
	 * 
	 * Check if the proper error message is displayed
	 */
	@Test
	public void Scenario1()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("sh");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity_container = driver.findElement(By.id("session_password-login-error"));
		
		assertEquals(identity_container.getText(), "The password you provided must have at least 6 characters.");
	}
	
	/**
	 * The user should not be authenticated with wrong password
	 * 
	 * Check if the proper error message is displayed
	 */
	@Test
	public void Scenario2()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("wrong_password");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("session_password-login-error"));
		
		assertTrue(identity.getText().contains("Hmm, that's not the right password"));
	}
	
	/**
	 * The user should not be authenticated with wrong email
	 * 
	 * Check if the proper error message is displayed
	 */
	@Test
	public void Scenario3()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105105105105105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("wrong_password");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("session_key-login-error"));
		
		assertEquals(identity.getText(), "Hmm, we don't recognize that email. Please try again.");
	}

	/**
	 * The user should be authenticated with a
	 * valid email and password
	 * 
	 * Check if the user name is contained in the profile element
	 */
	@Test
	public void Scenario4()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("chiburashka");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("identity"));
		
		assertTrue(identity.getText().contains("vil vil"));
	}

}
