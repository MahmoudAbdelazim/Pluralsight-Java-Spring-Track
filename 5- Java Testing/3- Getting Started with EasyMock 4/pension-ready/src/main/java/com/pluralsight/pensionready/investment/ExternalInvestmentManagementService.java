package com.pluralsight.pensionready.investment;

import com.pluralsight.pensionready.Account;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;

public class ExternalInvestmentManagementService implements InvestmentManagementService {

    private static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.DOWN);

    public ExternalInvestmentManagementService() {
    }

    @Override
    public void addFunds(Account account, BigDecimal investmentAmount, Currency investmentCcy) {
        if (investmentCcy != Currency.getInstance("USD")) {
            throw new IllegalArgumentException("Only USD accounts are supported.");
        }
        account.setAvailableCash(account.getAvailableCash().add(investmentAmount, MATH_CONTEXT));
    }

    @Override
    public boolean buyInvestmentFund(Account account, String fundId, BigDecimal investmentAmount) throws IOException {
        if (account.getAvailableCash().compareTo(investmentAmount) < 0) {
            throw new IllegalArgumentException("Not enough cash in account.");
        }
        if (executeInvestmentTransaction(fundId, investmentAmount, "BUY")) {
            account.setAvailableCash(account.getAvailableCash().subtract(investmentAmount, MATH_CONTEXT));
            account.getInvestments().add(fundId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sellInvestmentFund(Account account, String fundId, BigDecimal investmentAmount) throws IOException {
        if (!account.getInvestments().contains(fundId)) {
            throw new IllegalArgumentException("Account doesn't have any holdings in " + fundId);
        }
        if (executeInvestmentTransaction(fundId, investmentAmount, "SELL")) {
            account.getInvestments().remove(fundId);
            account.setAvailableCash(account.getAvailableCash().add(investmentAmount, MATH_CONTEXT));
            return true;
        } else {
            return false;
        }
    }

    boolean executeInvestmentTransaction(String fundId,
                                         BigDecimal investmentAmount,
                                         String direction) throws IOException {

        return new ExternalBrokerLink().executeInvestmentTransaction(fundId, investmentAmount, direction);
    }

}
