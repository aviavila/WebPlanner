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


public class sanitytesT {


    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("sanitytesT", "sanitytesT");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mit4mit.co.il/");
    }

    @After
    public void tearDown() throws Exception {
        extent.flush();
        driver.quit();
    }

    @Test
    public void sanity() throws Exception {

        WebElement home = driver.findElement(By.xpath("//div[@class='fullWidthHeader']//img[@alt='mit4mit']"));
        home.click();

        if (driver.getTitle().equals("מתחתנים למען מתחתנים - חוות דעת והמלצות אמיתיות")) {
            ttb.pass("test 1 - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail("test 1 - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }

    }
}


