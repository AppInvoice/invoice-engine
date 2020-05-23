package com.invoice.util;

import com.invoice.entity.Account;
import com.invoice.entity.Currency;
import com.invoice.entity.RatingExchange;
import com.invoice.exception.InvoiceException;
import org.apache.commons.collections4.ListUtils;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Utility Balance class. The feature provided is the account's balance calculation.
 * Will be retrieved in EUR currency.
 */
public class BalanceUtils {

    /**
     * Retrieves the total balance from a account list.
     * Each account can have different currencies, but the value retrieved will be taking into account the exchange to EUR.
     *
     * @param accounts The {@link Account} list.
     * @return The balance summation or {@link BigDecimal#ZERO} if any account is provided.
     */
    public static BigDecimal retrieveBalanceFromAccounts(final List<Account> accounts) {
        return ListUtils.emptyIfNull(accounts)
                .parallelStream()
                .map(account ->
                        Optional.ofNullable(account.getCurrency())
                                .map(Currency::getRatingExchange)
                                .map(RatingExchange::getPercentChange)
                                .map(percentChange -> account.getBalance().divide(percentChange, 2, ROUND_HALF_UP))
                                .orElseThrow(() -> new InvoiceException(HttpStatus.CONFLICT, "Data not consistent")))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
