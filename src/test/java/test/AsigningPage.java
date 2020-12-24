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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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
//צריך להפוך את הלוגאין נתקעת בלחצן אחריי המייל

    @Test
    @Order(1)
    public void signing() throws Exception {

//להרשם בדף הרשמה לפי מייל
        driver.get("https://www.mit4mit.co.il/login/main/?last_page=/wedapp/?open=true");
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.id("ngConnect")));
        WebElement mail = driver.findElement(By.className("emailToggle"));
                   mail.click();
        Thread.sleep(1000);
        WebElement mail2 = driver.findElement(By.name("userEmail"));
                   mail2.sendKeys("aadfdfa@fadf.com");
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.id("ngConnect")));
        WebElement mail3 = driver.findElement(By.id("3"));
                   mail3.click();
        driver.switchTo().frame(driver.findElement(By.id("ngConnect")));
        driver.findElement(By.name("userEmail")).sendKeys("1965161561");
        Thread.sleep(3000);
        WebElement mail4 = driver.findElement(By.id("3"));
                   mail4.click();
        Thread.sleep(3000);

        driver.findElement(xpath("/html/body/app-root/app-completed/div[1]/button")).click();
        Thread.sleep(3000);


    }

    @Test
    @Order(2)
    public void signup() throws Exception {

            driver.switchTo().frame(driver.findElement(By.name("1")));

            driver.findElement(By.xpath("//*[@id=\"BrideName\"]")).click();
            driver.findElement(By.id("BrideName")).sendKeys("יוכבד");
            driver.findElement(By.xpath(" //*[@id=\"GroomName\"]")).click();
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
