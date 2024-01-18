package com.example.exceptions;

public class User {
    private final String username;
    private boolean valid;

    public User(String username, boolean valid) {
        this.username = username;
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "User{"
                + "username='" + username + '\''
                + ", valid=" + valid
                + "}";
    }

    public String getUsername() {
        return username;
    }

    public boolean isValid() {
        return valid;
    }
}