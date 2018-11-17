package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cucumber.api.java.en.Given;

public class SetUp {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public WebDriver driver = null;

	
	public void mysetUp() throws IOException {

		Properties p = new Properties();
		FileInputStream fi = new FileInputStream(
				 "C:\\Users\\Ferhat\\git\\repository\\stuffs\\Config Folder\\global.properties");
		p.load(fi);

		

		System.out.println(p.getProperty("browser"));

		if (p.getProperty("browser").contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\XtraSoftwares\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (p.getProperty("browser").contains("chrome")) {

			System.setProperty("webdriver.chrome.driver", "C:\\XtraSoftwares\\chromedriver_win32\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			// To run scripts in headless mode on jenkins
			// options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver();
		} else {
			Internetexplorer();
		}
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}

	private void Internetexplorer() {
		// TODO Auto-generated method stub
	}

	@BeforeSuite(alwaysRun = true)
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter
				("C:\\Users\\Ferhat\\git\\repository\\stuffs\\test-report\\AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Host Name", "LAPTOP-68GSKGTP");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Ferhat");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
		htmlReporter.config().setReportName("Automation Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);

	}
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			/*
			 * test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()
			 * +" Test case FAILED due to below issues:", ExtentColor.RED));
			 * test.fail(result.getThrowable());
			 */

			String screenShotPath = CaptureScreenshot.captureScreenshot(driver, result.getName());
			System.out.println(screenShotPath);
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Failure Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

			/*
			 * String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
			 * test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()
			 * +" Test case FAILED due to below issues:", ExtentColor.RED));
			 * test.fail(result.getThrowable()); test.fail("Snapshot below: " +
			 * test.addScreenCaptureFromPath(screenShotPath));
			 */
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterSuite(alwaysRun = true)

	public void tearDown() {
		extent.flush();

		/*
		 * driver.get("file://" + System.getProperty("user.dir") +
		 * "/test-report/AutomationReport.html"); driver.quit();
		 */

	}
}