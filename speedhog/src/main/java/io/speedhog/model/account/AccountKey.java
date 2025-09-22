package io.speedhog.model.account;

import java.time.LocalDateTime;

public class AccountKey {

    private Integer accountKeyId;
    private String accountId;
    private String accountToken;
    private boolean active;
    private LocalDateTime created;

    public AccountKey() {}

    public AccountKey(
        Integer accountKeyId,
        String accountId,
        String accountToken,
        boolean active,
        LocalDateTime created
    ) {
        this.accountKeyId = accountKeyId;
        this.accountId = accountId;
        this.accountToken = accountToken;
        this.active = active;
        this.created = created;
    }

    public Integer getAccountKeyId() {
        return accountKeyId;
    }

    public void setAccountKeyId(Integer accountKeyId) {
        this.accountKeyId = accountKeyId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
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
}
