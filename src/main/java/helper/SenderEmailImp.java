package helper;

import com.aspose.email.MailAddress;
import com.aspose.email.MailMessage;
import com.aspose.email.SmtpClient;

public class SenderEmailImp implements ISenderEmail {
    @Override
    public void sendEmail(String body) {
        MailMessage message = new MailMessage();

// Set From field, To field and Plain text body
        message.setFrom(MailAddress.to_MailAddress("ppejalvarado@gmail.com"));
        message.getTo().add("jese.alvarado@outlook.com");
        message.setBody(body);

// Create an instance of the SmtpClient class
        SmtpClient client = new SmtpClient();

// And Specify your mailing host server, Username, Password and Port
        client.setHost("smtp.gmail.com");
        client.setUsername("ppejalvarado@gmail.com");
        client.setPassword("P2ssw.rd");
        client.setPort(587);
        //client.setSecurityOptions(SecurityOptions.SSLExplicit);

        try {
            // Client.Send will send this message
            client.send(message);
            System.out.println("Message sent");
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
