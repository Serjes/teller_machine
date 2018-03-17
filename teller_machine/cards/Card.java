package teller_machine.cards;

import teller_machine.bank.Account;

import java.math.BigDecimal;

public abstract class Card {
    private Account account;
    String description;

    public String getDescription() {
        return description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void withdraw(BigDecimal amount) {
        account.withdraw(amount);
    }
}
