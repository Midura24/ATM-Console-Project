package ATMProject;

public class Transaction {
    public String performer;
    public double amount;
    public String transactionType;
    public Transaction(String performer, double amount, String transactionType)
    {
        this.setPerformer(performer);
        this.setAmount(amount);
        this.setTransactionType(transactionType);
    }

    public void setPerformer(String performer)
    {
        this.performer = performer;
    }
    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    public String getperformer()
    {
        return performer;
    }
    public String getTransactionType()
    {
        return transactionType;
    }
    public double getAmount()
    {
        return amount;
    }
}
