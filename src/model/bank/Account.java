package model.bank;

import java.math.BigDecimal;

public class Account {
    private BigDecimal balance;
    private long number;

    private Account() {
        number = (long)(1000000000 * Math.random());
    }

    Account(BigDecimal balance) {
        this();
        this.balance = balance;
    }

    BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void withdraw(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            System.out.println("Не достаточно средств на карте");
            return;
        }
        balance = balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "" + number;
    }
}
