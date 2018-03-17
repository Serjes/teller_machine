package teller_machine.cards;

import teller_machine.bank.Account;

import java.math.BigDecimal;

public abstract class Card {
    String description;
    private Account account;
    private int pin;

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

    public void setPin(int pin) {
        this.pin = pin;
    }

    private int getPin() {
        return pin;
    }

    public boolean checkPin(int pin) {
        return (this.pin == pin) ? true : false;
    }
}
