package com.invoice.service;

import com.invoice.entity.User;
import com.invoice.exception.InvoiceException;
import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import com.invoice.repository.UserRepository;
import com.invoice.service.impl.UserServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.invoice.mapper.UserMapper.USER_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void retrieveFinalBalance() {
        final String email = RandomStringUtils.randomAlphabetic(10);
        final User storedUser = mock(User.class);

        when(storedUser.getEmail()).thenReturn(email);
        when(storedUser.getAccounts()).thenReturn(Collections.emptyList());
        when(userRepository.findByEmail(email)).thenReturn(storedUser);

        FinalBalancePayload finalBalancePayload = userService.retrieveFinalBalance(email);

        assertEquals(email, finalBalancePayload.getEmail());
        assertEquals(BigDecimal.ZERO, finalBalancePayload.getBalance());
    }

    @Test
    public void retrieveFinalBalanceNotExistingUser() {
        final String email = RandomStringUtils.randomAlphabetic(10);

        when(userRepository.findByEmail(email)).thenReturn(null);

        final InvoiceException retrievedException =
                assertThrows(InvoiceException.class, () -> userService.retrieveFinalBalance(email));

        assertEquals(HttpStatus.NOT_FOUND, retrievedException.getStatus());

        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void findAll() {
        final List<User> users = Collections.singletonList(new EasyRandom().nextObject(User.class));

        when(userRepository.findAll()).thenReturn(users);

        List<UserPayload> usersResponse = userService.findAll();

        assertNotNull(usersResponse);
        usersResponse.forEach(userResponse ->
                users.forEach(user ->
                        assertThat(userResponse)
                                .usingRecursiveComparison()
                                .isEqualTo(user)
                ));

        verify(userRepository, times(1)).findAll();
    }
}
