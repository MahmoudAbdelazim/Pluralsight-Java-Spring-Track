package patientintake;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Clinic Calendar Test")
class ClinicCalendarTest {

    private ClinicCalendar calendar;

    @BeforeAll
    static void testClassSetup() {

    }

    @BeforeEach
    void init() {
        this.calendar = new ClinicCalendar(LocalDate.of(2022, 5, 9));
    }

    @Test
    @DisplayName("allow entry of a new appointment when input is passed correctly")
    void allowEntryOfAnAppointment() {
        calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                "09/01/2018 2:00 pm");
        List<PatientAppointment> appointments = calendar.getAppointments();
        assertNotNull(appointments);
        assertEquals(1, appointments.size());
        PatientAppointment enteredAppt = appointments.get(0);
        assertAll(
                () -> assertEquals("Mahmoud", enteredAppt.getPatientFirstName()),
                () -> assertEquals("Abdelazim", enteredAppt.getPatientLastName()),
                () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
                () -> assertEquals("9/1/2018 2:00 PM",
                        enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy h:mm a")))
        );
    }

    @Nested
    @DisplayName("has appointments")
    class HasAppointments {
        @Test
        @DisplayName("return true if there are appointments")
        void returnTrueForHasAppointmentsIfThereAreAppointments() {
            calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                    "09/01/2018 2:00 pm");
            assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
        }

        @Test
        @DisplayName("return false if there no appointments")
        void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
            assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
        }
    }

    @Test
    @DisplayName("return the appointments for today")
    void returnCurrentDaysAppointments() {
        calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                "5/9/2022 2:00 pm");
        calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                "5/9/2022 2:00 pm");
        assertEquals(2, calendar.getTodayAppointments().size());
        assertEquals(calendar.getTodayAppointments(), calendar.getAppointments());
    }

    @Nested
    @DisplayName("return upcoming appointments")
    class UpcomingAppointments {
        @Test
        @DisplayName("when there are none")
        void whenThereAreNone() {
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(0, appointments.size());
        }

        @Test
        @DisplayName("when there are some past and some future appointments")
        void whenThereAreSomePastAndFuture() {
            calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                    "5/8/2022 8:00 pm");
            calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                    "5/9/2022 8:00 pm");
            calendar.addAppointment("Mahmoud", "Abdelazim", "avery",
                    "5/10/2022 8:00 pm");
            List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
            assertEquals(1, appointments.size());
        }
    }

    @AfterEach
    void tearDownEachTest() {

    }

    @AfterAll
    static void tearDownTestClass() {

    }
}