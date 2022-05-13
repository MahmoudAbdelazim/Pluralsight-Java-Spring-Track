package com.pluralsight.pension.withdrawal;

import com.pluralsight.pension.Account;
import com.pluralsight.pension.setup.BackgroundCheckResults;
import com.pluralsight.pension.setup.BackgroundCheckService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AccountClosingServiceTest {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final String TAX_ID = "123";
    @Mock
    private BackgroundCheckService backgroundCheckService;

    @Test
    public void shouldDeclineAccountClosingTodayIfHolderReachesRetirementTomorrow() throws IOException {
        Instant fixedTime = LocalDate.of(2019, 7, 4)
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
        Clock clock = Clock.fixed(fixedTime, ZoneId.systemDefault());
        AccountClosingService underTest = new AccountClosingService(backgroundCheckService, clock);

        Account account = new Account();
        account.setDob(LocalDate.of(1954, 7, 5));

        final AccountClosingResponse accountClosingResponse = underTest.closeAccount(account);
        assertEquals(AccountClosingStatus.CLOSING_DENIED, accountClosingResponse.getStatus());
        System.out.println(accountClosingResponse.getProcessingDate());
        assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()),
                accountClosingResponse.getProcessingDate());
    }

    @Test
    public void shouldApproveAccountClosingIfHolderReachesRetirementAgeToday() throws IOException {
        Instant fixedTime = LocalDate.of(2019, 7, 4)
                .atStartOfDay(ZoneId.systemDefault()).toInstant();
        Clock clock = Clock.fixed(fixedTime, ZoneId.systemDefault());
        AccountClosingService underTest = new AccountClosingService(backgroundCheckService, clock);

        Account account = new Account();
        account.setFistName(FIRST_NAME);
        account.setLastName(LAST_NAME);
        account.setTaxId(TAX_ID);
        final LocalDate dob = LocalDate.of(1954, 7, 4);
        account.setDob(dob);

        BDDMockito.given(backgroundCheckService.confirm(FIRST_NAME, LAST_NAME, TAX_ID, dob))
                .willReturn(new BackgroundCheckResults("OK", 1));

        final AccountClosingResponse accountClosingResponse = underTest.closeAccount(account);
        assertEquals(AccountClosingStatus.CLOSING_OK, accountClosingResponse.getStatus());
        assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefault()), accountClosingResponse.getProcessingDate());

    }
}