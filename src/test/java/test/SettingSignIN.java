package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class SettingSignIN {

    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("SettingSignIN", "SettingSignIN");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wedapp.mit4mit.co.il/home");
    }

    @After
    public void tearDown() throws Exception {
        extent.flush();
        driver.quit();
    }

    @Test
    @Order(1)
    public void signing() throws Exception {

//להרשם בדף הרשמה לפי מייל
        driver.findElement(xpath("//a[contains(@class,'wedappReg wow')]")).click();
        Thread.sleep(2000);
        driver.switchTo().frame("//*[@id=\"ngConnect\"]").findElement(xpath("/html/body/app-root/app-login/div[1]/span/span")).click();
        Thread.sleep(3000);
        driver.findElement(className("userEmail")).sendKeys("GHFGGF@GMAIL.COM");
        Thread.sleep(3000);
        driver.findElement(xpath(" //*[@id=\"3\"]")).click();
        Thread.sleep(3000);
        driver.findElement(xpath("//*[@id=\"userpassword\"]")).sendKeys("1965161561");
        Thread.sleep(3000);
        driver.findElement(xpath(" //*[@id=\"3\"]")).click();
        Thread.sleep(3000);

        driver.findElement(xpath("  /html/body/app-root/app-completed/div[1]/button")).click();
        Thread.sleep(3000);


    }



}
