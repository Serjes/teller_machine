package model.bank;

public class Client {
    private String surname;
    private String name;

    public Client(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" + surname + " " + name + '}';
    }
}
