import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupUserStory {
 
	private WebDriver driver = new FirefoxDriver();
	private String URL = "http://www.linkedin.com";
	
	
	// Tests that attempting to create an account with an email address
	// that has been registered by someone else will be rejected
	@Test
	public void Scenario1() throws InterruptedException {
		 driver.get(URL);	 	 
		 driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys("Alice");
		 driver.findElement(By.id("lastName-coldRegistrationForm")).sendKeys("Smith");
		 driver.findElement(By.id("email-coldRegistrationForm")).sendKeys("jiz86@pitt.edu");
		 driver.findElement(By.id("password-coldRegistrationForm")).sendKeys("3Jrx9Qw");
		 driver.findElement(By.id("btn-submit")).click();		 
		 String expectedText = "The email address, jiz86@pitt.edu, is already registered.";
		 String actualText = driver.findElement(By.id("global-error")).getText();
		 assertEquals(expectedText, actualText);		 
		 driver.quit();
	}
	
	
	// Tests that attempting to create an account with a password
	// that is too short (5-character long) will be rejected
	@Test
	public void Scenario2() throws InterruptedException {
		driver.get(URL);
		driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys("Alice");
		driver.findElement(By.id("lastName-coldRegistrationForm")).sendKeys("Smith");
		driver.findElement(By.id("email-coldRegistrationForm")).sendKeys("dummy@pitt.edu");
		driver.findElement(By.id("password-coldRegistrationForm")).sendKeys("12345");
		driver.findElement(By.id("btn-submit")).click();		 
		String expectedText = "Please correct the marked field(s) below.";		
		String actualText = driver.findElement(By.id("global-error")).getText();
		assertEquals(expectedText, actualText);
		driver.quit();
	}
	
	
	// Tests that attempting to create an account with a email address
	// that does not follow the format of a legitimate email address will be rejected
	@Test
	public void Scenario3() throws InterruptedException {
		driver.get(URL);	 	 
		driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys("Alice");
		driver.findElement(By.id("lastName-coldRegistrationForm")).sendKeys("Smith");
		driver.findElement(By.id("email-coldRegistrationForm")).sendKeys("dummy@1");
		driver.findElement(By.id("password-coldRegistrationForm")).sendKeys("3Jrx9Qw");
		driver.findElement(By.id("btn-submit")).click();	
		String expectedText = "Please correct the marked field(s) below.";		
		String actualText = driver.findElement(By.id("global-error")).getText();
		assertEquals(expectedText, actualText);
		driver.quit();
	}
	
	// Tests that attempting to create an account
	// without entering a last name will be rejected
	@Test
	public void Scenario4() throws InterruptedException {
		driver.get(URL);
		driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys("Alice");
		driver.findElement(By.id("email-coldRegistrationForm")).sendKeys("dummy@pitt.edu");
		driver.findElement(By.id("password-coldRegistrationForm")).sendKeys("3Jrx9Qw");
		driver.findElement(By.id("btn-submit")).click();	
		String expectedText = "Please correct the marked field(s) below.";		
		String actualText = driver.findElement(By.id("global-error")).getText();
		assertEquals(expectedText, actualText);
		driver.quit();
	}
	
	
	// Tests that attempting to create an account with the same first name
	// and last name will be accepted and user will be redirected to another
	// web page that asks for user's phone number in order to do verification
	@Test
	public void Scenario5() throws InterruptedException {
		driver.get(URL);	 	 
		driver.findElement(By.id("firstName-coldRegistrationForm")).sendKeys("James");	
		driver.findElement(By.id("lastName-coldRegistrationForm")).sendKeys("James");
		driver.findElement(By.id("email-coldRegistrationForm")).sendKeys("dummy@pitt.edu");
		driver.findElement(By.id("password-coldRegistrationForm")).sendKeys("3Jrx9Qw");
		driver.findElement(By.id("btn-submit")).click();
		
		String expectedText = "Please enter your phone number and we'll send you a text message with a verification code you can use for registration.";
		String bodyText = driver.findElement(By.id("main")).getText();
		assertTrue(bodyText.contains(expectedText));
		driver.quit();
	}
}