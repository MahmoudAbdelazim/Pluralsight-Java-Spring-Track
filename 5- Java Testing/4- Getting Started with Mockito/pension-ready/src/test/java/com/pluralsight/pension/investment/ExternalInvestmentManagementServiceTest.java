package com.pluralsight.pension.investment;

import com.pluralsight.pension.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ExternalInvestmentManagementServiceTest {

    public static final String TEST_FUND_ID = "FUND_ID";
    @Spy
    private ExternalInvestmentManagementService underTest;

    @Test
    public void shouldBeAbleToBuyPensionFundInvestmentIfEnoughCashInAccount() throws IOException {
        doReturn(true).when(underTest).executeInvestmentTransaction(
                anyString(), any(BigDecimal.class), anyString());
        Account testAccount = new Account();
        testAccount.setInvestments(new HashSet<>());
        final BigDecimal startingAccountBalance = new BigDecimal(1000000);
        testAccount.setAvailableCash(startingAccountBalance);
        final BigDecimal desiredInvestmentAmount = new BigDecimal(100000);

        underTest.buyInvestmentFund(testAccount, TEST_FUND_ID, desiredInvestmentAmount);

        assertEquals(testAccount.getAvailableCash(), startingAccountBalance.subtract(desiredInvestmentAmount));
        assertTrue(testAccount.getInvestments().contains(TEST_FUND_ID));
    }
}