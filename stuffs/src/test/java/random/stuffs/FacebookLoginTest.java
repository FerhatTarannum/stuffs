package random.stuffs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import utility.SetUp;

public class FacebookLoginTest extends SetUp {

	final static Logger logger = Logger.getLogger(FacebookLoginTest.class);

	// Description:

	@BeforeTest

	public void browserLaunch() throws IOException, InterruptedException {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test

	public void facebookLoginTest() throws Exception {

		test = extent.createTest("addingNewBookingListTest");

		FacebookLoginPage facebookLoginPage = new FacebookLoginPage(driver);

		test.log(Status.INFO, "Entering email.........");
		facebookLoginPage.enterEmailOrPhone("ft.31012013@gmail.com");

		test.log(Status.INFO, "Entering Password.........");
		facebookLoginPage.enterPassword("Ferhat@123");

		test.log(Status.INFO, "Clicking on Submit button.........");
		facebookLoginPage.clickOnLogInButton();

	}

}
