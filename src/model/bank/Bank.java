package model.bank;

import model.cards.Card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class Bank implements Serializable{
    private Set<Client> clientsDB = new HashSet<>();
    private Map<Client, Account> accForClientsDB = new HashMap<>();
    private Map<Account,Card> cardDB = new HashMap<>();
    private Atm atm = new Atm();

    public Atm getAtm() {
        return atm;
    }

    public Client getClient(String surname, String name){
        Client client = null;
        for (Client cl: clientsDB){
            if (cl.getSurname().equals(surname) && cl.getName().equals(name)) {
                client = cl;
            }
        }
        return client;
    }

    private void addAccount(Client client, BigDecimal bigDecimal) {
        Account account = new Account(bigDecimal);
        accForClientsDB.put(client, account);
    }

    public Card getCardOfClient(Client client) {
        Card card = cardDB.get(accForClientsDB.get(client));
        return card;
    }

    public Account getAccountOfClient(Client client) {
        //Account account = accForClientsDB.get(client);
        return  accForClientsDB.get(client);
    }

    public String issueCard(Client client, Card card, BigDecimal bigDecimal, int pin, int cvv) {
        clientsDB.add(client);
        addAccount(client, bigDecimal);
        cardDB.put(accForClientsDB.get(client), card);
        card.setAccount(accForClientsDB.get(client));
        card.setPin(pin);
        card.setCvv(cvv);
        Calendar calendar = Calendar.getInstance();
        calendar.roll(calendar.YEAR, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        //System.out.println("(срок действия карты до " + sdf.format(calendar.getTime()) + ")");
        card.setExpDate(calendar);
        return "(срок действия карты до " + sdf.format(calendar.getTime()) + ")";
    }

    public String printAccounts(){
        //System.out.println(accForClientsDB.values());
        return accForClientsDB.values().toString();
    }

    public String printClients() {
        //System.out.println(clientsDB);
        return clientsDB.toString();
    }
}
