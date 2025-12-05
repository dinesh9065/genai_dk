package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoginPage;
import java.time.Duration;

public class test {
    private WebDriver driver;
    private LoginPage loginPage;
    private static ExtentReports extent;
    private ExtentTest test;


    @BeforeMethod
    public void setup() {
        // Read config
        String url = "https://opensource-demo.orangehrmlive.com/";
String browser = "chrome";
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

        // Perform actions
        loginPage.setUsername("Admin");
        loginPage.setPassword("admin123");
        loginPage.clickLogin();

        // Basic assertion: after login, URL should change or title contains 'Dashboard' - this demo uses URL check
        String currentUrl = driver.getCurrentUrl();
        
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
