package io.speedhog.adapter.in.rest;

import io.speedhog.adapter.out.persist.jdbc.JDBCAccountRepositoryCmd;
import io.speedhog.app.port.in.CreateAccount;
import io.speedhog.app.port.out.persistence.AccountRepositoryCmd;
import io.speedhog.app.service.account.CreateAccountService;
import io.speedhog.model.account.Account;
import io.speedhog.model.account.AccountCreateException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/account")
public class CreateAccountController {

    private CreateAccount createAccountUseCase;

    private AccountRepositoryCmd accountRepositoryCmd;

    private CreateAccount getCreateAccountUseCase() {
        if (createAccountUseCase == null) {
            accountRepositoryCmd = new JDBCAccountRepositoryCmd();
            createAccountUseCase = new CreateAccountService(
                accountRepositoryCmd
            );
        }
        return createAccountUseCase;
    }

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
            account = getCreateAccountUseCase().createAccount(
                accountCreateRecord.email(),
                accountCreateRecord.name()
            );
        } catch (AccountCreateException e) {
            e.printStackTrace();
            ErrorResponse error = new ErrorResponse(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
        }

        return Response.ok(account).build();
    }
}
