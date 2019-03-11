package asgb;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given; 
import cucumber.api.java.en.Then; 
import cucumber.api.java.en.When;

import java.util.concurrent.TimeUnit;

public class SendMailWithImage {

	public static final String senderAddress = "dummyrecipient832@gmail.com";
	public static final String password = "n0tapassword123";
    public WebDriver senderDriver = null;
    
    @Before
    public void testSetup() {
    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");
    }

    @Given("that I am on a new email page")
    public void senderLogin() {
        senderDriver = login(senderAddress);
    }
    
    /*Write the address into the 'To' textfield
	 *TODO: Determine correct locator
	 */
    @When("I enter {string} as a recipient email address")
    public void enterOneAddress(String address) {
    	//Write the address into the 'To' textfield
    	//TODO: Determine correct locator
    	WebElement toInput = senderDriver.findElement(By.xpath("//textarea [@id=':6w']"));
    	(new WebDriverWait(senderDriver, 20)).until(ExpectedConditions.elementToBeClickable(toInput));
    	toInput.sendKeys(address);
    }
    
    
    @When("I enter {string} and {string} as recipient email addresses")
    public void enterTwoAddresses(String address1, String address2) {
    	//Write the two addresses into the 'To' textfield
    	//TODO: Determine correct locator
    	WebElement toInput = senderDriver.findElement(By.xpath(""));
    	(new WebDriverWait(senderDriver, 20)).until(ExpectedConditions.elementToBeClickable(toInput));
    	toInput.sendKeys(address1+ ";" + address2);
    }
    
    @And("I add {string} as an image to the email as an attachment")
    public void addImage(String image) {
    	//Upload image with its url (built from the current directory + the argument)
    	//TODO: Determine correct locator. The method used might not work.
    	WebElement uploadElement = senderDriver.findElement(By.cssSelector("div [background-image=''"));
    	uploadElement.sendKeys();
    }
    
    @And("I send the email")
    public void clickSend() {
    	//Click the send button
    	//TODO: Determine correct locator
    	WebElement sendButton = senderDriver.findElement(By.xpath("//div[text()='Send']"));
    	(new WebDriverWait(senderDriver, 20)).until(ExpectedConditions.elementToBeClickable(sendButton));
    	sendButton.click();
    	logout(senderDriver);
    }
    
    @Then("the recipient {string} receives the email with {string} attached")
    public void confirmEmailReception(String address, String image) {
    	//Login as the receiver
    	WebDriver driver = login(address);
    	//TODO: verify that the email has been received and has the image attachment
    	logout(driver);
    }
    
    @Then("the recipients {string} and {string} receive the email with {string} attached")
    public void confirmEmailReception(String address1, String address2, String image) {
    	confirmEmailReception(address1, image);
    	confirmEmailReception(address2, image);
    }
    
    @Then("I receive an email explaining that the address could not be found")
    public void confirmEmailReception() {
    	WebDriver driver = login(senderAddress);
    	logout(driver);
    }
    
    public WebDriver login(String address) {
    	
    	//Initialize driver
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        
        //Go to google signing page
        driver.get("https://accounts.google.com/signin");
        
        //Enter username (email address) and click next
        WebElement addressInput = driver.findElement(By.xpath("//input[@id='identifierId']"));
        addressInput.sendKeys(address);
        WebElement indentifierNext = driver.findElement(By.id("identifierNext"));
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(indentifierNext));
        indentifierNext.click();
        
        //Enter password and click next
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.sendKeys(password);
        WebElement passwordNext = driver.findElement(By.id("passwordNext"));
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(passwordNext));
        passwordNext.click();
        
        //Open Gmail
        driver.get("https://mail.google.com/mail/#inbox");
        
        //Click Compose
        WebElement composeButton = driver.findElement(By.xpath("//div[@role='button'and text()='Compose']"));
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(composeButton));
        composeButton.click();
        
        return driver;
    }
    
    public void logout(WebDriver driver) {
    	driver.get("https://accounts.google.com/Logout");
    	driver.close();
    }
}