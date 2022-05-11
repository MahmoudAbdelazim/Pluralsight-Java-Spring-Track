package com.pluralsight.pensionready.setup;

import java.io.IOException;
import java.time.LocalDate;

public interface BackgroundCheckService {
    BackgroundCheckResults confirm(String firstName, String lastName, String taxId, LocalDate dob) throws IOException;
}
