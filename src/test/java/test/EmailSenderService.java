package test;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class EmailSenderService {
    private final Properties properties = new Properties();

    String toCc = "avililian1123@gmail.com";
    String subject = "report WEBplanner";
    String BodyText = "tnx";
    private String password = "01121995";
    String file_path = "d;//exReport.html";
    String userName = "avililian112@gmail.com";

    private Session session;

    private void init() {

        //ssl
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("eu***@gmail.com",password);
                    }
                });
    }

    public void sendEmail(){

        init();

        //ssl
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("avililian112@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("avililian1123@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler," +
                    "\n\n No spam to my email, please!");
            Transport t = session.getTransport("smtp");
            t.connect("smtp.gmail.com", "avililian112@gmail.com", password);
            t.sendMessage(message, message.getAllRecipients());
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
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}