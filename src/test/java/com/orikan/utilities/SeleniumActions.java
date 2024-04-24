package com.orikan.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SeleniumActions {
	public void selectbyText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	public void selectbyValue(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

}
