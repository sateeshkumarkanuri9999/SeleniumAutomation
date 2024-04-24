package com.orikan.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.log4testng.Logger;

import com.orikan.utilities.SeleniumActions;
import com.orikan.utilities.TestUtils;
import com.orikan.utilities.WebDriverListener;

public class TestBase {

	protected static WebDriver driver;
	protected static Properties properties;
	protected static com.orikan.utilities.SeleniumActions sele_Actions;
	protected static WebDriverEventListener eventListener;
	protected static EventFiringWebDriver e_driver;
	protected static ChromeOptions chromeOptions;
	private static Logger log;

	public TestBase() {

		try {
			properties = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/java/com/orikan/config/config.properties");
			properties.load(ip);
			log = Logger.getLogger(TestBase.class);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("io exception");
		}
	}

	public static void initialization() {
		String browserName = properties.getProperty("browser");
		driver = getDriver(browserName);
		log.info(browserName + " is configured");

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebDriverListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.get(properties.getProperty("url"));
		sele_Actions = new SeleniumActions();

	}

	private static WebDriver getDriver(String browserName) {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.setBrowserVersion("stable");
		driver = new ChromeDriver(chromeOptions);
		return driver;
	}

	public void tearDownMain() {
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
