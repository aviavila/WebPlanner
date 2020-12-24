package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.junit.platform.commons.function.Try;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class reportFanction {
    public WebDriver driver;


    public reportFanction(WebDriver driver) {
        this.driver = driver;
    }

    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter htmlReporter;

    static DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    static Date today = Calendar.getInstance().getTime();
    static String reportDate = df.format(today);
    public static String filePath = "d:\\" + reportDate + "\\exReport.html";


    public static ExtentReports GetExtent() {
        new File("d:\\" + reportDate).mkdirs();
        if (extent != null)
            return extent; //avoid creating new instance of html file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }

    private static ExtentSparkReporter getHtmlReporter() {

        htmlReporter = new ExtentSparkReporter(filePath);
        htmlReporter.config().setDocumentTitle("WEBplanner report");
        htmlReporter.config().setReportName("AVI MEDINA");
        htmlReporter.config().setEncoding("windows-1255");
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description) {
        test = extent.createTest(name, description);
        return test;
    }

    public static String CaptureScreen(WebDriver driver) throws AWTException, IOException {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("ddHHmmss"));
        String folderPath = ("d:\\" + reportDate);
        String imagePath = folderPath + "/pic" + time + ".jpg";

        TakesScreenshot oScn = (TakesScreenshot) driver;
        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
        File oDest = new File(imagePath);
        try {
            FileUtils.copyFile(oScnShot, oDest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        return imagePath;
    }

    public static String[] multi_addressee = {"USER1@gmail.com", "USER2@gmail.com"};

    {
        String toCc = "avililian1123@gmail.com";
        String mailFrom = "avililian1123@gmail.com";
        String subject = "exReport";
        String BodyText = "new testReport";
        String file_path = "d:\\" + reportDate + "\\exReport.html";
        String userName = "avililian112@gmail.com";
        String password = "01121995";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom));
            InternetAddress[] addressTo = new InternetAddress[multi_addressee.length];
            for (int i = 0; i < multi_addressee.length; i++) {
                message.addRecipient(Message.RecipientType.TO, new
                        InternetAddress(multi_addressee[i]));
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(toCc));
                message.setSubject(subject);
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(BodyText);
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(file_path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(file_path);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
                Transport.send(message);
                System.out.println("message sent successfully");


            }

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("message sent fails");
        }

    }
}