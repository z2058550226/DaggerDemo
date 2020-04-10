package DaggerDemo.user;

import DaggerDemo.Database;
import DaggerDemo.base.BigDecimalCommand;
import DaggerDemo.output.Outputter;

import javax.inject.Inject;
import java.math.BigDecimal;

class WithdrawCommand extends BigDecimalCommand {
    private final Outputter outputter;
    private final Database.Account account;
    private final WithdrawalLimiter withdrawalLimiter;
    private final BigDecimal minimumBalance;
    private final BigDecimal maximumWithdrawal;

    @Inject
    protected WithdrawCommand(Outputter outputter,
                              Database.Account account,
                              WithdrawalLimiter withdrawalLimiter,
                              @MinimumBalance BigDecimal minimumBalance,
                              @MaximumWithdrawal BigDecimal maximumWithdrawal) {
        super(outputter);
        this.outputter = outputter;
        this.account = account;
        this.withdrawalLimiter = withdrawalLimiter;
        this.minimumBalance = minimumBalance;
        this.maximumWithdrawal = maximumWithdrawal;
    }

    @Override
    protected void handleAmount(BigDecimal amount) {
        BigDecimal remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit();
        if (amount.compareTo(remainingWithdrawalLimit) > 0) {
            outputter.output(String.format("you may not withdraw %s; you may withdraw %s more in this session",
                    amount, remainingWithdrawalLimit));
            return;
        }
        if (amount.compareTo(maximumWithdrawal) > 0) {
            // output error
            outputter.output("error withdraw amount is more than the maximum withdraw balance: " + minimumBalance);
            return;
        }
        BigDecimal newBalance = account.balance().subtract(amount);
        if (newBalance.compareTo(minimumBalance) < 0) {
            outputter.output("error withdraw amount is more than the minimum balance " + minimumBalance);
            return;
        } else {
            account.withdraw(amount);
            outputter.output("your new balance is: " + account.balance());
        }
        withdrawalLimiter.recordWithdrawal(amount);
    }
}
