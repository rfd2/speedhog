package io.speedhog.app.port.in;

import io.speedhog.model.account.Account;
import io.speedhog.model.account.AccountCreateException;

public interface CreateAccount {
    public Account createAccount(String email, String name)
        throws AccountCreateException;
}
