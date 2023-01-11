package Service;
public abstract class Cards {


    // Attributes

    private Client client;
    private Account account;
    private int cardNumber;
    private int pin;


    // Constructor

    public Cards (Client client, Account account, int cardNumber, int pin) {
        this.client = client;
        this.account = account;
        this.cardNumber = cardNumber;
        this.pin = pin;
    }


    // Getters and Setters

    public Client getClient() {return client;}
    public void setClient(Client client) {this.client = client;}

    public Account getAccount() {return account;}
    public void setAccount(Account account) {this.account = account;}

    public int getCardNumber() {return cardNumber;}
    public void setCardNumber(int cardNumber) {this.cardNumber = cardNumber;}

    public int getPin() {return pin;}
    public void setPin(int pin) {this.pin = pin;}


    // Function Methods
    
    public void changePin() {
    }


    // Verification Methods
}

