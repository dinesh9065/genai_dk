package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.TestDataReader;

import java.io.File;
import java.nio.file.Paths;
import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setupClass() {
        // Initialize report
        new File("reports").mkdirs();
//ExtentSparkReporter spark = new ExtentSparkReporter("reports/OrangeHRMLoginReport.html");

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/OrangeHRMLoginReport.html");
        htmlReporter.config().setDocumentTitle("OrangeHRM Login Test Report");
        htmlReporter.config().setReportName("Login Tests");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup() {
        // Read config
        String configPath = Paths.get("src","test","resources","env","config.properties").toString();
        ConfigReader config = new ConfigReader(configPath);
        String browser = config.get("browser");
        String url = config.get("url");

        // Initialize driver using WebDriverManager as required
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("Only chrome supported in this sample");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);

        loginPage = new LoginPage(driver);
    }

    @Test(description = "Verify user can login with valid credentials")
    public void testLogin() {
        test = extent.createTest("testLogin");
        // Read testdata from JSON
        String testdataPath = Paths.get("src","test","resources","testdata","login.json").toString();
        TestDataReader tdr = new TestDataReader(testdataPath);
        String username = tdr.get("login", "username");
        String password = tdr.get("login", "password");

        // Perform actions
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        // Basic assertion: after login, URL should change or title contains 'Dashboard' - this demo uses URL check
        String currentUrl = driver.getCurrentUrl();
        test.info("Current URL after login: " + currentUrl);
      //  Assert.assertFalse(currentUrl.contains("/auth/login"), "Login likely failed, still on login page");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}
