package io.speedhog.app.port.out.persistence;

import io.speedhog.model.account.Account;
import io.speedhog.model.account.AccountCreateException;

public interface AccountRepositoryCmd {
    public Account saveAccount(String email, String name)
        throws AccountCreateException;
}
