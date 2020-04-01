import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.util.Assert;

import javax.jws.WebResult;

public class ChromeSeleniumWalkthrough {

    public static final String PET_CLINIC_WINDOW_TITLE = "PetClinic :: a Spring Framework demonstration";

    public static void main(String[] args) {

        // Initialize WebDriver
        WebDriver driver = getWebDriver(args);

        try {
            // start with the initial page
            driver.get("http://localhost:9192");

            String actualWindowTitle = driver.getTitle();
            Assert.isTrue(actualWindowTitle.equalsIgnoreCase(PET_CLINIC_WINDOW_TITLE)
                , "Title unexpected: " + actualWindowTitle);


            Point xyWindowPosition = driver.manage().window().getPosition();
            Dimension windowDimension = driver.manage().window().getSize();

            System.out.println("X: now is " + xyWindowPosition.getX() );
            System.out.println("Y: now is " + xyWindowPosition.getY() );


            // instead of
            // driver.manage().window().fullscreen(); // which will get rid of all window controls
            driver.manage().window().maximize();

            xyWindowPosition = driver.manage().window().getPosition();
            System.out.println("X: now is " + xyWindowPosition.getX() );
            System.out.println("Y: now is " + xyWindowPosition.getY() );


            WebElement displayElement = driver.findElement(By.className("col-md-12"));
            Assert.notNull(displayElement, "Can not find elment by name.");

            displayElement = driver.findElement(By.className("img-responsive"));
            Assert.notNull(displayElement, "No image on screen");

            displayElement = driver.findElement(By.id("main-navbar"));
            Assert.notNull(displayElement, "Navigation bar not found");
            Assert.isTrue(displayElement.isEnabled() && displayElement.isDisplayed(), "NavBar not visible");

            // displayElement.findElement(By.linkText("Find Owners")).click();

            driver.navigate().to("http://localhost:9192/owners/find.html");
            displayElement = driver.



        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // driver.close();
            driver.quit();
        }
    }

    private static WebDriver getWebDriver(String[] args) {
        WebDriver driver = null;
        if (args.length > 0) {
            if ("-c".equalsIgnoreCase(args[0])) {
                driver = new ChromeDriver();
            }
        } else {
            System.setProperty("webdriver.gecko.driver", "/opt/selenium/webdriver/bin/geckodriver");
            driver = new FirefoxDriver();
        }
        return driver;
    }
}
