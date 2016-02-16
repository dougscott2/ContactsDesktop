package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import javax.xml.bind.util.JAXBSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;


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
        File f = new File("contacts.json");
        Scanner s = null;
        try {
            s = new Scanner(f);
            s.useDelimiter("\\Z");
            String contents = s.next();
            JsonParser parser = new JsonParser();
            ArrayList<HashMap> contactlist = parser.parse(contents);
            for (HashMap<String, String> contactMap: contactlist){
                contacts.add(new Contact(contactMap.get("name"),contactMap.get("phone"), contactMap.get("email") ));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listView.setItems(contacts);
    }

    public void addContact(){
        if (!nameField.getText().equals("") && !phoneField.getText().equals("") && !emailField.getText().equals("")) {
            Contact contact = new Contact(nameField.getText(), phoneField.getText(), emailField.getText());
            contacts.add(contact);
            String content = "";
            for (Contact c : contacts){
                content += String.format("%s,%s,%s\n",c.name,c.phone,c.email);
            }
            writeFile("contacts.csv", content);
            saveContacts(contacts);
        }
           nameField.setText(""); //sets text field to blank after addItem has been clicked
           phoneField.setText("");
           emailField.setText("");


    }
    public void removeContact(){
        Contact contact = (Contact) listView.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
        String content = "";
        for (Contact c : contacts){
            content += String.format("%s,%s,%s\n",c.name,c.phone,c.email);
        }
        writeFile("contacts.csv", content);
        saveContacts(contacts);
    }
    static void writeFile(String fileName, String fileContent){
        File f = new File(fileName);
        try{
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e){
            System.out.println("Something went wrong with writeFile()");
        }
    }

    public static void saveContacts(ObservableList<Contact> contacts) {
        File f = new File("contacts.json");
        JsonSerializer serializer = new JsonSerializer();  //json serializer
        String contentToSave = serializer.serialize(contacts);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong with saveContacts()...sorry!");
        }
    }
}
