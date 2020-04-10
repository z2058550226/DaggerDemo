package DaggerDemo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// 对含有@Inject、@Binds或者@Provides注解的类的构造函数的对象添加@Singleton注解，相当于koin中的single
// dagger中的single更多是为了同Component之间共享
@Singleton
public class Database {
    private final Map<String, Account> accounts = new HashMap<>();

    @Inject
    Database() {
        System.out.println("Creating a new " + this);
    }

    public Account getAccount(String username) {
        return accounts.computeIfAbsent(username, Account::new);
    }

    public static final class Account {
        private final String username;
        private BigDecimal balance = BigDecimal.ZERO;

        Account(String username) {
            this.username = username;
            System.out.println("Account created, username: " + username);
        }

        public String username() {
            return username;
        }

        public BigDecimal balance() {
            return balance;
        }

        public void deposit(BigDecimal balance) {
            this.balance = this.balance.add(balance);
        }

        public void withdraw(BigDecimal amount) {
            this.balance = this.balance.subtract(amount);
        }
    }
}