package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.SetUp;

public class FacebookLoginWithCucumberPage extends SetUp {

	By emailOrPhone = By.name("email");
	By password = By.name("pass");
	By logInButton = By.xpath("//label[text()='Password']/following::input[3]");

	WebDriver driver = null;

	public FacebookLoginWithCucumberPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		
		this.driver = driver;// Calling Browser
	}

	@When("^I enter valid username$")
	public void i_enter_valid_username_and_valid_password(String uname, String pword) throws Throwable {
		driver.findElement(emailOrPhone).sendKeys(uname);
		driver.findElement(password).sendKeys(pword);
		throw new PendingException();
	}

	@Then("^User should be able to login successfully$")
	public void user_should_be_able_to_login_successfully() throws Throwable {
		driver.findElement(logInButton).click();
		throw new PendingException();
	}

}
