package stepDefinition;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import stepDefinition.FacebookLoginWithCucumberPage;
import utility.SetUp;

public class FacebookLoginWithCucumberTest extends SetUp {

	final static Logger logger = Logger.getLogger(FacebookLoginWithCucumberTest.class);

	// Description:

	@BeforeTest

	public void browserLaunch() throws IOException, InterruptedException {

		open_browser_and_start_application();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	private void open_browser_and_start_application() {
		// TODO Auto-generated method stub
		
	}

	@Test

	public void facebookLoginTest() throws Throwable {

		test = extent.createTest("FacebookLoginWithCucumberTest");

		FacebookLoginWithCucumberPage facebookLoginWithCucumberPage = new FacebookLoginWithCucumberPage(driver);

		test.log(Status.INFO, "Entering email and password.........");
		facebookLoginWithCucumberPage.i_enter_valid_username_and_valid_password("ft.31012013@gmail.com","Ferhat@123");

		test.log(Status.INFO, "Clicking on Submit button.........");
		facebookLoginWithCucumberPage.user_should_be_able_to_login_successfully();

	}

}
