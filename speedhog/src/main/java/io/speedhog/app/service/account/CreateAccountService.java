package io.speedhog.app.service.account;

import io.speedhog.app.port.in.CreateAccount;
import io.speedhog.app.port.out.persistence.AccountRepositoryCmd;
import io.speedhog.model.account.Account;
import io.speedhog.model.account.AccountCreateException;

public class CreateAccountService implements CreateAccount {

    private AccountRepositoryCmd accountRepositoryCmd;

    public CreateAccountService(AccountRepositoryCmd accountRepositoryCmd) {
        this.accountRepositoryCmd = accountRepositoryCmd;
    }

    @Override
    public Account createAccount(String email, String name)
        throws AccountCreateException {
        Account account = null;
        try {
            account = accountRepositoryCmd.saveAccount(email, name);
        } catch (AccountCreateException e) {
            throw e;
        }
        return account;
    }
}
