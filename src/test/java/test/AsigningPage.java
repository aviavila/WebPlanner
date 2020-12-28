package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.paulhammant.ngwebdriver.ByAngular;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.var;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.openqa.selenium.By.*;

public class AsigningPage {

    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("signingPage", "signingPage");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mit4mit.co.il/wedapp/");
    }

    @After
    public void tearDown() throws Exception {
        extent.flush();
        driver.quit();
    }


    @Test
    @Order(0)
    public void signing() throws Exception {
//להרשם בדף הרשמה לפי מייל
        driver.get("https://www.mit4mit.co.il/login/main/?last_page=/wedapp/?open=true");
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.id("ngConnect")));
        WebElement mail = driver.findElement(By.className("emailToggle"));
        mail.click();
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("document.getElementById('useremail').value='ayjtgdgfh@gmail.co';");
        Thread.sleep(5000);
        Robot rob = new Robot();
        rob.keyPress(KeyEvent.VK_TAB);
        rob.keyRelease(KeyEvent.VK_TAB);
        rob.keyPress(KeyEvent.VK_RIGHT);
        rob.keyRelease(KeyEvent.VK_RIGHT);
        rob.keyPress(KeyEvent.VK_M);
        rob.keyRelease(KeyEvent.VK_M);
        Thread.sleep(2000);
        driver.findElement(By.id("3")).click();
        Thread.sleep(5000);
        driver.findElement(By.name("userpassword")).sendKeys("sadasd213131");
        Robot robs = new Robot();
        robs.keyPress(KeyEvent.VK_TAB);
        robs.keyRelease(KeyEvent.VK_TAB);
        robs.keyPress(KeyEvent.VK_RIGHT);
        robs.keyRelease(KeyEvent.VK_RIGHT);
        robs.keyPress(KeyEvent.VK_M);
        robs.keyRelease(KeyEvent.VK_M);
        Thread.sleep(3000);
        driver.findElement(By.id("3")).click();
        Thread.sleep(3000);
        driver.findElement(xpath("/html/body/app-root/app-completed/div[1]/button")).click();
        Thread.sleep(3000);

        if (driver.getTitle().equals("ברוך הבא - מתחתנים למען מתחתנים")) {
            ttb.pass("test 1 - login page pass without mail Authentication", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail("test 1 - can't open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }

        driver.switchTo().frame(driver.findElement(By.id("cboxLoadedContent")));
        driver.findElement(By.id("BrideName")).click();
        driver.findElement(By.id("BrideName")).sendKeys("יוכבד");
        driver.findElement(By.id("GroomName")).click();
        driver.findElement(By.id("GroomName")).sendKeys("נבוכדנצר");
        driver.findElement(By.className("unknownWeddingDate")).click();
        driver.findElement(By.id("addNewUSER")).click();
        driver.findElement(tagName("button")).click();

        if (driver.getClass().equals("מה עושים עכשיו? ")) {
            ttb.pass("test 1 - sign up successful", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(new RuntimeException("A runtime exception occurred!"));
        }
    }


}
