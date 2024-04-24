package com.orikan.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.orikan.base.TestBase;
import com.orikan.pages.OrikanHomePage;

public class OrikanTestcases extends TestBase {

	OrikanHomePage orikanHomePage;
	

	public OrikanTestcases() {
		super();
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		initialization();
		orikanHomePage = new OrikanHomePage();
	}

	@Test
	public void TC001_OrikanHomePageDefaultValidations() {
		Assert.assertTrue(orikanHomePage.isLogoDisplayed());
		Assert.assertTrue(orikanHomePage.page1.isDisplayed());
		Assert.assertTrue(orikanHomePage.registration.isDisplayed());
	}

	@Test
	public void TC002_emailAddressPage() {
		String lsInvalidEmails[] = { "abc", "abc123", "122", "c", "@sadfas" };
		String lsValidEmails[] = { "a@abc", "a@a.com", "test@abc.com" };

		for (int i = 0; i < lsInvalidEmails.length; i++) {
			orikanHomePage.emailAddress.clear();
			orikanHomePage.emailAddress.sendKeys(lsInvalidEmails[i]);
			orikanHomePage.emailAddress.sendKeys(Keys.TAB);
			Assert.assertTrue(orikanHomePage.invalidEmailAddress.isDisplayed());
		}

		driver.navigate().refresh();
		for (int i = 0; i < lsValidEmails.length; i++) {
			orikanHomePage.emailAddress.clear();
			orikanHomePage.emailAddress.sendKeys(lsValidEmails[i]);
			orikanHomePage.emailAddress.sendKeys(Keys.TAB);
			Assert.assertEquals(driver.findElements(By.xpath("//span[text()='Invalid email address.']")).size(), 0);
		}
	}

	@Test
	public void TC002_PasswordValidations() {
		orikanHomePage.password.sendKeys("");
		orikanHomePage.nextButton.click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Password is required.']")).isDisplayed());

		orikanHomePage.confirmPassword.sendKeys("");
		orikanHomePage.nextButton.click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Confirm Password is required']")).isDisplayed());

		driver.navigate().refresh();
		Assert.assertFalse(driver.findElements(By.xpath("//span[text()='Password is required.']")).size() > 0);
		Assert.assertFalse(driver.findElements(By.xpath("//span[text()='Confirm Password is required']")).size() > 0);

		orikanHomePage.password.clear();
		orikanHomePage.confirmPassword.clear();
		orikanHomePage.password.sendKeys("Hello");
		orikanHomePage.confirmPassword.sendKeys("Hello");
		Assert.assertFalse(driver.findElements(By.xpath("//span[text()='Passwords do not mach']")).size()>0);
		orikanHomePage.confirmPassword.sendKeys("Hello122342");
		orikanHomePage.confirmPassword.sendKeys(Keys.TAB);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Passwords do not mach']")).isDisplayed());

		orikanHomePage.confirmPassword.clear();
		orikanHomePage.confirmPassword.sendKeys("Hello");
		Assert.assertFalse(driver.findElements(By.xpath("//span[text()='Passwords do not mach']")).size()>0);
	}

	@Test
	public void TC003_ContactValidations() {
		
		orikanHomePage.logo.click();
		orikanHomePage.emailAddress.sendKeys("Test@testing.com");
		orikanHomePage.password.sendKeys("Hello");
		orikanHomePage.confirmPassword.sendKeys("Hello");
		orikanHomePage.nextButton.click();
			
		String lsFields[] = { "First", "Last", "Line 1", "Post", "City", "State" };
		for (int i = 0; i < lsFields.length; i++) {
			Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), '"  + lsFields[i] + "')]/span[text()='*']"))
					.isDisplayed());
		}

		String lsNonMandateFields[] = { "Middle", "Preferred", "Line 2", "Line 3" };
		for (int i = 0; i < lsNonMandateFields.length; i++) {
			Assert.assertEquals(
					driver.findElements(By.xpath("//label[contains(text(), '" + lsNonMandateFields[i] + "')]/span[text()='*']")).size(), 0);
		}
	}

	@Test
	public void TC004_NavigateToPayment() {
		orikanHomePage.logo.click();
		orikanHomePage.emailAddress.sendKeys("test@test.com");
		orikanHomePage.password.sendKeys("Test@1234");
		orikanHomePage.confirmPassword.sendKeys("Test@1234");
		orikanHomePage.nextButton.click();
		
		orikanHomePage.firstName.sendKeys(TestBase.properties.getProperty("firstName"));
		orikanHomePage.lastName.sendKeys(TestBase.properties.getProperty("lastName"));
		orikanHomePage.middleName.sendKeys(TestBase.properties.getProperty("middleName"));

		orikanHomePage.address1.sendKeys(TestBase.properties.getProperty("Address1"));
		orikanHomePage.address2.sendKeys(TestBase.properties.getProperty("Address2"));
		orikanHomePage.address3.sendKeys(TestBase.properties.getProperty("Address3"));

		orikanHomePage.postCode.sendKeys(TestBase.properties.getProperty("PostCode"));
		orikanHomePage.city.sendKeys(TestBase.properties.getProperty("City"));

		orikanHomePage.selectState(TestBase.properties.getProperty("State"));

		orikanHomePage.nextButton.click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='wizard-page-item active']//*[@class='wizard-icon-text']")).getText(),"3");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='wizard-page-item active']//div[@class='wizard-page-text']")).getText(),"Payment");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		tearDownMain();
	}

}
