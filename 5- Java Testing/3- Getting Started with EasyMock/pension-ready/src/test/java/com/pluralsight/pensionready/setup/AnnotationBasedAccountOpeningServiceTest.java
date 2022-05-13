package com.pluralsight.pensionready.setup;

import com.pluralsight.pensionready.AccountRepository;
import com.pluralsight.pensionready.reporting.GovernmentDataPublisher;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static com.pluralsight.pensionready.setup.AccountOpeningServiceTest.*;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(EasyMockExtension.class)
class AnnotationBasedAccountOpeningServiceTest {

    public static final String ACCOUNT_ID = "an account ID";
    public static final BackgroundCheckResults ACCEPTABLE_BACKGROUND_CHECK_RESULTS = new BackgroundCheckResults("an acceptable risk profile", 5000000);
    @TestSubject
    private final AccountOpeningService underTest = new AccountOpeningService();
    @Mock
    private BackgroundCheckService backgroundCheckService;
    @Mock
    private ReferenceIdsManager referenceIdsManager;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private GovernmentDataPublisher governmentDataPublisher;

    @Test
    void shouldDeclineAccountOpeningIfBackgroundCheckResultsRiskProfileUnacceptable() throws IOException {
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        )).andReturn(new BackgroundCheckResults(AccountOpeningService.UNACCEPTABLE_RISK_PROFILE, 0));
        replay(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);
        final AccountOpeningStatus accountOpeningStatus = underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        );

        assertEquals(AccountOpeningStatus.DECLINED, accountOpeningStatus);
        verify(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);
    }

    @Test
    void shouldOpenAccount() throws IOException {
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        )).andReturn(ACCEPTABLE_BACKGROUND_CHECK_RESULTS);
        expect(referenceIdsManager.obtainId(
                eq(FIRST_NAME),
                anyString(),
                eq(LAST_NAME),
                eq(TAX_ID),
                eq(DOB)
        )).andReturn(ACCOUNT_ID);
        expect(accountRepository.save(
                eq(ACCOUNT_ID),
                eq(FIRST_NAME),
                eq(LAST_NAME),
                eq(TAX_ID),
                eq(DOB),
                same(ACCEPTABLE_BACKGROUND_CHECK_RESULTS)
        )).andReturn(true);
        governmentDataPublisher.publishAccountOpeningEvent(ACCOUNT_ID);
        expectLastCall().anyTimes();
        replay(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);

        final AccountOpeningStatus accountOpeningStatus = underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB);
        assertEquals(AccountOpeningStatus.OPENED, accountOpeningStatus);
        verify(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);
    }

    @Test
    void shouldThrowIfBackgroundChecksServiceThrows() throws IOException {
        final String thrownMsg = "a custom message that we can assert on later";
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        )).andThrow(new IOException(thrownMsg));

        replay(backgroundCheckService);

        final IOException thrown = assertThrows(IOException.class, () -> underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        ));

        assertEquals(thrownMsg, thrown.getMessage());
        verify(backgroundCheckService);
    }

    @Test
    void shouldThrowIfReferenceIdsManagerThrows() throws IOException {
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        )).andReturn(ACCEPTABLE_BACKGROUND_CHECK_RESULTS);
        final String expectedThrownMsg = "a message for our runtime exception";
        expect(referenceIdsManager.obtainId(
                eq(FIRST_NAME),
                anyString(),
                eq(LAST_NAME),
                eq(TAX_ID),
                eq(DOB)
        )).andThrow(new RuntimeException(expectedThrownMsg));
        replay(backgroundCheckService, referenceIdsManager);
        final RuntimeException thrown = assertThrows(RuntimeException.class, () -> underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        ));
        assertEquals(expectedThrownMsg, thrown.getMessage());
        verify(backgroundCheckService, referenceIdsManager);
    }

    @Test
    void shouldThrowIfGovernmentDataPublisherThrows() throws IOException {
        expect(backgroundCheckService.confirm(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        )).andReturn(ACCEPTABLE_BACKGROUND_CHECK_RESULTS);
        expect(referenceIdsManager.obtainId(
                eq(FIRST_NAME),
                anyString(),
                eq(LAST_NAME),
                eq(TAX_ID),
                eq(DOB)
        )).andReturn(ACCOUNT_ID);
        expect(accountRepository.save(
                eq(ACCOUNT_ID),
                eq(FIRST_NAME),
                eq(LAST_NAME),
                eq(TAX_ID),
                eq(DOB),
                same(ACCEPTABLE_BACKGROUND_CHECK_RESULTS)
        )).andReturn(true);
        governmentDataPublisher.publishAccountOpeningEvent(ACCOUNT_ID);
        final String govDataExpectedThrownMsg = "government data exception";
        expectLastCall().andThrow(new RuntimeException(govDataExpectedThrownMsg));
        replay(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);

        final RuntimeException actualThrown = assertThrows(RuntimeException.class, () -> underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB
        ));

        assertEquals(govDataExpectedThrownMsg, actualThrown.getMessage());
        verify(backgroundCheckService, referenceIdsManager, accountRepository, governmentDataPublisher);
    }
}