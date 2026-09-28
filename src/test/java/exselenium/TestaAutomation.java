package exselenium;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestaAutomation {

    protected WebDriver driver;
    
    // step 1 open browser
    @BeforeEach
    public void createDriver() {  
        driver = new ChromeDriver();
        driver.get("http://automationexercise.com"); // step 2 open page
    }   

    @Test
    public void testLoginWithIncorrectCredentials() {
        // step 3 verify if home is visible
        String homeTitle = driver.getTitle();
        assertTrue(homeTitle.contains("Automation Exercise"), "Home page isn't visible!");

        // step 4 click on Signup / Login button
        WebElement loginNavButton = driver.findElement(By.xpath("//a[contains(text(), 'Signup / Login')]"));
        loginNavButton.click();

        // step 5 verify login to your account is visible
        WebElement loginHeading = driver.findElement(By.xpath("//h2[text()='Login to your account']"));
        assertTrue(loginHeading.isDisplayed(), "text 'Login to your account' isn't visible!");

        // step 6 enter incorrect email address and password
        WebElement emailInput = driver.findElement(By.cssSelector("form[action='/login'] input[name='email']"));
        WebElement passwordInput = driver.findElement(By.cssSelector("form[action='/login'] input[name='password']"));
        
        emailInput.sendKeys("invalidemail@test.com");
        passwordInput.sendKeys("incorrectpassword123");

        // step 7 click login button
        WebElement loginButton = driver.findElement(By.cssSelector("form[action='/login'] button[type='submit']"));
        loginButton.click();

        // step 8 verify error your email or password is incorrect! is visible
        WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
        
        assertTrue(errorMessage.isDisplayed(), "error message isn't visible!");
        assertEquals("Your email or password is incorrect!", errorMessage.getText());
    }
    
    // close window after test
    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}