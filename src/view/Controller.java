package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.bank.Atm;
import model.bank.Bank;
import model.bank.Client;
import model.cards.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class Controller {
    Bank bank = new Bank();
    Atm atm = bank.getAtm();
    File file = new File("bankDB.txt");
    Card currentCardATM;

    @FXML
    private Button issueButton;
    @FXML
    private Button printClientsButton;
    @FXML
    private Button printAccountsButton;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField pinTextField;
    @FXML
    private TextField cvvTextField;
    @FXML
    private TextArea outputTextArea;
    @FXML
    private TextField resultTextField;
    @FXML
    private TextArea resultTextArea;

    @FXML
    private TextField surnameTextFieldATM;
    @FXML
    private TextField nameTextFieldATM;
    @FXML
    private Button findButtonATM;
    @FXML
    private TextArea resultTextAreaATM;
    @FXML
    private TextField amountTextFieldATM;

    public Controller() {
    }

    public void issueCard(ActionEvent event) {
        if (surnameTextField.getText().isEmpty() || nameTextField.getText().isEmpty()) {
            resultTextArea.setText("Ошибка: не указан клиент!");
            return;
        }
        resultTextArea.setText("");
        Client client = new Client(surnameTextField.getText(), nameTextField.getText());
        System.out.printf("Выпустим карту для клиента: %s\n", client);
        int amount = 0;
        int pin = 0;
        int cvv = 0;
        if (!amountTextField.getText().isEmpty())
            amount = Integer.parseInt(amountTextField.getText());
        if (!pinTextField.getText().isEmpty())
            pin = Integer.parseInt(pinTextField.getText());
        if (!cvvTextField.getText().isEmpty())
            cvv = Integer.parseInt(cvvTextField.getText());
        String issueReturn = bank.issueCard(client, new VisaCard(), new BigDecimal(amount), pin, cvv);
//        String issueReturn = bank.issueCard(client, new VisaCard(), new BigDecimal(amountTextField.getText()),
//                Integer.parseInt(pinTextField.getText()), Integer.parseInt(cvvTextField.getText()));
        surnameTextField.setText("");
        nameTextField.setText("");
        amountTextField.setText("");
        pinTextField.setText("");
        cvvTextField.setText("");
        resultTextArea.setText("Выпущена карта для клиента: \n");
        resultTextArea.appendText(client.getSurname() + " " + client.getName() + "\n");
        resultTextArea.appendText(issueReturn);

    }

    public void printClients(ActionEvent actionEvent) {
        outputTextArea.appendText("\n");
        outputTextArea.appendText(bank.printClients() + "\n");
        //outputTextArea.setText("привет!");
    }

    public void printAccounts(ActionEvent actionEvent) {
        outputTextArea.appendText("Счета: \n");
        outputTextArea.appendText(bank.printAccounts() + "\n");
    }

    public void showDateTime(ActionEvent event) {
        System.out.println("Button Clicked!");
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        // Model Data
        String dateTimeString = df.format(now);
        // Show in VIEW
        //myTextField.setText(dateTimeString);

    }

    public void saveDB(ActionEvent event) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(bank);
            showOkDialog("Успешно сохранено!");
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Что то пошло не так: IO error");
        }

    }

    public void loadDB(ActionEvent event) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            try {
                Object obj = ois.readObject();
                bank = (Bank) obj;
                showOkDialog("Успешно загружено!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                showErrorDialog("Что то пошло не так: NotFound");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Что то пошло не так: IO error");
        }
    }

    public void showOkDialog(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void showErrorDialog(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void findCardATM(ActionEvent event) {
        //resultTextAreaATM.setText("");
        if (surnameTextFieldATM.getText().isEmpty() || nameTextFieldATM.getText().isEmpty()) {
            resultTextAreaATM.setText("Ошибка: не указан клиент!");
            return;
        }
        Client client = bank.getClient(surnameTextFieldATM.getText(),nameTextFieldATM.getText());
        resultTextAreaATM.setText(client.toString() + "\n");
        //Card currentCard = bank.getCardOfClient(client);
        currentCardATM = bank.getCardOfClient(client);
        resultTextAreaATM.appendText(currentCardATM.getDescription() + "\n");
        //getAccountOfClient
        resultTextAreaATM.appendText("номер счета: " + bank.getAccountOfClient(client) + "\n");
    }

    public void insertCardATM(ActionEvent event) {
        if (currentCardATM != null) {
            if (atm.insertCard(currentCardATM)) {
                resultTextAreaATM.setText("Карта вставлена, " + currentCardATM.getDescription() + "\n");
            } else {
                resultTextAreaATM.setText("Ошибка: уже вставлена\n" );
            }
        }
        else resultTextAreaATM.setText("Ошибка: нет карты\n");
    }

    public void showBalanceATM(ActionEvent event) {
        if (checkPin()) {
            resultTextAreaATM.appendText("Баланс карты: \n");
            resultTextAreaATM.appendText(atm.showBalance());
        } else {
            resultTextAreaATM.setText("Неверный pin \n");
        }
    }

    public boolean checkPin() {
        TextInputDialog dialog = new TextInputDialog("0000");
        dialog.setTitle("PIN Input Dialog");
        dialog.setHeaderText("PIN");
        dialog.setContentText("Please enter pin-code:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            //System.out.println("Your name: " + result.get());
            int pin = Integer.parseInt(result.get());
            if (currentCardATM.checkPin(pin)) return true;
        }
        return false;
    }

    public void withdrawCashATM(ActionEvent event) {
        if (checkPin()) {
            resultTextAreaATM.appendText("Снятие наличных: \n" + amountTextFieldATM.getText());
            //resultTextAreaATM.appendText(atm.withdrawCash(new BigDecimal(Integer.parseInt(amountTextFieldATM.getText()))));
            atm.withdrawCash(new BigDecimal(Integer.parseInt(amountTextFieldATM.getText())));
        } else {
            resultTextAreaATM.setText("Неверный pin \n");
        }
    }
}

