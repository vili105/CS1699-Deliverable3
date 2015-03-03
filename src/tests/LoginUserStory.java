import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
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
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
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
		
		WebElement error = driver.findElement(By.id("session_password-login-error"));
		
		assertEquals(error.getText(), "The password you provided must have at least 6 characters.");
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
		
		WebElement error = driver.findElement(By.id("session_password-login-error"));
		
		assertTrue(error.getText().contains("Hmm, that's not the right password"));
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
		
		WebElement error = driver.findElement(By.id("session_key-login-error"));
		
		assertEquals(error.getText(), "Hmm, we don't recognize that email. Please try again.");
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
}
