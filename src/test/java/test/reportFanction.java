package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class reportFanction {
    public WebDriver driver;


    public reportFanction(WebDriver driver){
        this.driver=driver;
    }
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter htmlReporter;

    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    static Date today = Calendar.getInstance().getTime();
    static String reportDate = df.format(today);
    public static String filePath = "d:\\"+reportDate+"\\exReport.html";


    public static ExtentReports GetExtent(){
        new File("d:\\"+ reportDate).mkdirs();
        if (extent != null)
            return extent; //avoid creating new instance of html file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }



    private static ExtentSparkReporter getHtmlReporter() {

        htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setDocumentTitle("planner report");
        htmlReporter.config().setReportName("Regression cycle");
        htmlReporter.config().setEncoding("windows-1255");
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description){
        test = extent.createTest(name, description);
        return test;
    }

    public static String CaptureScreen(WebDriver driver) throws AWTException, IOException
    {
        LocalDateTime now = LocalDateTime.now();
        String  time =now.format(DateTimeFormatter.ofPattern("ddHHmmss"));
        String folderPath = ("d:\\"+reportDate);
        String imagePath   = folderPath  +"/pic" +time +".jpg";

        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(imagePath);
        try
        {
            FileUtils.copyFile(oScnShot, oDest);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


        return imagePath;
    }

}


