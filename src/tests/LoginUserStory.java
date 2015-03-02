package tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginUserStory
{
	private WebDriver driver;
	
	@Before
	public void setUp()
	{
		driver = new FirefoxDriver();
	}
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	/**
	 * Short password under 6 characters should not be accepted
	 * Check whether the proper error message is displayed
	 */
	@Test
	public void Scenario1()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_element = driver.findElement(By.id("session_key-login"));
		username_element.sendKeys("vili105@abv.bg");
		WebElement password_element = driver.findElement(By.id("session_password-login"));
		password_element.sendKeys("sh");
		WebElement form_element = driver.findElement(By.id("signin"));
		form_element.submit();
		
		WebElement identity = driver.findElement(By.id("session_password-login-error"));
		
		assertEquals(identity.getText(), "The password you provided must have at least 6 characters.");
	}
	
	/**
	 * Enter wrong password
	 * Check whether the proper error message is displayed
	 */
	@Test
	public void Scenario2()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_element = driver.findElement(By.id("session_key-login"));
		username_element.sendKeys("vili105@abv.bg");
		WebElement password_element = driver.findElement(By.id("session_password-login"));
		password_element.sendKeys("wrong_password");
		WebElement form_element = driver.findElement(By.id("signin"));
		form_element.submit();
		
		WebElement identity = driver.findElement(By.id("session_password-login-error"));
		
		assertTrue(identity.getText().contains("Hmm, that's not the right password"));
	}
	
	/**
	 * Enter wrong email
	 * Check whether the proper error message is displayed
	 */
	@Test
	public void Scenario3()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_element = driver.findElement(By.id("session_key-login"));
		username_element.sendKeys("vili105105105105105@abv.bg");
		WebElement password_element = driver.findElement(By.id("session_password-login"));
		password_element.sendKeys("wrong_password");
		WebElement form_element = driver.findElement(By.id("signin"));
		form_element.submit();
		
		WebElement identity = driver.findElement(By.id("session_key-login-error"));
		
		assertEquals(identity.getText(), "Hmm, we don't recognize that email. Please try again.");
	}

	/**
	 * Enter valid email and password and obtain login
	 * Check whether the user name is contained in the profile element
	 */
	@Test
	public void Scenario4()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_element = driver.findElement(By.id("session_key-login"));
		username_element.sendKeys("vili105@abv.bg");
		WebElement password_element = driver.findElement(By.id("session_password-login"));
		password_element.sendKeys("chiburashka");
		WebElement form_element = driver.findElement(By.id("signin"));
		form_element.submit();
		
		WebElement identity = driver.findElement(By.id("identity"));
		
		assertTrue(identity.getText().contains("vil vil"));
	}

}
