package teller_machine.bank;

import teller_machine.cards.Card;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Account {
    private BigDecimal balance;
    //public static Map<Account,Card> cardDB = new HashMap<>();

    Account(BigDecimal balance) {
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
}
