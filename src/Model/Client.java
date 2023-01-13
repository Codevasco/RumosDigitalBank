package Model;

import java.time.LocalDate;

public class Client {

    
    // Attributes

    private int nif;
    private int password;
    private String name;
    private LocalDate dateOfBirth;
    private int telephone;
    private int cellphone;
    private String email;
    private String occupation;


    // Constructor

    public Client (int nif, int password, String name, LocalDate dateOfBirth, int telephone, int cellphone, String email, String occupation) {
        this.nif = nif;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.email = email;
        this.occupation = occupation;
    }


    // Getters and Setters

    public int getNif() {return nif;}
    public void setNif(int nif) {this.nif = nif;}

    public int getPassword() {return password;}
    public void setPassword(int password) {this.password = password;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LocalDate getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(LocalDate dateOfBirth) {this.dateOfBirth = dateOfBirth;}

    public int getTelephone() {return telephone;}
    public void setTelephone(int telephone) {this.telephone = telephone;}

    public int getCellphone() {return cellphone;}
    public void setCellphone(int cellphone) {this.cellphone = cellphone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getOccupation() {return occupation;}
    public void setOccupation(String occupation) {this.occupation = occupation;}


    // Function Methods

    public void requestCard() {
    }

    public void cancelCard() {
    }


    // Verification Methods

}
