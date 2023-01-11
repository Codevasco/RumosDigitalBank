package Service;
public class Account {


    // Attributes

    private int accountNumber;
    private Client primaryHolder;
    private Client secondaryHolder;
    private double balance;


    // Constructor

    public Account (int accountNumber, Client primaryHolder, Client secondaryHolder, int balance) {
        this.accountNumber = accountNumber;
        this.primaryHolder = primaryHolder;
        this.secondaryHolder = secondaryHolder;
        this.balance = balance;
    }


    // Getters and Setters

    public int getAccountNumber() {return accountNumber;}
    public void setAccountNumber(int accountNumber) {this.accountNumber  = accountNumber;}

    public Client getPrimaryHolder() {return primaryHolder;}
    public void setPrimaryHolder(Client primaryHolder) {this.primaryHolder = primaryHolder;}

    public Client getSecondaryHolder() {return secondaryHolder;}
    public void setSecondaryHolder(Client secondaryHolder) {this.secondaryHolder = secondaryHolder;}

    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}


    // Function Methods
    
    public void deposit() {
    }

    public void withdrawal() {
    }

    public void transfer() {
    }


    // Verification Methods
    
}
