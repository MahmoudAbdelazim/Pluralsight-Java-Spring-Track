package com.pluralsight.pensionready.withdrawal;

import com.pluralsight.pensionready.Account;
import com.pluralsight.pensionready.setup.BackgroundCheckResults;
import com.pluralsight.pensionready.setup.BackgroundCheckService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.*;

import static com.pluralsight.pensionready.setup.AccountOpeningServiceTest.*;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountClosingServiceTest {

    private final BackgroundCheckService backgroundCheckService = mock(BackgroundCheckService.class);
    Instant fixedTime = LocalDate.of(2020, 1, 1).atStartOfDay(ZoneId.systemDefault())
            .toInstant();
    Clock fixedClock = Clock.fixed(fixedTime, ZoneId.systemDefault());
    private final AccountClosingService underTest = new AccountClosingService(backgroundCheckService, fixedClock);

    @Test
    void shouldDeclineAccountClosingTodayIfAccountHolderReachesRetirementAgeTomorrow() throws IOException {
        Account account = new Account();
        account.setDob(LocalDate.of(1955, 1, 2));

        replay(backgroundCheckService);
        final AccountClosingResponse accountClosingResponse = underTest.closeAccount(account);
        assertEquals(AccountClosingStatus.CLOSING_DENIED, accountClosingResponse.getStatus());
        assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneId.systemDefault()),
                accountClosingResponse.getProcessingDate());
    }

    @Test
    void shouldApproveAccountClosingTodayIfAccountHolderReachedRetirementAgeToday() throws IOException {
        Account account = new Account();
        final LocalDate dob = LocalDate.of(1955, 1, 1);
        account.setDob(dob);
        account.setFistName(FIRST_NAME);
        account.setLastName(LAST_NAME);
        account.setTaxId(TAX_ID);

        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                dob)).andReturn(new BackgroundCheckResults("not_unacceptable", 1000));

        replay(backgroundCheckService);
        final AccountClosingResponse accountClosingResponse = underTest.closeAccount(account);
        verify(backgroundCheckService);
        assertEquals(AccountClosingStatus.CLOSING_OK, accountClosingResponse.getStatus());
        assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneId.systemDefault()),
                accountClosingResponse.getProcessingDate());
    }
}