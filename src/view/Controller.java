package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.bank.Bank;
import model.bank.Client;
import model.cards.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    Bank bank = new Bank();

    @FXML
    private Button issueButton;
    @FXML
    private Button printClientsButton;
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
        outputTextArea.appendText(bank.printClients());
        //outputTextArea.setText("привет!");
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
}
