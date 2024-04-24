package com.orikan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.orikan.base.TestBase;

public class OrikanHomePage extends TestBase {

    public OrikanHomePage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@class='titleImage']")
    public WebElement logo;
    
    @FindBy(xpath = "//*[@class='wizard-page-item active']")
    public WebElement activePage;    
    
    @FindBy(xpath = "//*[@class='wizard-icon-text' and text()='1']")
    public WebElement page1;

    @FindBy(xpath = "//*[@class='wizard-page-text' and text()='Registration']")
    public WebElement registration;

    @FindBy(xpath = "//input[@id='emailAddress']")
    public WebElement emailAddress;

    @FindBy(xpath = "//span[contains(text(), ' is required')]")
    public WebElement errorMessage;
    
    @FindBy(xpath = "//span[text()='Invalid email address.']")
    public WebElement invalidEmailAddress;
    
    @FindBy(xpath = "//input[@id='password']")
    public WebElement password;
    
    @FindBy(xpath = "//input[@id='confirmPassword']")
    public WebElement confirmPassword;
    
    @FindBy(xpath = "//button[text()='Next']")
    public WebElement nextButton;
    
    @FindBy(xpath = "//input[@id='firstName']")
    public WebElement firstName;
    
    @FindBy(xpath = "//input[@id='lastName']")
    public WebElement lastName;
    
    @FindBy(xpath = "//input[@id='middleName']")
    public WebElement middleName;
    
    @FindBy(xpath = "//input[@id='preferredFullName']")
    public WebElement preferredFullName;
    
    @FindBy(xpath = "//input[@id='addressLine1']")
    public WebElement address1;
    
    @FindBy(xpath = "//input[@id='addressLine2']")
    public WebElement address2;
    
    @FindBy(xpath = "//input[@id='addressLine3']")
    public WebElement address3;
    
    @FindBy(xpath = "//input[@id='postcode']")
    public WebElement postCode;
    
    @FindBy(xpath = "//button[text()='Back']")
    public WebElement backButton;
    
    @FindBy(xpath = "//input[@id='cardTypeVISA']")
    public WebElement rbtnVisa;
    
    @FindBy(xpath = "//input[@id='cardTypeMastercard']")
    public WebElement rbtnMC;
    
    @FindBy(xpath = "//input[@id='cardNumber']")
    public WebElement cardNumber;
    
    @FindBy(xpath = "//input[@id='cardCVV']")
    public WebElement cardCVV;
    
    @FindBy(xpath = "//input[@id='cardHolderName']")
    public WebElement cardHolderName;

    @FindBy(xpath = "//input[@id='cardTypeVISA']")
    public WebElement cardTypeVisa;
    
    @FindBy(xpath = "//input[@id='cardTypeMastercard']")
    public WebElement cardTypeMC;
   
    @FindBy(xpath = "//select[@id='cardExpiryMonth']")
    public WebElement cardExpMonth;
   
    @FindBy(xpath = "//input[@id='cardExpiryYear']")
    public WebElement cardExpYear;
    
    @FindBy(xpath = "//input[@id='agreedToTerms']")
    public WebElement chkBxagreement;
    
    @FindBy(xpath = "//*[@class='ng-untouched ng-pristine ng-valid']")
    public WebElement welcomeMsg;
    
    @FindBy(xpath = "//input[@id='city']")
    public WebElement city;
    
    @FindBy(xpath = "//select[@id='state']")
    public WebElement state;
 
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
    
    public void selectState(String sState) {
    	Select select = new Select(state);
    	select.selectByVisibleText(sState);    	
    }

}
