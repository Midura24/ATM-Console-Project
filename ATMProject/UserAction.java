package ATMProject;
import notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class UserAction//class contains the operations of user
{
    public static User userlogin(Scanner scanner) // to perform user login return type as User
    {
        while (true) //if the method is called, it will execute the loop
        {
            int i = 1;// initialize the default value to the integer variable 'i'
            System.out.println("Enter the user Id:");
            String userId = scanner.nextLine();
            if (checkUsername(userId))//if the given user id is matched to the existing one then it will execute the condition
            {
                while (i <= 3)//it executes when the password is false until the value of 'i' becomes equal to 3
                {
                    System.out.println("enter the admin password:");
                    String userPassword = scanner.nextLine();
                    if (checkUserpin(userPassword))//to the given user password is matched to the existing one then it will execute the condition
                    {
                        Accounts currentUser = ATM.getCurrentUser(userId);//object to store the currentUser's id
                        System.out.println("login successfully");
                        return (User) currentUser;//returns the object
                    } else // if the password is wrong then it will execute the else condition
                    {
                        System.out.println("invalid password");
                        i++;// it will increment the value of 'i' and execute the while loop again
                    }
                }
            } else//if the admin id is not matched then it will execute this condition
            {
                System.out.println("Enter the correct admin id!!");
                break;// it breaks the loop
            }
        }
        return null;// returns null
    }

    public static void withdraw(Scanner scanner, User currentUser) throws CloneNotSupportedException    // Method userwithdraw is created and defined
    {
        ArrayList<Notes> duplicate = new ArrayList<>();    // Arraylist to temporary storage of original Notes arraylist
        ArrayList<String> denominations = new ArrayList<>();// arraylist to store the denominations of notes
        System.out.println("Withdraw Amount: ");
        long userWithdraw = Long.parseLong(scanner.nextLine());
        if (userWithdraw <= ATM.getAtmBalance())// Check if ATM has sufficient balance
        {
            if (userWithdraw <= currentUser.getBalance())// check if user has sufficient balance
            {
                long amount = userWithdraw;      // Assigns userWithdraw to duplicate withdraw
                for (Notes note : ATM.getNoteList())//to check the notes in the notelist using objects
                {
                    duplicate.add(note.clone());    // Clone the notes list for safe manipulation
                }
                if (userWithdraw != 0) //if the withdraw amount is not equal to 0
                {
                    for (Notes dupnote : duplicate) //check the duplicate notes using th object
                    {
                        String dupNotename = dupnote.getNote();//assign the notes to the variable
                        switch (dupNotename) {
                            case "2000", "500", "200", "100":
                                userWithdraw = UserAction.withdrawAmount(userWithdraw, dupnote, denominations);   // Process withdrawal
                                break;
                        }
                    }
                    if (userWithdraw == 0)//if withdraw amount is equal to 0
                    {
                        ATM.setNotesList(duplicate);    // Update ATMApp.ATM's notes list
                        currentUser.setBalance((long) currentUser.getBalance() - amount);  // Update user's balance
                        ATM.setAtmBalance((long) (ATM.getAtmBalance() - amount));   // Sets the balance of atm
                        System.out.println("Withdraw Successful...");
                        System.out.println("ATMApp.ATM Balance : " + ATM.getAtmBalance());
                        for (String str : denominations) {
                            System.out.println(str);
                        }
                        for (Notes note : ATM.getNoteList()) {
                            System.out.println("Number of " + note.getNote() + " are " + note.getNotecount());
                        }
                        currentUser.getTransactions().add(new Transaction(currentUser.getId(), (long) amount, " Withdrawed"));   // Adds obj to usertransaction
                        System.out.println(currentUser.getId() + " : Withdrawed :- " + amount);
                        System.out.println("ATMApp.ATM Balance: " + ATM.getAtmBalance());
                        return;
                    }
                    System.out.println("Denominations not match...");
                    return;
                }
            }
            System.out.println("Insufficient Balance");
        }
        System.out.println("Insufficient Amount...");
    }

    public static long withdrawAmount(long userWithdraw, Notes note, ArrayList<String> denominations) // Method withdrawOperation is created and defined
    {
        long count = userWithdraw / Integer.parseInt(note.getNote());   // Calculate notes needed
        if (Long.parseLong(note.getNote()) <= userWithdraw && count <= note.getNotecount()) {
            userWithdraw = userWithdraw - (count * Integer.parseInt(note.getNote()));  // Deduct amount
            note.setNotecount((note.getNotecount() - count));      // Update note count in ATMApp.ATM
            denominations.add("You withdrawed : " + note.getNote() + " ,Count: " + count);
            return userWithdraw;
        }
        return userWithdraw;  // Return remaining amount if withdrawal is not possible
    }

    public static void deposit(Scanner scanner,User currentUser)// to deposit the amount by the currentUser
    {
        System.out.println("Enter the amount to deposit:");
        long deposit_amount = Long.parseLong(scanner.nextLine());
        long balance1 = (long) (currentUser.getBalance() + deposit_amount);
        currentUser.setBalance(balance1);//set the deposit amount to the currentuser's balance
        long balance2 = (long) (ATM.getAtmBalance() + deposit_amount);
        ATM.setAtmBalance(balance2);// set the deposit amount to the ATM balancwe
        if (deposit_amount % 100 == 0)//check whether the given amount is divisible by 100
        {
            System.out.println("Enter the denomination of 2000, 500, 200, 100 notes");
            System.out.print("Rs.2000 X ");
            long twothousandnotecount = Long.parseLong(scanner.nextLine());
            System.out.print("Rs.500 X ");
            long fiveHundrednotecount = Long.parseLong(scanner.nextLine());
            System.out.print("Rs.200 X ");
            long twoHundrednotecount = Long.parseLong(scanner.nextLine());
            System.out.print("Rs.100 X ");
            long oneHundrednotecount = Long.parseLong(scanner.nextLine());
            long totalAmount = (2000 * twothousandnotecount) + (500 * fiveHundrednotecount) + (200 * twoHundrednotecount) + (100 * oneHundrednotecount);// add all the denomination with note count and store it in variable
            if (totalAmount == deposit_amount)//execute this condition if both are equal
            {
                for (Notes notes : ATM.getNoteList()) //check the denomination using object
                {
                    String availablenote = notes.getNote();//store the notes in variable
                    switch (availablenote)//to store the notecount in the particular denominations
                    {
                        case "2000":
                            notes.setNotecount(notes.getNotecount() + twothousandnotecount);
                            break;
                        case "500":
                            notes.setNotecount(notes.getNotecount() + fiveHundrednotecount);
                            break;
                        case "200":
                            notes.setNotecount(notes.getNotecount() + twoHundrednotecount);
                            break;
                        case "100":
                            notes.setNotecount(notes.getNotecount() + oneHundrednotecount);
                            break;
                    }
                }
                System.out.println("Rs." + deposit_amount + " deposited successfully");
                currentUser.getTransactions().add(new Transaction(currentUser.getId(), deposit_amount, "deposit"));// add the deposit operation in currentUser's transaction
            }
            else// this condition will exeuctes if both amount are not equal
            {
                System.out.println("enter the valid amount");
            }
        }
    }

    public static void checkbalance(User currentUser)// to check the currentUser's balance
    {
        System.out.println("The Account Balance is " + currentUser.getBalance());
    }

    public static void changePin(Scanner scanner)//to change the currentUser's password
    {
        for(Accounts currentUser:ATM.getAccounts())// create an object to check the User in Accounts arraylist
        {
            if(currentUser instanceof User)// to initialize the currentUser object to the User subclass
            {
                System.out.println("Enter the current password:");
                String currentPin = scanner.nextLine();
                if (currentUser.getPassword().equals(currentPin))//if the currentUser's password and the given password is equal then it execute the condition to change the new password
                {
                    System.out.println("Enter the new password");
                    String newPin = scanner.nextLine();
                    currentUser.setPassword(newPin);// set the new password to the currentUser
                    System.out.println("Pin changed successfully");
                }
                System.out.println("The new password successfully changed");
            }
        }
    }

    public static void viewTransaction(User currentUser)// to view the transaction of currentUser
    {
        if(currentUser.getTransactions().isEmpty())//if the current user's transaction is empty then it will execute if condition
        {
            System.out.println("No Transaction found");
        }
        else {
            for(Transaction transaction: currentUser.getTransactions())//creating an object to check the user transaction in transaction arraylist
            {
                System.out.println(transaction.getperformer() + " has " + transaction.getTransactionType() + " Rs. " + transaction.getAmount());
            }
        }
    }

    public static boolean checkUsername(String userid)// method to check the user id
    {
        for(Accounts individualuser:  ATM.getAccounts())//to check each objects in the Accounts arraylist
        {
            if(individualuser instanceof User)// to initialize the individual object to the User subclass
            {
                if (individualuser.getId().equals(userid))//check whether the passing parameter (admin id) and the stored admin id are same, if true then it will execute if condition
                {
                    return true;//returns true
                }
            }
        }
        return false;// else returns false
    }
    public static boolean checkUserpin(String userpin)// method to check the user password
    {
        for(Accounts individualuser:  ATM.getAccounts())//to check each objects in the Accounts arraylist
        {
            if(individualuser instanceof User)// to initialize the individual object to the User subclass
            {
                if (individualuser.getPassword().equals(userpin))//check whether the passing parameter (admin password) and the stored admin password are same, if true then it will execute if condition
                {
                    return true;//returns true
                }
            }
        }
        return false;//else returns false
    }
}
