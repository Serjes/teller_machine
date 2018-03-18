package teller_machine.bank;

import teller_machine.cards.Card;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank {
    private Set<Client> clientsDB = new HashSet<>();
    private Map<Client, Account> accForClientsDB = new HashMap<>();
    private Map<Account,Card> cardDB = new HashMap<>();
    private Atm atm = new Atm();

    public Atm getAtm() {
        return atm;
    }

    private void addAccount(Client client, BigDecimal bigDecimal) {
        Account account = new Account(bigDecimal);
        accForClientsDB.put(client, account);
    }

    public Card getCardOfClient(Client client) {
        Card card = cardDB.get(accForClientsDB.get(client));
        return card;
    }

    public void issueCard(Client client, Card card, BigDecimal bigDecimal, int pin, int cvv) {
        clientsDB.add(client);
        addAccount(client, bigDecimal);
        cardDB.put(accForClientsDB.get(client), card);
        card.setAccount(accForClientsDB.get(client));
        card.setPin(pin);
        card.setCvv(cvv);
//        Date curDate = new Date();
//        Long time = curDate.getTime();
//        time = time + (60*60*24*1000*100);
//        Date expDate = new Date(time);
//        System.out.println(expDate.toString());
//        //card.setValidityPeriod(curDate);
        Calendar calendar = Calendar.getInstance();
        calendar.roll(calendar.YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println("срок действия карты до " + sdf.format(calendar.getTime()));
        card.setExpDate(calendar);
    }

    public void printAccounts(){
        System.out.println(accForClientsDB.values());
    }

    public void printClients() {
        System.out.println(clientsDB);
    }
}
