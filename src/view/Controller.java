package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.bank.Atm;
import model.bank.Bank;
import model.bank.Client;
import model.cards.*;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    Bank bank = new Bank();
    Atm atm = bank.getAtm();
    File file = new File("bankDB.txt");

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

    public Controller() {
    }

    public void issueCard(ActionEvent event) {
        if (surnameTextField.getText().isEmpty() || nameTextField.getText().isEmpty()) {
            resultTextArea.setText("Ошибка: не указан клиент!");
            return;
        }
        resultTextArea.setText("");
        Client client = new Client(surnameTextField.getText(),nameTextField.getText());
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
        Date now= new Date();
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
            showErrorDialog();
        }

    }
    public void loadDB(ActionEvent event) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            try {
                Object obj = ois.readObject();
                bank = (Bank) obj;
                showOkDialog("Успешно загружено!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                showErrorDialog();
            }
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog();
        }
    }

    public  void showOkDialog(String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public void showErrorDialog(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setContentText("Что то пошло не так");
        alert.showAndWait();
    }
}

