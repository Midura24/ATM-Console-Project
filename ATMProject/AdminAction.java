package ATMProject;

import notes.Notes;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction//class contains the operations of admin
{
    public static Admin adminlogin(Scanner scanner)//to perform admin login return type as Admin
    {
        while(true) //if the method is called, it will execute the loop
        {
            int i = 1;// initialize the default value to the integer variable 'i'
            System.out.println("Enter the admin Id:");
            String adminId = scanner.nextLine();//store the admin id to the string variable
            if (checkAdminname(adminId)) //if the given admin id is matched to the existing one then it will execute the condition
            {
                while (i <= 3) //it executes when the password is false until the value of 'i' becomes equal to 3
                {
                    System.out.println("enter the admin password:");
                    String adminPassword = scanner.nextLine();//variable to store the admin password
                    if (checkAdminpin(adminPassword))//to the given admin password is matched to the existing one then it will execute the condition
                    {
                    Accounts currentAdmin = ATM.getCurrentAdmin(adminId);//object to store the currentAdmin's id
                    System.out.println("login successfully");
                    return (Admin) currentAdmin;// returns the object
                    }
                    else// if the password is wrong then it will execute the else condition
                    {
                        System.out.println("invalid password");
                        i++;// it will increment the value of 'i' and execute the while loop again
                    }
                }
            }
            else//if the admin id is not matched then it will execute this condition
            {
                System.out.println("Enter the correct admin id!!");
                break;// it breaks the loop
            }
       }
       return null;// returns null
    }

    public static void addAdmin(Scanner scanner)//to add the admin id and password
    {
        System.out.println("Enter the admin id:");
        String adminid = scanner.nextLine();// variable to store the admin id
        System.out.println("Enter the admin password:");
        String adminpassword = scanner.nextLine();// variable to store the admin password
        ATM.getAccounts().add(new Admin(adminid,adminpassword));//add the admin id and password to the account list
        System.out.println("Login successfully");
    }

    public static void adduser(Scanner scanner)//to add the user id and password
    {
        System.out.println("Enter user name:");
        String enterUsername = scanner.nextLine();//variable to store the user id
        System.out.println("enter user pin:");
        String enterUserpin = scanner.nextLine();// variable to store the user password
        ATM.getAccounts().add(new User(enterUsername,enterUserpin));// add the user id and password to the account list
        System.out.println("Added successfully");
    }

    public static void deleteUser(Scanner scanner) // to delete the user id and password
    {
        System.out.println("Enter User ID to Delete: ");
        String deleteuser = scanner.nextLine();// variable to store the user id to delete
        for (Accounts user : ATM.getAccounts()) //create an object to check the objects in the account
        {
            if (user instanceof User)// to initialise the user object to the User subclass
            {
                if (user.getId().equals(deleteuser))//to check whether the current user id and the getting is equal or not. if it equals then it will execute the if condition
                {
                    ATM.getAccounts().remove(user);//using remove() method remove the user from the Account list
                    System.out.println("User Deleted Successfully...");
                    return;
                }
            }
        }
        System.out.println("ATMApp.User ID not Found...");
    }

    public static void adminDeposit(Scanner scanner, Admin currentAdmin)//to deposit the amount by the admin to the ATM
    {
                System.out.println("Enter the deposit amount:");
                long deposit_amount = Long.parseLong(scanner.nextLine());// get the depositing amount from the admin and store it in the variable of type long by typecasting
                long balance = (long) (ATM.getAtmBalance() + deposit_amount);//add the depositing amount to the ATM balance
                ATM.setAtmBalance(balance);// set it to it
                if (deposit_amount % 100 == 0)//check whether the given amount is divisible by 100 or not, if yes then execute this condition
                {
                    System.out.println("Enter the denomination of 2000, 500, 200, 100 notes");
                    System.out.print("Rs.2000 X ");
                    long twothousandnotecount = Long.parseLong(scanner.nextLine());// getting the count of the 2000 rs note
                    System.out.print("Rs.500 X ");
                    long fiveHundrednotecount = Long.parseLong(scanner.nextLine());// getting the count of the 500 rs note
                    System.out.print("Rs.200 X ");
                    long twoHundrednotecount = Long.parseLong(scanner.nextLine());// getting the count of the 200 rs note
                    System.out.print("Rs.100 X ");
                    long oneHundrednotecount = Long.parseLong(scanner.nextLine());// getting the count of the 100 rs note
                    long totalAmount = (2000 * twothousandnotecount) + (500 * fiveHundrednotecount) + (200 * twoHundrednotecount) + (100 * oneHundrednotecount);// make total of the denominations
                    if (totalAmount == deposit_amount) // if the total amount is matches with depositing amount then it will execute the condition

                    {
                        for (Notes notes : ATM.getNoteList())// creating the object to the note arraylist
                        {
                            String availablenote = notes.getNote();// storing the notes in a string variable
                            switch (availablenote)//using switch case performs the operation for each avaliable note
                            {
                                case "2000":// if the available note is 2000, then it will set the count of the note setNotecount
                                    notes.setNotecount(notes.getNotecount() + twothousandnotecount);
                                    break;
                                case "500":// if the available note is 500, then it will set the count of the note to the setNotecount
                                    notes.setNotecount(notes.getNotecount() + fiveHundrednotecount);
                                    break;
                                case "200":// if the available note is 200, then it will set the count of the note to the setNotecount
                                    notes.setNotecount(notes.getNotecount() + twoHundrednotecount);
                                    break;
                                case "100":// if the available note is 100, then it will set the count of the note to the setNotecount
                                    notes.setNotecount(notes.getNotecount() + oneHundrednotecount);
                                    break;
                            }
                        }
                        System.out.println("Rs." + deposit_amount + "deposited successfully");
                        currentAdmin.getTransactions().add(new Transaction(currentAdmin.getId(), deposit_amount, "Deposit"));// add the transaction type, performer and amount to the transactions
                    } else //if the depositing amount is not divisible by 100, then it will execute this condition
                    {
                        System.out.println("enter the valid amount");
                    }
                }
            }
    public static void viewAllUser()//to view all the users available in account arraylist
    {
        ArrayList<Accounts>alluser = ATM.getAccounts();// store the account arraylist in another arraylisst
        if (!alluser.isEmpty())//if that arraylist is  not empty then it will execute this condition
        {
            System.out.println("Users list");
            int i = 1;//initialise the integer variable 'i' with default value of 1
            for (Accounts user : alluser)// create an object for the new arraylist
            {
                if (user instanceof User)// to initialise the user object to the User subclass
                {
                    System.out.println(i + ":" + user.getId());// list the user id with 'i' value
                    i++;// increment the i value
                }
            }
        }
        else//if the arraylist is empty then it will execute else condition
        {
            System.out.println("No account is added");
        }
    }

    public static void viewAllTransaction(Scanner scan) // Method viewAllTransactionHistory is created and defined
    {
        System.out.println("1.Admin Transactions\n2.User Transactions\n3.view All Transactions\nEnter your choice: ");
        int options = Integer.parseInt(scan.nextLine());// store the choice in the variable by type casting
        switch (options) {
            case 1:// Prints the transactions of admin
                for (Accounts currentAdmin : ATM.getAccounts())//create the object to check all the objects inside the Accounts arraylist
                {
                    if (currentAdmin instanceof Admin)// to initialise the currentAdmin object to the Admin subclass
                    {
                        for (Transaction admins : currentAdmin.getTransactions())//to check all the admin transactions inside the transaction arraylist
                        {
                            System.out.println(admins.getperformer() + " has " + admins.getTransactionType() + " Rs." + admins.getAmount());
                        }
                    }
                }

                break;
            case 2:// Prints the transaction of users

                System.out.println("Enter the user name to view the transaction:");
                String usertransaction = scan.nextLine();// store the user id in the string variable
                for (Accounts currentUser : ATM.getAccounts())// object to check the user in the account arraylist
                {
                    if(currentUser.getId().equals(usertransaction))// if the currentUser and input user id are same then it execute the condition
                    {
                        if(currentUser.getTransactions().isEmpty())//if the current user's transaction is empty then it will execute if condition
                        {
                            System.out.println("No transaction occurs");
                        }
                        for (Transaction users : currentUser.getTransactions())//creating an object to check the user transaction in transaction arraylist
                        {
                            System.out.println(users.getperformer() + " has " + users.getTransactionType() + " Rs. " + users.getAmount());
                        }
                    }
                }
                break;

            case 3:           // Prints both admin and user transaction
                System.out.println("Admin Transactions\n");
                for (Accounts currentAdmin : ATM.getAccounts())// create an object to check the admin in Accounts arraylist
                {
                    if (currentAdmin instanceof Admin)// to initialise the currentAdmin object to the Admin subclass
                    {
                        for (Transaction admins : currentAdmin.getTransactions())// create an object to check the admin transaction in transaction arraylist
                        {
                            System.out.println(admins.getperformer() + " has " + admins.getTransactionType() + " Rs." + admins.getAmount());
                        }
                    }
                }

                System.out.println("User Transactions\n");
                for (Accounts currentUser: ATM.getAccounts())//object to check the user in Accounts arraylist
                {
                    if (currentUser instanceof User)// to initialize the currentUser object to the User subclass
                    {
                        for (Transaction users : currentUser.getTransactions())// create an object to check the user transaction in transaction arraylist
                        {
                            System.out.println(users.getperformer() + " has " + users.getTransactionType() + " Rs. " + users.getAmount());
                        }
                    }
                }
                break;
        }
    }
    public static boolean checkAdminname(String adminid)//method to check the adminid
    {
        for (Accounts individual : ATM.getAccounts()) //to check each objects in the Accounts arraylist
        {
            if(individual instanceof Admin) // to initialize the individual object to the Admin subclass
            {
                if (individual.getId().equals(adminid))//check whether the passing parameter (admin id) and the stored admin id are same, if true then it will execute if condition
                {
                    return true;// returns true
                }
            }
        }
        return false;// else returns false
    }

    public static boolean checkAdminpin(String adminpin)// method to check the admin password
    {
        for (Accounts individual : ATM.getAccounts())//to check each objects in the Accounts arraylist
        {
            if (individual instanceof Admin) // to initialize the individual object to the Admin subclass
            {
                if (individual.getPassword().equals(adminpin))//check whether the passing parameter (admin password) and the stored admin password are same, if true then it will execute if condition
                {
                    return true;// returns true
                }
            }
        }
        return false;// else returns false
    }
}
