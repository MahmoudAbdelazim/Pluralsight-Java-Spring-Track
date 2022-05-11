package com.pluralsight.pensionready.setup;

import com.pluralsight.pensionready.AccountRepository;
import com.pluralsight.pensionready.reporting.GovernmentDataPublisher;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.pluralsight.pensionready.setup.AccountOpeningServiceTest.*;
import static com.pluralsight.pensionready.setup.AnnotationBasedAccountOpeningServiceTest.ACCEPTABLE_BACKGROUND_CHECK_RESULTS;
import static com.pluralsight.pensionready.setup.AnnotationBasedAccountOpeningServiceTest.ACCOUNT_ID;
import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderEnabledAccountOpeningServiceTest {

    private AccountOpeningService underTest;
    private BackgroundCheckService backgroundCheckService;
    private ReferenceIdsManager referenceIdsManager;
    private AccountRepository accountRepository;
    private GovernmentDataPublisher governmentDataPublisher;
    private IMocksControl strictControl;

    @BeforeEach
    public void setup() {
        strictControl = createStrictControl();
        backgroundCheckService = strictControl.createMock(BackgroundCheckService.class);
        referenceIdsManager = strictControl.createMock(ReferenceIdsManager.class);
        accountRepository = strictControl.createMock(AccountRepository.class);
        governmentDataPublisher = strictControl.createMock(GovernmentDataPublisher.class);
        underTest = new AccountOpeningService(
                backgroundCheckService,
                referenceIdsManager,
                accountRepository,
                governmentDataPublisher);
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
        strictControl.replay();

        final AccountOpeningStatus accountOpeningStatus = underTest.openAccount(
                FIRST_NAME,
                LAST_NAME,
                TAX_ID,
                DOB);
        assertEquals(AccountOpeningStatus.OPENED, accountOpeningStatus);
        strictControl.verify();
    }
}
