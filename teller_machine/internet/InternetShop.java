package teller_machine.internet;

import teller_machine.cards.Card;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class InternetShop {
    public void payPurchase(Card currentCard, BigDecimal amount) {
        if (checkCvvAndDate(currentCard)) {
            currentCard.withdraw(amount);
            System.out.println("Покупка успешно оплачена");
        } else {
            System.out.println("Неверный CVV код или срок действия карты");
        }

    }

    private boolean checkCvvAndDate(Card card) {
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
        scanner.nextLine();
        System.out.println("Введите дату(срок действия карты) в формате: дд.мм.гггг");
        String expDateString = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(sdf.parse(expDateString));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        if (!card.checkCvvAndDate(cvv, calendar)) return false;
        return true;
    }
}
