package patientintake.notifier;

import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

public class EmailNotifierTestDouble implements EmailNotifier {

    ArrayList<Message> receivedMessages = new ArrayList<>();

    @Override
    public void sendNotification(String subject, String body, String address) {
        receivedMessages.add(new Message(address, subject, body));
    }

    static class Message {
        public String to;
        public String subject;
        public String body;

        public Message(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }
    }
}
