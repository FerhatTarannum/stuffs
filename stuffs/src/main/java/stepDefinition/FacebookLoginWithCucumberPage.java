package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.SetUp;

public class FacebookLoginWithCucumberPage extends SetUp {

	By emailOrPhone = By.name("email");
	By pword = By.name("pass");
	By logInButton = By.xpath("//label[text()='Password']/following::input[3]");

	WebDriver driver = null;

	public FacebookLoginWithCucumberPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser
	}

	@When("^I enter valid username and valid password$")
	public void i_enter_valid_username_and_valid_password(String username, String password) throws Throwable {
		driver.findElement(emailOrPhone).sendKeys(username);
		driver.findElement(pword).sendKeys(password);

	}

	@Then("^user should be able to login successfully$")
	public void user_should_be_able_to_login_successfully() throws Throwable {
		driver.findElement(logInButton).click();

	}

}
