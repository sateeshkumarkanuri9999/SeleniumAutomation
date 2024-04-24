package com.orikan.utilities;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtils {

	static DateFormat dateFormat;
	static Date date;

	public static final long PAGE_LOAD_TIMEOUT = 100;

	public static final long IMPLICIT_WAIT = 10;

	public static final String WORKSAPCE_PATH = System.getProperty("user.dir");// provide path of workspace from your local machine
	
	public static final String DateFormat = "yyyyMMddHHmmss";

	public static String getDate(String format) {
		dateFormat = new SimpleDateFormat(format);
		date = new Date();
		return dateFormat.format(date);
	}

	public static void takeScreenShot(WebDriver driver) {
		
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(src, new File("C:\\"+ getDate(DateFormat) + ".png"));
			System.out.println("Error seen...");
			System.out.println("Path is :: " + System.getProperty("user.dir") + "\\Screenshot\\screenshot\\"+ getDate(DateFormat) + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
