package io.speedhog.model.account;

import java.time.LocalDateTime;

public class Account {

    private String accountId;
    private String userEmail;
    private String name;
    private boolean active;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;

    public Account() {}

    public Account(
        String accountId,
        String userEmail,
        String name,
        boolean active,
        LocalDateTime created,
        LocalDateTime lastUpdated
    ) {
        this.accountId = accountId;
        this.userEmail = userEmail;
        this.name = name;
        this.active = active;
        this.created = created;
        this.lastUpdated = lastUpdated;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
