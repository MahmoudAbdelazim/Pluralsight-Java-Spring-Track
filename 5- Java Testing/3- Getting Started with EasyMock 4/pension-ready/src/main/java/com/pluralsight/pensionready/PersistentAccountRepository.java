package com.pluralsight.pensionready;

import com.pluralsight.pensionready.setup.BackgroundCheckResults;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PersistentAccountRepository implements AccountRepository {

    @Override
    public boolean save(String id, String firstName, String lastName, String taxId, LocalDate dob, BackgroundCheckResults backgroundCheckResults) {
        //implementation not relevant for this course module
        return false;
    }

    @Override
    public boolean isExpired(Account account) {
        return LocalDate.now().compareTo(account.getExpectedRetirement()) >= 0;
    }
}
