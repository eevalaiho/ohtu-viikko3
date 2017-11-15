package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertTrue;

public class Stepdefs {

    WebDriver driver = null;
    String baseUrl = "http://localhost:4567";

    @Before
    public void setupClass() {
        System.setProperty("webdriver.chrome.driver", "/home/local/laihoeev/chromedriver");
        driver = new ChromeDriver();
    }
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();          
    } 

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("^nonexistend username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistend_username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Given("^command new user is selected$")
    public void command_new_user_is_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_valid_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        createUser(username, password, password);
    }

    @Then("^a new user is created$")
    public void a_new_user_is_created() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    void createUser(String username, String password, String passwordConfirmation) {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    @When("^too short username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void too_short_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        createUser(username, password, password);
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_is_not_created_and_error_is_reported(String message) throws Throwable {
        pageHasContent(message);
    }

    @When("^a reserved username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void a_reserved_username_and_password_and_matching_password_confirmation_are_entered(String username, String password) throws Throwable {
        createUser(username, password, password);
        sleep(5);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and password \"([^\"]*)\" confirmation are entered$")
    public void a_valid_username_and_password_and_password_confirmation_are_entered(String username, String password, String passwordConfirmation) throws Throwable {
        createUser(username, password, passwordConfirmation);
        sleep(5);
    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_successfully_created(String username, String password) throws Throwable {
        command_new_user_is_selected();
        a_valid_username_and_password_and_password_confirmation_are_entered(username, password, password);
        a_new_user_is_created();
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void user_with_username_and_password_is_tried_to_be_created(String username, String password) throws Throwable {
        command_new_user_is_selected();
        too_short_username_and_password_and_matching_password_confirmation_are_entered(username, password);
        sleep(2);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
        sleep(2);
        login_selected();
        sleep(2);
        username_and_password_are_given(username, password);
        sleep(2);
        user_is_not_logged_in_and_error_message_is_given();
        sleep(2);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    private static void sleep(int n){
        try{
            //Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
