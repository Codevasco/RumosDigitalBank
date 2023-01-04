package Service;
public class CreditCard extends Cards {

    
    // Attributes

    private int withdrawalsPerDay;

    
    // Constructor

    public CreditCard (Client client, Account account, int cardNumber, int pin, int withdrawalsPerDay) {
        super(client, account, cardNumber, pin);
        this.withdrawalsPerDay = withdrawalsPerDay;
    }


    // Getters and Setters

    public int getWithdrawnAmountPerDay() {return withdrawalsPerDay;}
    public void setWithdrawnAmountPerDay(int withdrawalsPerDay) {this.withdrawalsPerDay = withdrawalsPerDay;}


    // Methods

}
