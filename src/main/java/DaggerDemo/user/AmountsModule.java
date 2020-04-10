package DaggerDemo.user;

import dagger.Module;
import dagger.Provides;

import java.math.BigDecimal;

@Module
public interface AmountsModule {
    @Provides
    @MinimumBalance
    static BigDecimal minimumBalance() {
        return BigDecimal.ZERO;
    }

    @Provides
    @MaximumWithdrawal
    static BigDecimal maximumWithdrawal() {
        return new BigDecimal(1000);
    }
}