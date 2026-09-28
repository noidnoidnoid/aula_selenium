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
import org.openqa.selenium.support.ui.Select;

public class TestaAutomation {

    protected WebDriver driver;

    // step 1 open browser
    @BeforeEach
    public void createDriver() {
        driver = new ChromeDriver();
        driver.get("http://automationexercise.com"); // step 2 open page
    }

    @Test
    public void testRegisterUser() {
        // step 3 verify if home is visible
        String homeTitle = driver.getTitle();
        assertTrue(homeTitle.contains("Automation Exercise"), "Home page isn't visible!");

        // step 4 click on Signup / Login button
        WebElement loginNavButton = driver.findElement(By.xpath("//a[contains(text(), 'Signup / Login')]"));
        loginNavButton.click();

        // step 5 verify New User Signup! is visible
        WebElement signupHeading = driver.findElement(By.xpath("//h2[text()='New User Signup!']"));
        assertTrue(signupHeading.isDisplayed(), "text 'New User Signup!' isn't visible!");

        // step 6 enter name and email address
        String userName = "test user";
        String uniqueEmail = "user_" + System.currentTimeMillis() + "@test.com";

        WebElement nameInput = driver.findElement(By.cssSelector("form[action='/signup'] input[name='name']"));
        WebElement emailInput = driver.findElement(By.cssSelector("form[action='/signup'] input[name='email']"));

        nameInput.sendKeys(userName);
        emailInput.sendKeys(uniqueEmail);

        // step 7 click Signup button
        WebElement signupButton = driver.findElement(By.cssSelector("form[action='/signup'] button[type='submit']"));
        signupButton.click();

        // step 8 verify ENTER ACCOUNT INFORMATION is visible
        WebElement enterAccountHeading = driver.findElement(By.xpath("//b[text()='Enter Account Information']"));
        assertTrue(enterAccountHeading.isDisplayed(), "text 'ENTER ACCOUNT INFORMATION' isn't visible!");

        // step 9 fill details
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("password123");

        new Select(driver.findElement(By.id("days"))).selectByValue("7");
        new Select(driver.findElement(By.id("months"))).selectByValue("3");
        new Select(driver.findElement(By.id("years"))).selectByValue("2005");

        // step 10 select checkbox Sign up for our newsletter!
        driver.findElement(By.id("newsletter")).click();

        // step 11 select checkbox Receive special offers from our partners!
        driver.findElement(By.id("optin")).click();

        // step 12 fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        driver.findElement(By.id("first_name")).sendKeys("First");
        driver.findElement(By.id("last_name")).sendKeys("Last");
        driver.findElement(By.id("company")).sendKeys("Company");
        driver.findElement(By.id("address1")).sendKeys("Address 123");
        driver.findElement(By.id("address2")).sendKeys("Apt 4");

        new Select(driver.findElement(By.id("country"))).selectByVisibleText("Brazil");

        driver.findElement(By.id("state")).sendKeys("Rio de Janeiro");
        driver.findElement(By.id("city")).sendKeys("São Gonçalo");
        driver.findElement(By.id("zipcode")).sendKeys("24710-395");
        driver.findElement(By.id("mobile_number")).sendKeys("+55 21 99999-9999");

        // step 13 click Create Account button
        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

        // step 14 verify ACCOUNT CREATED! is visible
        WebElement accountCreatedText = driver.findElement(By.xpath("//b[text()='Account Created!']"));
        assertTrue(accountCreatedText.isDisplayed(), "text 'ACCOUNT CREATED!' isn't visible!");

        // step 15 click Continue button
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();

        // step 16 verify Logged in as username is visible
        WebElement loggedInText = driver.findElement(By.xpath("//a[contains(text(), 'Logged in as')]"));
        assertTrue(loggedInText.isDisplayed(), "text 'Logged in as username' isn't visible!");

        // step 17 click Delete Account button
        driver.findElement(By.xpath("//a[contains(text(), 'Delete Account')]")).click();

        // step 18 verify ACCOUNT DELETED! is visible and click Continue button
        WebElement accountDeletedText = driver.findElement(By.xpath("//b[text()='Account Deleted!']"));
        assertTrue(accountDeletedText.isDisplayed(), "text 'ACCOUNT DELETED!' isn't visible!");

        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
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