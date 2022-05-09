package patientintake.notifier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Upcoming Appointment Notifier Test")
class UpcomingAppointmentNotifierTest {

    private EmailNotifierTestDouble emailDouble;

    @BeforeEach
    void init() {
        emailDouble = new EmailNotifierTestDouble();
    }

    @Test
    @DisplayName("send email notification with correct format")
    void sendNotificationWithCorrectFormat() {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2022, 5, 9));
        calendar.addAppointment("Mahmoud", "Abdelazim",
                "avery", "5/10/2022 2:00 pm");
        UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);
        notifier.run();
        assertEquals(1, emailDouble.receivedMessages.size());
        EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
        assertAll(
                () -> assertEquals("abdelazimmahmoud@mail.com", expectedMessage.to),
                () -> assertEquals("Appointment Reminder", expectedMessage.subject),
                () -> assertEquals("You have an appointment tomorrow at 2:00 PM with Dr. Ralph Avery.",
                        expectedMessage.body)
        );
    }

}