package Service;
public class DebitCard extends Cards {


    // Attributes

    private int withdrawnAmountPerDay;

    
    // Constructor

    public DebitCard (Client client, Account account, int cardNumber, int pin, int withdrawnAmountPerDay) {
        super(client, account, cardNumber, pin);
        this.withdrawnAmountPerDay = withdrawnAmountPerDay;
    }


    // Getters and Setters

    public int getWithdrawnAmountPerDay() {return withdrawnAmountPerDay;}
    public void setWithdrawnAmountPerDay(int withdrawnAmountPerDay) {this.withdrawnAmountPerDay = withdrawnAmountPerDay;}


    // Methods

}
