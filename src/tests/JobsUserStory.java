import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class JobsUserStory {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception
	{
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		login();
		goToPostJob();
	}
	

	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}
	

	// Tests that if user submit the form without filling the company name,
	// an error message will be returned
	@Test
	public void Scenario1()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-company > .error > strong"));
		assertEquals("Please enter a company name.", error_element.getText());
	}
	
	
	// Tests that if user enter a company name longer than 100 characters
	// an error message will be returned
	@Test
	public void Scenario2()
	{
		WebElement company_name_input = driver.findElement(By.id("field-company-name"));
		company_name_input.sendKeys(StringUtils.repeat("a", 101));
		
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-company > .error > strong"));
		assertEquals("Your company name must be no longer than 100 characters.", error_element.getText());
	}
	
	
	// Tests that if user submit the form with empty job description
	// an error message will be returned
	@Test
	public void Scenario3()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-job-description > .error > strong"));
		assertEquals("Please enter a job description.", error_element.getText());
	}
	
	
	
	// Tests that if user submit the form without postal code
	// an error message will be returned
	@Test
	public void Scenario4()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".location > .error"));
		assertEquals("Please enter a valid postal code.", error_element.getText());
	}
	
	
	// Tests that if user submit the form with the minimum required fields filled
	// including the company name, job function, job title, job description, skills and post code fields
	// the test will fail because Selenium cannot fill the fields
	// inside the TinyMCE plugin (http://www.tinymce.com) inside iframe 
	@Test
	public void Scenario5()
	{
		WebElement company_name_input = driver.findElement(By.id("field-company-name"));
		company_name_input.sendKeys("Test company");
		
		Select job_function_select = new Select(driver.findElement(By.name("jobFunctions")));
		job_function_select.selectByVisibleText("Analyst");
		
		WebElement job_title_input = driver.findElement(By.id("field-job-title"));
		job_title_input.sendKeys("Test Engineer");
		
		try {
			WebElement job_description_input = driver.findElement(By.id("field-job-description"));
			job_description_input.sendKeys("No description");
			
			WebElement skills_input = driver.findElement(By.id("field-skills"));
			skills_input.sendKeys("No skills");
		} catch (ElementNotVisibleException ex) {
			fail();
		}
		
		WebElement postal_code_input = driver.findElement(By.id("field-postal-code"));
		postal_code_input.sendKeys("1582");
		
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		assertTrue(driver.getTitle().contains("Step 2"));
	}
	
	
	// Tests that if user submit the form with empty job title
	// an error message will be returned
	@Test
	public void Scenario6()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".section-position .form-row > .error > strong"));
		assertEquals("Please enter a title for this job.", error_element.getText());
	}
	

	// Helper method that authenticates the test user before each test scenario	 
	private void login()
	{
		driver.get("https://www.linkedin.com/");
		WebElement username_element = driver.findElement(By.id("session_key-login"));
		username_element.sendKeys("vili105@abv.bg");
		WebElement password_element = driver.findElement(By.id("session_password-login"));
		password_element.sendKeys("chiburashka");
		WebElement form_element = driver.findElement(By.id("signin"));
		form_element.submit();
	}
	

	 // Helper method that loads the post a job" page before each test scenario	 
	private void goToPostJob()
	{
		WebElement jobs_nav_link = driver.findElement(By.partialLinkText("Jobs"));
		jobs_nav_link.click();
		
		WebElement post_a_job_button1 = driver.findElement(By.className("post-job-button"));
		post_a_job_button1.click();
		
		WebElement post_a_job_button2 = driver.findElement(By.id("extra")).findElement(By.linkText("Post a Job"));
		post_a_job_button2.click();
	}
}
