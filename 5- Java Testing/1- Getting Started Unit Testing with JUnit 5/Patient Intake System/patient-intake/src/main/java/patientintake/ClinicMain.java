package patientintake;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class ClinicMain {

    private static ClinicCalendar calendar;

    public static void main(String[] args) throws Throwable {
        calendar = new ClinicCalendar(LocalDate.now());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake Computer System!\n\n");
        String lastOption = "";
        while (!lastOption.equalsIgnoreCase("x")) {
            lastOption = displayMenu(scanner);
        }
        System.out.println("\nExiting System...\n");
    }

    private static String displayMenu(Scanner scanner) throws IOException {
        System.out.println("Please select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Today's Appointments");
        System.out.println("4. Enter Patient Height and Weight");
        System.out.println("X.  Exit System.");
        System.out.print("Option: ");
        String option = scanner.next();
        switch (option) {
            case "1" -> {
                performPatientEntry(scanner);
                return option;
            }
            case "2" -> {
                performAllAppointments();
                return option;
            }
            case "3" -> {
                performTodayAppointments();
                return option;
            }
            case "4" -> {
                performHeightWeight(scanner);
                return option;
            }
            default -> {
                System.out.println("Invalid option, please re-enter.");
                return option;
            }
        }
    }

    private static void performPatientEntry(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n\nPlease Enter Appointment Info:");
        System.out.print("  Patient Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("  Patient First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("  Appointment Date (M/d/yyyy h:m a): ");
        String when = scanner.nextLine();
        System.out.print("  Doctor Last Name: ");
        String doc = scanner.nextLine();
        try {
            calendar.addAppointment(firstName, lastName, doc, when);
        } catch (Exception t) {
            System.out.println("Error!  " + t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully.\n\n");
    }

    private static void performAllAppointments() throws IOException {
        System.out.println("\n\nAll Appointments in System:");
        for (PatientAppointment appointment : calendar.getAppointments()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.printf("%s:  %s, %s\t\tDoctor: %s%n", apptTime, appointment.getPatientLastName(), appointment.getPatientFirstName(), appointment.getDoctor().getName());
        }
        System.out.println("\nPress any key to continue...");
        System.in.read();
        System.out.println("\n\n");
    }

    private static void performTodayAppointments() throws IOException {
        System.out.println("\n\nToday's Appointments in System:");
        for (PatientAppointment appointment : calendar.getTodayAppointments()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String apptTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.printf("%s:  %s, %s\t\tDoctor: %s%n", apptTime, appointment.getPatientLastName(), appointment.getPatientFirstName(), appointment.getDoctor().getName());
        }
        System.out.println("\nPress any key to continue...");
        System.in.read();
        System.out.println("\n\n");
    }

    private static void performHeightWeight(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n\nEnter patient height and weight for today's appointment");
        System.out.print("Patient Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Patient First Name: ");
        String firstName = scanner.nextLine();
        PatientAppointment appt = findPatientAppointment(lastName, firstName).orElse(null);
        if (appt != null) {
            System.out.print("  Height in inches: ");
            Integer height = scanner.nextInt();
            System.out.print("  Weight in pounds: ");
            Integer weight = scanner.nextInt();
            double bmi = BMICalculator.calculateBmi(height, weight);
            appt.setBmi(bmi);
            System.out.println("Set patient BMI to " + bmi + "\n\n");
        } else {
            System.out.println("Patient not found.\n\n");
        }
    }

    private static Optional<PatientAppointment> findPatientAppointment(String lastName, String firstName) {
        for (PatientAppointment appt : calendar.getAppointments()) {
            if (appt.getPatientLastName().equalsIgnoreCase(lastName) && appt.getPatientFirstName().equalsIgnoreCase(firstName)) {
                return Optional.of(appt);
            }
        }
        return Optional.empty();
    }

}
