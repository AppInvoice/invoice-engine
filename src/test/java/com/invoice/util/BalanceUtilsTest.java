package com.invoice.util;

import com.invoice.entity.Account;
import com.invoice.entity.Currency;
import com.invoice.entity.RatingExchange;
import com.invoice.exception.InvoiceException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BalanceUtilsTest {

    @Test
    public void calculateBalanceNullAccounts() {
        assertEquals(BigDecimal.ZERO, BalanceUtils.retrieveBalanceFromAccounts(null));
    }

    @Test
    public void calculateBalanceEmptyAccounts() {
        assertEquals(BigDecimal.ZERO, BalanceUtils.retrieveBalanceFromAccounts(Collections.emptyList()));
    }

    @Test
    public void calculateBalanceMultipleAccounts() {
        final Currency currencyEuros = new Currency()
                .setCode("EUR")
                .setRatingExchange(new RatingExchange()
                        .setPercentChange(BigDecimal.ONE));

        final BigDecimal percentChange = new BigDecimal("0.5");
        final Currency otherCurrency = new Currency()
                .setRatingExchange(new RatingExchange()
                        .setPercentChange(percentChange));

        final Account firstAccount = new Account()
                .setBalance(BigDecimal.TEN)
                .setCurrency(currencyEuros);

        final Account secondAccount = new Account()
                .setBalance(BigDecimal.TEN)
                .setCurrency(otherCurrency);

        final BigDecimal expectedTotalBalance = firstAccount.getBalance()
                .add(secondAccount.getBalance().divide(percentChange, 2, BigDecimal.ROUND_HALF_UP));

        assertEquals(expectedTotalBalance, BalanceUtils.retrieveBalanceFromAccounts(Arrays.asList(firstAccount, secondAccount)));
    }

    @Test
    public void calculateBalanceWrongData() {
        assertThrows(InvoiceException.class, () -> BalanceUtils.retrieveBalanceFromAccounts(Collections.singletonList(new Account())));
    }
}
