package DaggerDemo.user;

import DaggerDemo.Database;
import DaggerDemo.base.BigDecimalCommand;
import DaggerDemo.output.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

final class DepositCommand extends BigDecimalCommand {
    private final Database.Account account;
    private final Outputter outputter;
    private final WithdrawalLimiter withdrawalLimiter;

    @Inject
    DepositCommand(Database.Account account, Outputter outputter, WithdrawalLimiter withdrawalLimiter) {
        super(outputter);
        this.account = account;
        this.outputter = outputter;
        this.withdrawalLimiter = withdrawalLimiter;
        System.out.println("Creating a new " + this);
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        account.deposit(amount);
        outputter.output(account.username() + " now has: " + account.balance());
        withdrawalLimiter.recordDeposit(amount);
    }
}
