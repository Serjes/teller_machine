package teller_machine.cards;

import teller_machine.bank.Account;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Card {
    String description;
    private Account account;
    private int pin;
    private int cvv;
    //private Date validityPeriod;
    //private Date expDate;
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

//    public void setValidityPeriod(Date validityPeriod) {
//        this.validityPeriod = validityPeriod;
//    }
//    private int getPin() {
//        return pin;
//    }

    public boolean checkPin(int pin) {
        //return (this.pin == pin) ? true : false;
        return (this.pin == pin);
    }

    public boolean checkCvvAndDate(int cvv, Calendar expDate) {
        if (this.cvv != cvv) return false;
//        Date currentDate = new Date();
//        Long currentTime = currentDate.getTime();
//        Long validityPeriodTime = validityPeriod.getTime();
//        if (currentTime > validityPeriodTime) return false;
//        return true;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        if (sdf.format(expDate.getTime()).equals(sdf.format(this.expDate.getTime()))) return true;
        return false;
//        if (this.expDate != expDate) return false;
//        return true;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public void setExpDate(Calendar expDate) {
        this.expDate = expDate;
    }
}
