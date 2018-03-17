package teller_machine.bank;

import java.math.BigDecimal;

import teller_machine.cards.Card;

public class Atm {
    private Card currentCard;

    public void showBalance() {
        if (currentCard == null) {
            System.out.println("Вставьте карту");
        } else {
            System.out.println("Balance: " + currentCard.getAccount().getBalance().setScale(2).toString());
        }
    }

    public void insertCard(Card card) {
        if (currentCard == null) {
            currentCard = card;
            System.out.println("Вставили карту " + card.getDescription());
        } else {
            System.out.println("Уже вставлена карта");
        }
    }

    public void withdrawCash(BigDecimal amount) {
        if (currentCard == null) {
            System.out.println("Вставьте карту");
        } else {
            currentCard.withdraw(amount);
        }
    }

    public void ejectCard() {
        if (currentCard == null) {
            System.out.println("Карта не вставлена");
        } else {
            currentCard = null;
            System.out.println("Карта извлечена");
        }
    }
}
