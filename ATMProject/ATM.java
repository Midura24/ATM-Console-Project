package ATMProject;

import notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM
{
    public static ArrayList<User>userarray = new ArrayList<>();//Arraylist for storing the user objects
    public static ArrayList<Admin>adminarray = new ArrayList<>();//Arraylist for storing the admin objects
    public static ArrayList<Notes>notearray = new ArrayList<>();//Arraylist for storing the notes objects

    private static double adminbalance=0.0;//variable for storing the atm balance in double
    public static Scanner scanner = new Scanner(System.in);//declare the scanner class as static to use in all the class by calling through parameter

    public static ArrayList<User> getUser()
    {
        return userarray;
    }
    public static ArrayList<Admin> getAdmin()
    {
        return adminarray;
    }
    public static ArrayList<Notes> getNotearray()
    {
        return notearray;
    }

    public static void setAtmBalance(double balance)
    {
        ATM.adminbalance = balance;
    }
    public static double getAtmBalance()
    {
        return adminbalance;
    }
    public static void start()//contains the main method of the program
    {
        while(true)//to start when the start() method is called
        {
            System.out.println("1.Admin\n2.User\nEnter the access:");//asking the choice from the user
            int access_input = Integer.parseInt(scanner.nextLine());//storing the choice in a variable by using wrapper class to get the output as string and converting into integer
            if(access_input==1)//if the user choice is 1(admin)
            {
                ATM.adminarray.add(new Admin("admin","admin12"));//set a default value for admin id and admin password
                AdminAction.adminlogin(scanner);//calling the adminlogin() from AdminAction class by passing the scanner as argument

            }
            else if(access_input==2)//if the user choice is 2(user)
           {
               UserAction.userlogin(scanner);//calling the userlogin() from UserAction class by passing the scanner as argument
           }
           else
           {
                System.out.println("Invalid choice");//if the userchoice is other than 1 and 2 then it will print the output as invalid choice
                break;//and breaks the while loop
           }
        }
    }
    public static void adminoption(Scanner scanner,Admin currentAdmin)//adminoption() is used for performing the operations of admin using the parameter scanner and currentAdmin
    {
        while(true)//if the method is called then it will perform this loop
        {
            System.out.println("1.Add admin account\n2.add the user account\n3.Delete user account\n4.Deposit Amount to ATM\n5.View transaction\n6.View All Users\n7.check ATM Balance\n8.Continue with main menu\nEnter the choice:");//getting the choice from the admin
            int adminchoice = Integer.parseInt(scanner.nextLine());//getting the input as string and convert it as integer and store it in the variable
            if (adminchoice == 1)//if the admin choice is 1(add admin account)
            {
                AdminAction.addAdmin(scanner);//calling the addAdmin() method from the AdminAction by passing the scanner as argument
            }
            else if (adminchoice == 2)//if the admin choice is 2(add user account)
            {
                AdminAction.adduser(scanner);//calling the adduser() method by passing the scanner argument and calling from the AdminAction class
            }
            else if (adminchoice == 3) //if the admin choice is 3(delete User account)
            {
                AdminAction.deleteUser(scanner);//calling the deleteUser() method by passing the scanner argument and calling from the AdminAction class
            }
            else if(adminchoice == 4) //if the admin choice is 4(deposit amount to atm)
            {
                AdminAction.adminDeposit(scanner,currentAdmin);//calling the adminDeposit() method by passing scanner arguments and calling from the AdminAction class
            }
            else if (adminchoice == 5) //if the admin choice is 5(view transaction)
            {
                AdminAction.transactionhistory(currentAdmin);//calling the transactionhistory() method by passing the currentAdmin as argument and calling through AdminAction class
            }
            else if (adminchoice == 6) {
                AdminAction.viewAllUser();
            }
            else if(adminchoice==7) {
                System.out.println("Amount Balance in ATM"+ATM.getAtmBalance());
            }
            else if(adminchoice==8){
                break;
            }
            else {
                System.out.println("enter the valid choice to access");
            }
        }
    }
    public static void useroptions(Scanner scanner,User currentUser)
    {
            while (true) {
            System.out.println("1.check balance\n2.withdraw\n3.deposit\n4.change pin\n5.view transaction\n6.continue with main menu\nEnter your choice");
            int userchoice = Integer.parseInt(scanner.nextLine());
            if (userchoice == 1) {
                UserAction.checkBalance(currentUser);
            } else if (userchoice == 2) {
                UserAction.withdraw(scanner, currentUser);
            } else if (userchoice == 3) {
                UserAction.deposit(scanner, currentUser);
            } else if (userchoice == 4) {
                UserAction.changePin(scanner,currentUser);
            }
            else if (userchoice == 5) {
                UserAction.viewTransaction(currentUser);
            }
            else if (userchoice == 6) {
                break;
            }
        }
    }
    public static User getCurrentUser(String userName) {
        ArrayList<User>availableUserid= ATM.getUser();
        for(User individualUserid : availableUserid)
        {
            if(individualUserid.getUsername().equals(userName))
            {
                return individualUserid;
            }
        }
        return null;
    }
    public static Admin getCurrentAdmin(String adminName)
    {
        ArrayList<Admin>availableAdminid = ATM.getAdmin();
        for(Admin individualAdminid : availableAdminid)
        {
            if(individualAdminid.getAdminId().equals(adminName))
            {
                return individualAdminid;
            }
        }
        return null;
    }
}
