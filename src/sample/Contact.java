package sample;

/**
 * Created by DrScott on 10/20/15.
 */
public class Contact {
    String name;
    String phone;
    String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString(){
        return String.format("%s, %s, %s", name, phone, email);
    }
}
