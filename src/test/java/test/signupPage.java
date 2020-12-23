package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.By.xpath;

public class signupPage {
    static WebDriver driver;
    public reportFanction rf = new reportFanction(driver);
    static ExtentReports extent;
    static ExtentTest ttb;

    @Before
    public void setUp() throws Exception {

        extent = rf.GetExtent();
        ttb = rf.createTest("signupPage", "signupPage");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mit4mit.co.il//wedapp/?open=true");
    }

    @After
    public void tearDown() throws Exception {
        extent.flush();
        driver.quit();
    }

    @Test
    public void test() throws Exception {


        WebElement siGNup = driver.findElement(By.xpath("//*[@id=\"BrideName\"]"));
        siGNup.click();
        siGNup.sendKeys("יוכבד");
        Thread.sleep(3000);

        WebElement siGNup2 = driver.findElement(By.xpath("//*[@id=\"GroomName\"]"));
        siGNup2.click();
        siGNup2.sendKeys("נבוכדנצר");
        Thread.sleep(3000);

        WebElement siGNup3 = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/form/div/div[2]/table/tbody/tr[4]/td[2]/input"));
        siGNup2.click();
        Thread.sleep(3000);

        WebElement siGNup4 = driver.findElement(By.xpath(" /html/body/div[27]/div[1]/div[2]/div[2]/div[1]/iframe"));
        siGNup4.click();
        Thread.sleep(3000);

    }
}