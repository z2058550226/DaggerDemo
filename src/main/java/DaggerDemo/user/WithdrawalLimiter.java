package DaggerDemo.user;

import javax.inject.Inject;
import java.math.BigDecimal;

@Persession
final class WithdrawalLimiter {
    private BigDecimal remainingWithdrawalLimit;

    @Inject
    WithdrawalLimiter(@MaximumWithdrawal BigDecimal maximumWithdrawal) {
        this.remainingWithdrawalLimit = maximumWithdrawal;
    }

    void recordDeposit(BigDecimal amount) {

    }

    void recordWithdrawal(BigDecimal amount) {
        this.remainingWithdrawalLimit = this.remainingWithdrawalLimit.subtract(amount);
    }

    public BigDecimal remainingWithdrawalLimit() {
        return this.remainingWithdrawalLimit;
    }
}