package patientintake.notifier;

import java.util.Properties;

public class SmtpMessageSender implements EmailNotifier {
    @Override
    public void sendNotification(String subject, String body, String address) {
        // Code that sends an actual email using an external service
    }
}
