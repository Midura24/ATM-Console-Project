package ATMProject;

import java.util.ArrayList;

public class Accounts {
   public String id;//variable to store the id of both admin and user
   public String password;//variable to store the password of both admin and user
   public ArrayList<Transaction>transactions = new ArrayList<>();//to store the transactions of both admin and user in common arraylist

   public Accounts(String id, String password)//
   {
       this.setId(id);
       this.setPassword(password);
   }

    public void setId(String id) {
        this.id = id;
    }
    public String getId()
   {
       return id;
   }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword()
   {
       return password;
   }
   public ArrayList<Transaction> getTransactions()
   {
       return transactions;
   }
}
