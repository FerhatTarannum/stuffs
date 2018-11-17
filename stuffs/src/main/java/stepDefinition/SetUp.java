package stepDefinition;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import cucumber.api.java.en.Given;

public class SetUp {
	
	public WebDriver driver = null;

	
	@Given("^Open browser and start application$")
	public void open_browser_and_start_application() throws Throwable {
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

	
}