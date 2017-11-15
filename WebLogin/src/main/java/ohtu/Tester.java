package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.UUID;

public class Tester {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/home/local/laihoeev/chromedriver");

        // Default scenario
        RunDefautScenario();

        sleep(3);

        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        RunScenario1();

        sleep(3);

        // epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
        RunScenario2();

        sleep(3);

        // uuden käyttäjätunnuksen luominen
        RunScenario3();

        sleep(3);

        // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
        RunScenario4();

        sleep(3);

    }

    // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
    static void RunScenario1() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep123");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);

        driver.quit();

    }

    // epäonnistunut kirjautuminen: ei-olemassaoleva käyttäjätunnus
    static void RunScenario2() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(getRandomString(8));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);

        driver.quit();

    }

    // uuden käyttäjätunnuksen luominen
    static void RunScenario3() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        String user = getRandomString(8);
        String pwd = getRandomString(8);

        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pwd);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwd);
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(2);
        driver.quit();

    }

    // uuden käyttäjätunnuksen luomisen jälkeen tapahtuva ulkoskirjautuminen sovelluksesta
    static void RunScenario4() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        String user = getRandomString(8);
        String pwd = getRandomString(8);

        element = driver.findElement(By.name("username"));
        element.sendKeys(user);
        element = driver.findElement(By.name("password"));
        element.sendKeys(pwd);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(pwd);
        element = driver.findElement(By.name("signup"));

        sleep(2);
        element.submit();

        sleep(2);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(2);
        driver.quit();
    }

    static void RunDefautScenario() {

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);

        driver.quit();
    }

    public static String getRandomString(int n) {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "").substring(0, n);
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
