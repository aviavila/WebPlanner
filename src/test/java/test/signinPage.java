package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.*;

public class signinPage {

    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("signinPage", "signinPage");
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
    public void test() throws Exception {

        WebElement SIGN = driver.findElement(xpath("//a[contains(@class,'wedappReg wow')]"));
        SIGN.click();
        Thread.sleep(3000);

        WebElement SIGN2 =driver.findElement(By.className("emailToggle"));
        SIGN2.click();
        Thread.sleep(3000);

        driver.findElement(className("userEmail")).sendKeys("GHFGGF@GMAIL.COM");
        Thread.sleep(3000);

        WebElement SIGN3 = driver.findElement(xpath(" //*[@id=\"3\"]"));
        SIGN3.click();
        Thread.sleep(3000);

        WebElement SIGN4 = driver.findElement(xpath("//*[@id=\"userpassword\"]"));
        SIGN4.sendKeys("1965161561");
        Thread.sleep(3000);

        WebElement SIGN5 = driver.findElement(xpath(" //*[@id=\"3\"]"));
        SIGN5.click();
        Thread.sleep(3000);

        WebElement SIGN6 = driver.findElement(xpath("  /html/body/app-root/app-completed/div[1]/button"));
        SIGN6.click();
        Thread.sleep(3000);

    }
}
