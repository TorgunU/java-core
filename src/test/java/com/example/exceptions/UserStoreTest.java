package com.example.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserStoreTest {
    @Test
    public void whenUserNotFind() {
        User[] users = {
                new User("Ivan", true),
        };

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new UserStore().findUser(users, "Iv");
        });
        assertThat(exception.getMessage()).isEqualTo("Пользователь не найден");
    }

    @Test
    public void whenUserInvalid() {
        User user = new User("Fedor", false);

        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            new UserStore().validate(user);
        });
        assertThat(exception.getMessage()).isEqualTo("Пользователь не валидный");
    }
}