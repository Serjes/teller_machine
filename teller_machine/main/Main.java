package teller_machine.main;

import teller_machine.bank.Atm;
import teller_machine.cards.Card;
import teller_machine.cards.VisaCard;
import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();
        Card currentCard = new VisaCard();
        atm.insertCard(currentCard);
        atm.showBalance();
        //atm.withdraw(new BigDecimal(100));
        atm.withdraw(new BigDecimal(2000));
        atm.showBalance();
        atm.eject();
        //сделать меню для действий: вставить карту, баланс , снять
        //разнести по пакетам +
        //банк со счетами --это 2 класса
        //клиент
        //выпуск карты и открытие счета
        //пин код для работы с картой
        //класс InternetShop, принимает карты но не пин а cvv код

    }
}
