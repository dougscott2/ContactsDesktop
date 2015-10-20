package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    ObservableList<Contact> contacts = FXCollections.observableArrayList();
    @FXML
    ListView listView;
    @FXML
    TextField nameField;
    @FXML
    TextField phoneField;
    @FXML
    TextField emailField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(contacts);
    }
    public void addContact(){
        if (!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals("")) {
            Contact contact = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
            contacts.add(contact);
        }
           nameField.setText(""); //sets text field to blank after addItem has been clicked
           phoneField.setText("");
           emailField.setText("");
    }
    public void removeContact(){
        Contact contact = (Contact) listView.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
    }
}
