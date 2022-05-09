package patientintake.notifier;

import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpcomingAppointmentNotifier {

    private final ClinicCalendar calendar;
    private final EmailNotifier notifier;

    public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier) {
        this.calendar = calendar;
        this.notifier = notifier;
    }

    public void run() {
        calendar.getTomorrowAppointments().forEach(this::sendNotificationForAppointment);
    }

    private void sendNotificationForAppointment(PatientAppointment appt) {
        String email = appt.getPatientLastName().toLowerCase()
                + appt.getPatientFirstName().toLowerCase()
                + "@mail.com";
        System.out.println("Sending with body: " + buildMessageBody(appt));
        notifier.sendNotification("Appointment Reminder", buildMessageBody(appt), email);
    }

    private String buildMessageBody(PatientAppointment appt) {
        return "You have an appointment tomorrow at "
                + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
                + " with Dr. "
                + appt.getDoctor().getName() + ".";
    }
}
