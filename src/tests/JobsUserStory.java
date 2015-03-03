package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class JobsUserStory
{
	private WebDriver driver;

	@Before
	public void setUp() throws Exception
	{
		driver = new FirefoxDriver();
		login();
		goToPostJob();
	}

	@After
	public void tearDown() throws Exception
	{
		driver.quit();
	}

	/**
	 * Submit the form without filling the company name
	 */
	@Test
	public void Scenario1()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-company > .error > strong"));
		assertEquals("Please enter a company name.", error_element.getText());
	}
	
	/**
	 * Enter a company name longer than 100 characters
	 */
	@Test
	public void Scenario2()
	{
		WebElement company_name_input = driver.findElement(By.id("field-company-name"));
		company_name_input.sendKeys("This is a very long company name. This is a very long company name. This is a very long company name. This is a very long company name. This is a very long company name. This is a very long company name.");
		
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-company > .error > strong"));
		assertEquals("Your company name must be no longer than 100 characters.", error_element.getText());
	}
	
	/**
	 * Submit the form with empty job description
	 */
	@Test
	public void Scenario3()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".form-row-job-description > .error > strong"));
		assertEquals("Please enter a job description.", error_element.getText());
	}
	
	/**
	 * Submit the form without postal code
	 */
	@Test
	public void Scenario4()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".location > .error"));
		assertEquals("Please enter a valid postal code.", error_element.getText());
	}
	
	/**
	 * Submit the form with the minimum required fields filled
	 * The test will fail because Selenium cannot fill the fields inside iframes
	 */
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
	
	/**
	 * Submit the form with empty job title
	 */
	@Test
	public void Scenario6()
	{
		WebElement continue_button = driver.findElement(By.id("_action_saveJobAttributesAndContinue"));
		continue_button.click();
		
		WebElement error_element = driver.findElement(By.cssSelector(".section-position .form-row > .error > strong"));
		assertEquals("Please enter a title for this job.", error_element.getText());
	}
	
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
	
	private void goToPostJob()
	{
		driver.get("https://www.linkedin.com/job/consumer/wow/index?trk=jobs_home_post_job_new");
		WebElement post_a_job_button = driver.findElement(By.id("extra")).findElement(By.linkText("Post a Job"));
		post_a_job_button.click();
	}
}
