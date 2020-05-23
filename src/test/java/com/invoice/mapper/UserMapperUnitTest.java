package com.invoice.mapper;

import com.invoice.entity.User;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;

import static com.invoice.mapper.UserMapper.USER_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperUnitTest {

    @Test
    public void toFinalBalance() {
        final User user = new EasyRandom().nextObject(User.class);
        assertThat(user)
                .usingRecursiveComparison()
                .ignoringFields("id", "accounts")
                .isEqualTo(USER_MAPPER.toFinalBalance(user));
    }

    @Test
    public void toPayload() {
        final User user = new EasyRandom().nextObject(User.class);
        assertThat(user)
                .usingRecursiveComparison()
                .ignoringFields("id", "accounts")
                .isEqualTo(USER_MAPPER.toPayload(user));
    }
}

