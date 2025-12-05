package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object for OrangeHRM Login Page.
 * Element locating follows priority: id -> link text (for links/buttons) -> XPath.
 * CamelCase used for element names and meaningful action methods provided.
 */
public class LoginPage {
    private WebDriver driver;

    // Username input located by XPath (no id present in DOM)
    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInput;

    // Password input located by XPath
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    // Login button located by XPath (button with type=submit and text contains 'Login')
    @FindBy(xpath = "//button[@type='submit' and normalize-space(.)='Login']")
    private WebElement loginButton;

    // Username label
    @FindBy(xpath = "//label[normalize-space(text())='Username']")
    private WebElement usernameLabel;

    // Password label
    @FindBy(xpath = "//label[normalize-space(text())='Password']")
    private WebElement passwordLabel;

    // Constructor - initializes PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Interactions

    public void setUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getUsernameLabelText() {
        return usernameLabel.getText();
    }

    public String getPasswordLabelText() {
        return passwordLabel.getText();
    }
}
