package DaggerDemo.login;

import DaggerDemo.Database;
import DaggerDemo.base.Command;
import DaggerDemo.base.SingleArgCommand;
import DaggerDemo.output.Outputter;
import DaggerDemo.user.UserCommandsRouter;

import javax.inject.Inject;
import java.util.Optional;

final class LoginCommand extends SingleArgCommand {
    private final Outputter outputter;

    private final Database database;
    private final UserCommandsRouter.Factory userCommandsRouterFactory;
    private final Optional<Database.Account> account;

    @Inject
    LoginCommand(Database database, Outputter outputter, UserCommandsRouter.Factory userCommandsRouterFactory, Optional<Database.Account> account) {
        this.outputter = outputter;
        this.database = database;
        this.userCommandsRouterFactory = userCommandsRouterFactory;
        this.account = account;
        System.out.println("Creating a new " + this);
    }

    @Override
    public Command.Result handleArg(String username) {
        if (account.isPresent()) {
            outputter.output("You has already signed in. try to use logout.");
            return Result.handled();
        }
        Database.Account account = database.getAccount(username);
        outputter.output(username + " is logged in with balance: " + account.balance());
        return Command.Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router());
    }
}