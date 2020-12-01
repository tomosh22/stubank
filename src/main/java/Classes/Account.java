package Classes;

import java.math.BigDecimal;

public class Account {
    public String name;
    public String type;
    public BigDecimal balance;
    public String currency;
    public String accNumber;

    public Account(String name, String type, BigDecimal balance, String currency,String accNumber){
        this.name = name;
        this.type = type;
        this.balance = balance;
        this.currency = currency;
        this.name = name;
        this.accNumber = accNumber;
    }
}