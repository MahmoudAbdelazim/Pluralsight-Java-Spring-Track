package com.pluralsight.pension.withdrawal;

import com.pluralsight.pension.Account;
import com.pluralsight.pension.setup.BackgroundCheckResults;
import com.pluralsight.pension.setup.BackgroundCheckService;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static com.pluralsight.pension.setup.AccountOpeningService.UNACCEPTABLE_RISK_PROFILE;
import static com.pluralsight.pension.withdrawal.AccountClosingStatus.*;

public class AccountClosingService {

    public static final int RETIREMENT_AGE = 65;
    private BackgroundCheckService backgroundCheckService;
    private Clock clock;

    public AccountClosingService(BackgroundCheckService backgroundCheckService, Clock clock) {
        this.backgroundCheckService = backgroundCheckService;
        this.clock = clock;
    }

    public AccountClosingResponse closeAccount(Account account) throws IOException {
        Period accountHolderAge = Period.between(account.getDob(), LocalDate.now(clock));
        if (accountHolderAge.getYears() < RETIREMENT_AGE) {
            return new AccountClosingResponse(CLOSING_DENIED, LocalDateTime.now(clock));
        } else {
            final BackgroundCheckResults backgroundCheckResults = backgroundCheckService.confirm(
                    account.getFistName(),
                    account.getLastName(),
                    account.getTaxId(),
                    account.getDob());
            if (backgroundCheckResults == null) {
                return new AccountClosingResponse(CLOSING_PENDING, LocalDateTime.now(clock));
            } else {
                final String riskProfile = backgroundCheckResults.getRiskProfile();
                if (riskProfile.equals(UNACCEPTABLE_RISK_PROFILE)) {
                    return new AccountClosingResponse(CLOSING_PENDING, LocalDateTime.now(clock));
                } else {
                    return new AccountClosingResponse(CLOSING_OK, LocalDateTime.now(clock));
                }
            }

        }
    }
}
