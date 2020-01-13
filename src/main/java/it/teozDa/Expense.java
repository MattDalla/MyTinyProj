package it.teozDa;

import java.util.Date;

public class Expense {

    private String typeOfExp;
    private String dateOfPaym; //dd-mm-yy
    private String cvedPeriod;
    private Double amount;


    public Expense(int intOfExp, String dateOfPaym, String cvedPeriod, Double amount) {
        setTypeOfExp(intOfExp);
        this.dateOfPaym = dateOfPaym;
        this.cvedPeriod = cvedPeriod;
        this.amount = amount;
    }

    public Expense(String typeOfExp, String dateOfPaym, String cvedPeriod, Double amount) {
        this.typeOfExp = typeOfExp;
        this.dateOfPaym = dateOfPaym;
        this.cvedPeriod = cvedPeriod;
        this.amount = amount;
    }

    public void setTypeOfExp(int num){
        /* 1. Affitto
           2. Fornitura Acqua
           3. Fornitura Gas
           4. Fornitura Luce
           5. Internet
           6. Extra
         */
        if (!(num <= 6 && num >= 1)){
            throw new IllegalArgumentException("\nOperation failed!\nAccepted values: 1 to 6.");
        }

        switch (num) {
            case 1:
                typeOfExp = "Affitto";
                break;
            case 2:
                typeOfExp = "Fornitura Acqua";
                break;
            case 3:
                typeOfExp = "Fornitura Gas";
                break;
            case 4:
                typeOfExp = "Fornitura Luce";
                break;
            case 5:
                typeOfExp = "Internet";
                break;
            case 6:
                typeOfExp = "Extra";
                break;
        }
    }

    public void setDateOfPaym(String date) {
        dateOfPaym = date;
    }

    public void setCvedPeriod(String period){
        cvedPeriod = period;
    }

    public void setAmount(double amt){
        amount = amt;
    }



    public String getTypeOfExp(){
        return typeOfExp;
    }

    public String getDateOfPaym(){
        return dateOfPaym;
    }

    public String getCvedPeriod(){
        return cvedPeriod;
    }

    public double getAmount(){
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "typeOfExp='" + typeOfExp + '\'' +
                ", dateOfPaym=" + dateOfPaym +
                ", cvedPeriod='" + cvedPeriod + '\'' +
                ", amount=" + amount +
                '}';
    }
}
