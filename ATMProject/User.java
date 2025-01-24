package ATMProject;

public class User extends Accounts//create a user class and extends the Accounts class
{
    private static long balance = 0;//variable to store the user balance
    public User(String id, String password)//constructor to assign the values to the variable by calling the parent class constructor using super() method
    {
        super(id, password);
    }

    public void setBalance(long balance)// set the balance to the variable
    {
        User.balance = balance;
    }

    public double getBalance()// return the balance to the variable
    {
        return balance;
    }
}
