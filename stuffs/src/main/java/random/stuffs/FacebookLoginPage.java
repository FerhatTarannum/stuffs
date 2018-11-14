package random.stuffs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;


public class FacebookLoginPage extends SetUp{

	By emailOrPhone = By.name("email");
	By password = By.name("pass");
	By logInButton = By.xpath("//label[text()='Password']/following::input[3]");

	WebDriver driver = null;

	public FacebookLoginPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser
	}
	
	public void enterEmailOrPhone(String uname) {
		driver.findElement(emailOrPhone).sendKeys(uname);
	}
	
	public void enterPassword(String pword) {
		driver.findElement(password).sendKeys(pword);
	}

	public void clickOnLogInButton() {
		driver.findElement(logInButton).click();
	}

}
