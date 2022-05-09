package patientintake;

import java.time.LocalDateTime;

public class PatientAppointment {

    private final String patientFirstName;
    private final String patientLastName;
    private final LocalDateTime appointmentDateTime;
    private final Doctor doctor;
    private double bmi;

    public PatientAppointment(String patientFirstName, String patientLastName, LocalDateTime appointmentDateTime, Doctor doctor) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getBmi() {
        return bmi;
    }
}
