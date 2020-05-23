package com.invoice.service.impl;

import com.invoice.exception.InvoiceException;
import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import com.invoice.repository.UserRepository;
import com.invoice.service.UserService;
import com.invoice.util.BalanceUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.invoice.mapper.UserMapper.USER_MAPPER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FinalBalancePayload retrieveFinalBalance(final String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .map(user -> USER_MAPPER.toFinalBalance(user)
                        .setBalance(BalanceUtils.retrieveBalanceFromAccounts(user.getAccounts())))
                .orElseThrow(() -> new InvoiceException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public List<UserPayload> findAll() {
        return userRepository.findAll()
                .stream()
                .map(USER_MAPPER::toPayload)
                .collect(Collectors.toList());
    }
}
