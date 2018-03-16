package teller_machine.cards;

import teller_machine.bank.Account;

import java.math.BigDecimal;

public abstract class Card {
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    //    private BigDecimal balance;
//
//    public Card() {
//        balance = new BigDecimal(1000.0);
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void withdraw(BigDecimal amount){
//        //BigDecimal tmpBalance =
//        //if (balance > amount)
//        if (balance.compareTo(amount) < 0) {
//            System.out.println("Не достаточно средств на карте");
//            //balance = balance.subtract(amount);
//            return;
//        }
//        balance = balance.subtract(amount);
//    }

}
