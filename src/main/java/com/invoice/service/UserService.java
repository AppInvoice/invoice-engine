package com.invoice.service;

import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;

import java.util.List;

public interface UserService {

    FinalBalancePayload retrieveFinalBalance(final String email);

    List<UserPayload> findAll();
}
