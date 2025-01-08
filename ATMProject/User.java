package ATMProject;

import java.util.ArrayList;

public class User {
    private String username;
    private String userpassword;
    private static double userbalance=0.0;
    public ArrayList<Transaction>viewTransaction = new ArrayList<>();
    public User(String username, String userpassword)
    {
        this.setUsername(username);
        this.setPassword(userpassword);
    }

    public void setUsername(String name)
    {
        this.username = name;
    }
    public String getUsername()
    {
        return username;
    }
    public void setPassword(String password)
    {
        this.userpassword = password;
    }
    public String getPassword()
    {
        return userpassword;
    }
    public void setBalance(double balance)
    {
        User.userbalance = balance;
    }
    public double getBalance()
    {
        return userbalance;
    }
    public ArrayList<Transaction> getViewTransaction()
    {
        return viewTransaction;
    }
    public void setViewTransaction(Transaction transaction)
    {
        viewTransaction.add(transaction);
    }
}