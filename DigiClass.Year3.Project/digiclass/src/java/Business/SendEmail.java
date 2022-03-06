package Business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author junta
 */
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.internet.AddressException;
import org.codemonkey.simplejavamail.Email;

public class SendEmail {

    public static String sendEmail(String email, String hashCode) throws AddressException, MessagingException {
        final String username = "digiclassdtc@gmail.com";
        final String password = "jzditcfghwntrdgt";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println(new PasswordAuthentication(username, password));
                return new PasswordAuthentication(username, password);

            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("juntan831@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Digiclass - Reset Password");
            message.setText("Dear, " + email
                    + "\n\n This is the link for reseting your password :"
                    + "\n\n http://localhost:8084/Digiclass1/controller?action=forgetPasswordLink&email=" + email + "&token=" + hashCode
                    + "\n\n From Digiclass."
                    + "\n\n Sincerly.");

            message.setSentDate(new Date());

            Transport.send(message);
        } catch (Exception ex) {
            return ex.toString();
        }
        return "Done";
    }

}
