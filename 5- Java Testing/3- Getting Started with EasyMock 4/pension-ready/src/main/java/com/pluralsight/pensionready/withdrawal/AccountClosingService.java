package com.pluralsight.pensionready.withdrawal;

import com.pluralsight.pensionready.Account;
import com.pluralsight.pensionready.setup.BackgroundCheckResults;
import com.pluralsight.pensionready.setup.BackgroundCheckService;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import static com.pluralsight.pensionready.setup.AccountOpeningService.UNACCEPTABLE_RISK_PROFILE;
import static com.pluralsight.pensionready.withdrawal.AccountClosingStatus.CLOSING_DENIED;
import static com.pluralsight.pensionready.withdrawal.AccountClosingStatus.CLOSING_OK;

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
            if (backgroundCheckResults == null ||
                    backgroundCheckResults.getRiskProfile().equals(UNACCEPTABLE_RISK_PROFILE)) {
                return new AccountClosingResponse(CLOSING_DENIED, LocalDateTime.now(clock));
            } else {
                return new AccountClosingResponse(CLOSING_OK, LocalDateTime.now(clock));
            }
        }
    }
}
