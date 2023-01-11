package Service;
public class CreditCard extends Cards {

    
    // Attributes

    private double plafond;
    private int withdrawalsPerDay;

    
    // Constructor

    public CreditCard (Client client, Account account, int cardNumber, int pin, double plafond, int withdrawalsPerDay) {
        super(client, account, cardNumber, pin);
        this.plafond = plafond;
        this.withdrawalsPerDay = withdrawalsPerDay;
    }


    // Getters and Setters

    public double getPlafond() {return plafond;}
    public void setPlafond(double plafond) {this.plafond = plafond;}

    public int getWithdrawnAmountPerDay() {return withdrawalsPerDay;}
    public void setWithdrawnAmountPerDay(int withdrawalsPerDay) {this.withdrawalsPerDay = withdrawalsPerDay;}


    // Function Methods

    @Override
    public void changePin() {
        super.changePin();
    }

    // Verification Methods
    
}
