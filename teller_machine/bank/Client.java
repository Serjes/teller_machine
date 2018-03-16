package teller_machine.bank;

public class Client {
    private String surname;
    private String name;

    public Client(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
