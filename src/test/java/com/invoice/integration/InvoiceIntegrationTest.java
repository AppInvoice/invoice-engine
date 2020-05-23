package com.invoice.integration;

import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void retrieveFinalBalance() {
        final String email = "batman@gmail.com";
        ResponseEntity<FinalBalancePayload> retrievedBalance = testRestTemplate.getForEntity(
                url + "/retrieve-balance?email=" + email, FinalBalancePayload.class);

        assertEquals(HttpStatus.OK, retrievedBalance.getStatusCode());
        assertNotNull(retrievedBalance.getBody());
        assertEquals(email, retrievedBalance.getBody().getEmail());
        assertThat(retrievedBalance.getBody().getBalance(), greaterThan(BigDecimal.ZERO));
    }

    @Test
    public void retrieveFinalBalanceWithoutParams() {
        ResponseEntity<FinalBalancePayload> retrievedBalance = testRestTemplate.getForEntity(
                url + "/retrieve-balance", FinalBalancePayload.class);

        assertEquals(HttpStatus.BAD_REQUEST, retrievedBalance.getStatusCode());
    }

    @Test
    public void retrieveFinalBalanceNonExistingUser() {
        ResponseEntity<FinalBalancePayload> retrievedBalance = testRestTemplate.getForEntity(
                url + "/retrieve-balance?email=NotExists", FinalBalancePayload.class);

        assertEquals(HttpStatus.NOT_FOUND, retrievedBalance.getStatusCode());
    }

    @Test
    public void retrieveAllUserNames() {
        ResponseEntity<UserPayload[]> usersResponse = testRestTemplate.getForEntity(url + "/users", UserPayload[].class);

        assertEquals(HttpStatus.OK, usersResponse.getStatusCode());
        assertNotNull(usersResponse.getBody());
        assertThat(usersResponse.getBody().length, greaterThan(0));
    }
}
