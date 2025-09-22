package io.speedhog.adapter.out.persist.jdbc;

import de.fxlae.typeid.TypeId;
import io.speedhog.app.port.out.persistence.AccountRepositoryCmd;
import io.speedhog.model.account.Account;
import io.speedhog.model.account.AccountCreateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JDBCAccountRepositoryCmd implements AccountRepositoryCmd {

    @Override
    public Account saveAccount(String email, String name)
        throws AccountCreateException {
        Account account = null;
        try {
            account = persist(email, name);
        } catch (SQLException e) {
            throw new AccountCreateException(
                "Failed to create account: " + e.getMessage()
            );
        }
        return account;
    }

    private Account persist(String email, String name) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        String sql =
            "INSERT INTO sh.accounts (account_id, user_email, name, active, created) VALUES (?, ?, ?, ?, ?)";

        TypeId acctIdT = TypeId.generate("acct");
        String acctId = acctIdT.toString();
        Timestamp createdOn = new Timestamp(System.currentTimeMillis());

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, acctId);
            stmt.setString(2, email);
            stmt.setString(3, name);
            stmt.setBoolean(4, true);
            stmt.setTimestamp(5, createdOn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        LocalDateTime localDateTime = createdOn.toLocalDateTime();
        Account account = new Account(
            acctId,
            email,
            name,
            true,
            localDateTime,
            null
        );
        return account;
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://0.0.0.0:5432/speedhog?sslmode=disable";
        String user = "shapp";
        String password = "shog25";
        return DriverManager.getConnection(url, user, password);
    }
}
