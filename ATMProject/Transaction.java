package ATMProject;

public class Transaction {
    public String performer;// variable to store the performer type as admin or user
    public double amount;// variable to store the amount
    public String transactionType;// variable to store the transaction type list deposit or withdraw
    public Transaction(String performer, double amount, String transactionType)// constructor to set the values for each variable
    {
        this.setPerformer(performer);
        this.setAmount(amount);
        this.setTransactionType(transactionType);
    }

    public void setPerformer(String performer)// method to set the values to the performer variable performer
    {
        this.performer = performer;
    }
    public void setTransactionType(String transactionType)// method to set the values to the transaction type variable transaction type
    {
        this.transactionType = transactionType;
    }
    public void setAmount(double amount)// method to set the values to the variable amount
    {
        this.amount = amount;
    }
    public String getperformer()//method to return the performer
    {
        return performer;
    }
    public String getTransactionType()// method to return the transaction type
    {
        return transactionType;
    }
    public double getAmount()// method to return the amount
    {
        return amount;
    }
}
