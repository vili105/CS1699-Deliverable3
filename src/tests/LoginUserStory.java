import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginUserStory {
	
	private WebDriver driver;
	private String URL = "http://www.linkedin.com";
	

	@Before
	public void setUp()
	{
		driver = new FirefoxDriver();
		driver.get(URL);
	}
	
	
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	
	// Tests that short password under 6 characters will not be accepted
	// an error message will be returned
	@Test
	public void Scenario1()
	{
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("sh");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity_container = driver.findElement(By.id("session_password-login-error"));
		
		assertEquals(identity_container.getText(), "The password you provided must have at least 6 characters.");
	}
	
	
	// Tests that the user should not be authenticated with wrong password
	// an error message will be returned
	@Test
	public void Scenario2()
	{
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("wrong_password");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("session_password-login-error"));
		
		assertTrue(identity.getText().contains("Hmm, that's not the right password"));
	}
	
	
	
	// Tests that the user should not be authenticated with wrong email
	// an error message will be returned
	@Test
	public void Scenario3()
	{		
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105105105105105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("wrong_password");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("session_key-login-error"));
		
		assertEquals(identity.getText(), "Hmm, we don't recognize that email. Please try again.");
	}

	
	
	// Tests that if the user is authenticated with a pair of valid email and password
	// the user will be redirected to the profile page that contains the user's name
	@Test	
	public void Scenario4()
	{
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("chiburashka");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement identity = driver.findElement(By.id("identity"));
		
		assertTrue(identity.getText().contains("vil vil"));
	}
	
	// Longer emails than 128 characters should not be accepted
	// Fill the email field with 200 repeating 'a' characters
	// Check if the proper error message is displayed
	@Test
	public void Scenario5()
	{
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys(StringUtils.repeat("a", 200));
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys("wrong_password");
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement error = driver.findElement(By.id("session_key-login-error"));
		
		assertTrue(error.getText().contains("The text you provided is too long"));
	}

	// Longer passwords than 400 characters should not be accepted
	// Fill the password field with 500 repeating 'a' characters
	// Check if the proper error message is displayed
	@Test
	public void Scenario6()
	{
		WebElement username_input = driver.findElement(By.id("session_key-login"));
		username_input.sendKeys("vili105@abv.bg");
		WebElement password_input = driver.findElement(By.id("session_password-login"));
		password_input.sendKeys(StringUtils.repeat("a", 500));
		WebElement form = driver.findElement(By.id("signin"));
		form.submit();
		
		WebElement error = driver.findElement(By.id("session_password-login-error"));
		
		assertEquals(error.getText(), "The password you provided must have at most 400 characters.");
	}
}
