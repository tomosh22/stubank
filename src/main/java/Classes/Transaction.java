package Classes;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {
    public BigDecimal amount;
    public Date dateTime;
    public String accNumberTo;

    public Transaction(BigDecimal amount, Date dateTime, String accNumberTo){
        this.amount = amount;
        this.dateTime = dateTime;
        this.accNumberTo = accNumberTo;
    }
}
