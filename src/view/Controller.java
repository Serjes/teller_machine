package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        Client client = new Client(surnameTextField.getText(),nameTextField.getText());
        System.out.printf("Выпустим карту для клиента: %s\n", client);
        String issueReturn = bank.issueCard(client, new VisaCard(), new BigDecimal(amountTextField.getText()),
                Integer.parseInt(pinTextField.getText()), Integer.parseInt(cvvTextField.getText()));
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
        FileOutputStream fileOutputStream = null;
        //ObjectOutputStream os = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(bank);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDB(ActionEvent event) {

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //ObjectOutputStream os = new ObjectOutputStream(fileOutputStream);
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            try {
                Object obj = ois.readObject();
                bank = (Bank) obj;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //bank = is.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
