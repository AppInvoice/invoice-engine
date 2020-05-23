package com.invoice.mapper;

import com.invoice.entity.User;
import com.invoice.payload.FinalBalancePayload;
import com.invoice.payload.UserPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    FinalBalancePayload toFinalBalance(final User user);

    UserPayload toPayload(final User user);
}
