package com.invoice.controller;

import com.invoice.exception.InvoiceException;
import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import com.invoice.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void retrieveFinalBalance() {
        final String email = RandomStringUtils.randomAlphabetic(10);
        final FinalBalancePayload finalBalancePayload = mock(FinalBalancePayload.class);

        when(finalBalancePayload.getEmail()).thenReturn(email);
        when(userService.retrieveFinalBalance(email)).thenReturn(finalBalancePayload);

        ResponseEntity<FinalBalancePayload> userBalanceResponse = userController.retrieveUserBalance(email);

        assertEquals(HttpStatus.OK, userBalanceResponse.getStatusCode());
        assertNotNull(userBalanceResponse.getBody());
        assertEquals(email, finalBalancePayload.getEmail());

        verify(userService, times(1)).retrieveFinalBalance(email);
    }

    @Test
    public void retrieveFinalBalanceRaisedException() {
        final String email = RandomStringUtils.randomAlphabetic(10);
        final String exceptionReason = RandomStringUtils.randomAlphabetic(10);

        InvoiceException invoiceException = new InvoiceException(HttpStatus.INTERNAL_SERVER_ERROR, exceptionReason);

        when(userService.retrieveFinalBalance(email)).thenThrow(invoiceException);

        final InvoiceException retrievedException =
                assertThrows(InvoiceException.class, () -> userController.retrieveUserBalance(email));

        assertEquals(invoiceException, retrievedException);

        verify(userService, times(1)).retrieveFinalBalance(email);
    }

    @Test
    public void findAllUsers() {
        final List<UserPayload> users = Collections.singletonList(mock(UserPayload.class));

        when(userService.findAll()).thenReturn(users);

        ResponseEntity<List<UserPayload>> usersResponse = userController.findAll();

        assertEquals(HttpStatus.OK, usersResponse.getStatusCode());
        assertEquals(users, usersResponse.getBody());

        verify(userService, times(1)).findAll();
    }


    @Test
    public void findAllUsersRaisedException() {
        final String exceptionReason = RandomStringUtils.randomAlphabetic(10);

        InvoiceException invoiceException = new InvoiceException(HttpStatus.INTERNAL_SERVER_ERROR, exceptionReason);

        when(userService.findAll()).thenThrow(invoiceException);

        final InvoiceException retrievedException =
                assertThrows(InvoiceException.class, () -> userController.findAll());

        assertEquals(invoiceException, retrievedException);

        verify(userService, times(1)).findAll();
    }
}
