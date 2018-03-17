package teller_machine.internet;

import teller_machine.cards.Card;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Scanner;

public class InternetShop {
    public void payPurchase(Card currentCard, BigDecimal amount) {
        if (checkCVVAndDate(currentCard)) {
            currentCard.withdraw(amount);
            System.out.println("Покупка оплачена");
        } else {
            System.out.println("Неверный CVV код или срок действия карты");
        }

    }

    private boolean checkCVVAndDate(Card card) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите CVV code:");
        int cvv;
        while (true) {
            if (scanner.hasNextInt()) {
                cvv = scanner.nextInt();
                break;
            } else {
                System.out.println("Неверный ввод");
                scanner.nextLine();
            }
        }
        //Date date = null;
        if (!card.checkCVVandDate(cvv)) return false;
        return true;
    }
}
