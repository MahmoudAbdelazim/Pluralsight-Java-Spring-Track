package com.pluralsight.pensionready.setup;

import com.pluralsight.pensionready.AccountRepository;
import com.pluralsight.pensionready.reporting.GovernmentDataPublisher;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AccountOpeningServiceTest extends EasyMockSupport {

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final String TAX_ID = "ABC9";
    public static final LocalDate DOB = LocalDate.of(1990, 1, 10);
    private AccountOpeningService underTest;
    private final BackgroundCheckService backgroundCheckService = mock(BackgroundCheckService.class);
    private final BackgroundCheckService niceBackgroundCheckService = niceMock(BackgroundCheckService.class);
    private final ReferenceIdsManager referenceIdsManager = mock(ReferenceIdsManager.class);
    private final AccountRepository accountRepository = mock(AccountRepository.class);
    private final GovernmentDataPublisher governmentDataPublisher = mock(GovernmentDataPublisher.class);

    @Test
    void shouldDeclineAccountOpeningWhenBackgroundCheckResultsAreNull() throws IOException {
        underTest = new AccountOpeningService(
                backgroundCheckService,
                referenceIdsManager,
                accountRepository,
                governmentDataPublisher);
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB))
                .andReturn(null);
        replayAll();
        final AccountOpeningStatus accountOpeningStatus = underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB);
        assertEquals(AccountOpeningStatus.DECLINED, accountOpeningStatus);
        verifyAll();
    }

    @Test
    void shouldDeclineAccountOpeningWhenBackgroundCheckResultsAreNull2() throws IOException {
        underTest = new AccountOpeningService(
                niceBackgroundCheckService,
                referenceIdsManager,
                accountRepository,
                governmentDataPublisher);
        expect(niceBackgroundCheckService.confirm(anyString(), anyString(), startsWith("T"), isA(LocalDate.class)))
                .andAnswer(() -> {
                    fail();
                    return null;
                }).anyTimes();
        replayAll();
        final AccountOpeningStatus accountOpeningStatus = underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB);
        assertEquals(AccountOpeningStatus.DECLINED, accountOpeningStatus);
        verifyAll();
    }
}