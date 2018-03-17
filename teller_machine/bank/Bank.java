package teller_machine.bank;

import teller_machine.cards.Card;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public void issueCard(Client client, Card card, BigDecimal bigDecimal) {
        clientsDB.add(client);
        addAccount(client, bigDecimal);
        cardDB.put(accForClientsDB.get(client), card);
        card.setAccount(accForClientsDB.get(client));
    }

    public void printAccounts(){
        System.out.println(accForClientsDB.values());
    }

    public void printClients() {
        System.out.println(clientsDB);
    }
}
