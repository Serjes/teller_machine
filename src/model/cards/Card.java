package model.cards;

import model.bank.Account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public abstract class Card {
    String description;
    private Account account;
    private int pin;
    private int cvv;
    private Calendar expDate;

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

    public boolean checkPin(int pin) {
        return (this.pin == pin);
    }

    public boolean checkCvvAndDate(int cvv, Calendar expDate) {
        if (this.cvv != cvv) return false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(expDate.getTime()).equals(sdf.format(this.expDate.getTime()));
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpDate(Calendar expDate) {
        this.expDate = expDate;
    }
}
