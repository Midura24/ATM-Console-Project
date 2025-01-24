package ATMProject;

public class Admin extends Accounts//creating a class and extends the class Accounts
{
    public Admin(String id, String password)//constructor to assign the admin id and admin password
    {
        super(id,password);//super keyword to use the constructor of the parent class(Accounts)
    }
}

