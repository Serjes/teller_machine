package model.bank;

import model.cards.Card;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Scanner;

public class Atm implements Serializable{
    private Card currentCard;

    private boolean checkPin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите pin:");
        int pin;
        while (true) {
            if (scanner.hasNextInt()) {
                pin = scanner.nextInt();
                break;
            } else {
                System.out.println("Неверный ввод");
                scanner.nextLine();
            }
        }
        if (currentCard.checkPin(pin)) return true;
        return false;
    }

    public void insertCard(Card card) {
        if (currentCard == null) {
            currentCard = card;
            System.out.println("ATM: Вставили карту " + card.getDescription() + ", account: " + card.getAccount());
        } else {
            System.out.println("Уже вставлена карта");
        }
    }

    public void showBalance() {
        System.out.println("ATM: Проверка баланса");
        if (currentCard == null) {
            System.out.println("ATM: Ошибка, вставьте карту");
        } else {
            if (checkPin()) {
                System.out.println("ATM: Balance: " + currentCard.getAccount().getBalance().setScale(2).toString());
            } else {
                System.out.println("ATM: Неверный pin");
            }

        }
    }

    public void withdrawCash(BigDecimal amount) {
        System.out.println("ATM: Снятие наличных");
        if (currentCard == null) {
            System.out.println("ATM: Ошибка, вставьте карту");
        } else {
            if (checkPin()) {
                currentCard.withdraw(amount);
            } else {
                System.out.println("ATM: Неверный pin");
            }
        }
    }

    public void ejectCard() {
        System.out.println("ATM: Извлечение карты");
        if (currentCard == null) {
            System.out.println("ATM: Карта не вставлена");
        } else {
            currentCard = null;
            System.out.println("ATM: Карта успешно извлечена");
        }
    }
}
