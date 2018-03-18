package teller_machine.main;

import teller_machine.bank.Atm;
import teller_machine.bank.Bank;
import teller_machine.bank.Client;
import teller_machine.cards.Card;
import teller_machine.cards.MasterCard;
import teller_machine.cards.VisaCard;
import teller_machine.internet.InternetShop;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Client client = new Client("Иванов", "Василий");
        System.out.printf("Выпустим карту для клиента: %s\n", client);
        bank.issueCard(client, new VisaCard(), new BigDecimal(1000), 1012, 3819);

        Atm atm = bank.getAtm();
        Card currentCard = bank.getCardOfClient(client);
        //System.out.printf("Владелец карты: %s\n", client);
        atm.insertCard(currentCard);
        atm.showBalance();

        System.out.println("Снимем 100");
        atm.withdrawCash(new BigDecimal(100));
        //atm.withdrawCash(new BigDecimal(2000));
        atm.showBalance();
        atm.ejectCard();

        Client client2 = new Client("Сидоров", "Петр");
        System.out.printf("Выпустим карту для клиента: %s\n", client);
        bank.issueCard(client2, new MasterCard(), new BigDecimal(5000), 5643, 3452);
        System.out.println("Выведем всех клиентов банка:");
        bank.printClients();
        System.out.println("Выведем все счета в банке:");
        bank.printAccounts();
        System.out.println("");

        //класс InternetShop, принимает карты но не пин а cvv код
        InternetShop internetShop = new InternetShop();
        System.out.println("Оплатим в Интернет магазине покупку:");
        internetShop.payPurchase(currentCard, new BigDecimal(100));

    }
}
