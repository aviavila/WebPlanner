package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.xpath;

public class upToolBar {

    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("upToolBar", "upToolBar");
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
//צריך להפוך את הלוגאין לעובד
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


    @Test
    public void writeReview () throws Exception {
        driver.findElement(By.linkText("+ כתיבת חוות דעת")).click();

        if (driver.getTitle().equals("מתחתנים למען מתחתנים - כתיבת חוות דעת")) {
            ttb.pass("test wirteReview - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test writeReview - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }



    }

    @Test
    public void showMakolLeiruaPopup() throws Exception {
        driver.findElement(By.linkText("מקום לארוע")).click();

        if (driver.getTitle().equals("מקום לאירוע - כל מה שצריך לבחירת מקום לחתונה המושלמת")) {
            ttb.pass("test showMakolLeiruaPopup - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test showMakolLeiruaPopup - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }
    }

    @Test
    public void showSapakimPopup() throws Exception {
        driver.findElement(By.linkText("כל נותני השירות")).click();
        if (driver.getTitle().equals("כל הספקים לארגון החתונה - מתחתנים למען מתחתנים")) {
            ttb.pass("test showSapakimPopup- home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test showSapakimPopup - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }
    }

    @Test
    public void showWedappPopup() throws Exception {

        driver.findElement(By.xpath("//img[@alt='wedplanner']")).click();
        if (driver.getTitle().equals("מתחתנים למען מתחתנים - חוות דעת והמלצות אמיתיות")) {
            ttb.pass("test showWedappPopup - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test showWedappPopup - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }
    }

    @Test
    public void wedappHeaderLinks() throws Exception {
        driver.findElement(By.xpath("(//img[@alt='wedmarket'])[2]")).click();
        if (driver.getTitle().equals("   הטבות ומבצעים - מתחתנים למען מתחתנים ")) {
            ttb.pass("test wedappHeaderLinks - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test wedappHeaderLinks - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }
    }

    @Test
    public void wedblog() throws Exception {

        driver.findElement(By.linkText("בלוג חתונות")).click();
        if (driver.getTitle().equals("מתחתנים למען מתחתנים - חוות דעת והמלצות אמיתיות")) {
            ttb.pass("test wedblog - home page", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        } else {
            ttb.fail(" test wedblog - cant open", MediaEntityBuilder.createScreenCaptureFromPath(rf.CaptureScreen(driver)).build());
        }
    }

    @Test
    public void showExtraLinksHover() throws Exception {
        driver.findElement(By.xpath("//span[text()[normalize-space()='...']]")).click();
        //צריך להוסיף תמונת מסך ללוג
    }


}
