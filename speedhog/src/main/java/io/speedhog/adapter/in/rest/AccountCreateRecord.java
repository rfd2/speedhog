package io.speedhog.adapter.in.rest;

import java.util.Objects;

public record AccountCreateRecord(String email, String name) {
    public AccountCreateRecord {
        Objects.requireNonNull(email, "'email' must not be null");
        if (email.isEmpty()) {
            throw new IllegalArgumentException("'email' must not be empty");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("'email' must contain '@'");
        }
        Objects.requireNonNull(name, "'name' must not be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("'name' must not be empty");
        }
    }
}
