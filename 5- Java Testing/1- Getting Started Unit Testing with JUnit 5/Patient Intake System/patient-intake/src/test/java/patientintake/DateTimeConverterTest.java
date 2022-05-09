package patientintake;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Tag("dateTime")
@DisplayName("DateTimeConverter Test")
class DateTimeConverterTest {

    @Nested
    @DisplayName("convert string with 'today' keyword")
    class TodayTests {
        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly() {
            LocalDate today = LocalDate.of(2022, 5, 9);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 10:30 am",
                    today);
            assertEquals(result, LocalDateTime.of(2022, 5, 9, 10, 30),
                    () -> "Failed to convert 'today' string to expected date time, " +
                            "today passed was: " + today);
        }

        @Test
        @DisplayName("correctly regardless of case")
        void convertTodayStringCorrectlyCaseInsensitive() {
            LocalDate today = LocalDate.of(2022, 5, 9);
            LocalDateTime result = DateTimeConverter.convertStringToDateTime("TODay 10:30 am",
                    today);
            assertEquals(result, LocalDateTime.of(2022, 5, 9, 10, 30),
                    () -> "Failed to convert 'TODay' string to expected date time, " +
                            "today passed was: " + today);
        }
    }

    @Test
    @DisplayName("convert expected date time pattern in string correctly")
    void convertCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter.convertStringToDateTime("4/10/2022 09:00 am",
                LocalDate.of(2022, 5, 9));
        assertEquals(result, LocalDateTime.of(2022, 4, 10, 9, 0));
    }

    @Test
    @DisplayName("throw exception if entered pattern of string incorrectly")
    void throwExceptionIfIncorrectPatternProvided() {
        String dateTimeString = "13/10/2022 09:00 am";
        Throwable error = assertThrows(InvalidParameterException.class, () ->
                DateTimeConverter.convertStringToDateTime(dateTimeString,
                        LocalDate.of(2022, 5, 9)));
        assertEquals("Unable to create date time from: [13/10/2022 09:00 AM], " +
                        "please enter with format [M/d/yyyy h:mm a]Text '13/10/2022 09:00 AM' " +
                        "could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13",
                error.getMessage());
    }
}