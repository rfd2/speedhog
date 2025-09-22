package io.speedhog;

import de.fxlae.typeid.TypeId;
import io.speedhog.adapter.in.rest.AccountCreateRecord;
import io.speedhog.model.account.Account;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Path("/testaccount")
public class CmdAccountController {

    /*
   Expected JSON body for AccountCreateRecord
   {
     "email": "user@example.com",
     "name": "John Doe"
   }

    */

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountCreateRecord accountCreateRecord) {
        Account account = null;
        try {
            account = persist(accountCreateRecord);
        } catch (SQLException e) {
            ErrorResponse error = new ErrorResponse("Internal Server Error");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }

        return Response.ok(account).build();
    }

    private Account persist(AccountCreateRecord acr) throws SQLException {
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
            stmt.setString(2, acr.email());
            stmt.setString(3, acr.name());
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
            acr.email(),
            acr.name(),
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

    public class ErrorResponse {

        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        private void setMessage(String message) {
            this.message = message;
        }

        private String getMessage() {
            return message;
        }
    }
}
