package com.pluralsight.pensionready.setup;

import java.time.LocalDate;

public class ExternalNationalReferenceIdsManager implements ReferenceIdsManager {

    @Override
    public String obtainId(String firstName, String middleName, String lastName, String taxId, LocalDate dob) {
        //implementation not relevant for this course module
        return null;
    }
}
