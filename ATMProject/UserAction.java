package ATMProject;
import notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;
public class UserAction
{
    public static void userlogin(Scanner scanner)
    {
        label: while(true) {
            int i = 1;
            System.out.println("Enter the user Id:");
            String userId = scanner.nextLine();
            if (checkUsername(userId)) {
                while (i <= 3) {
                    System.out.println("enter the admin password:");
                    String userPassword = scanner.nextLine();
                    if (checkUserpin(userPassword)) {
                        User currentUser = ATM.getCurrentUser(userId);
                        System.out.println("login successfully");
                        ATM.useroptions(scanner,currentUser);
                        break label;
                    } else {
                        System.out.println("invalid password");
                        i++;
                    }
                }
            }
            else {
                System.out.println("Enter the correct admin id!!");
            }
        }
    }

    public static void checkBalance(User currentUser)
    {
        for(User user:ATM.getUser())
        {
            if(user.equals(currentUser))
            {
                System.out.println("The Account Balance is "+ currentUser.getBalance());
            }
        }
    }
    public static void withdraw(Scanner scanner, User currentUser)
    {
        for(User user:ATM.getUser()) {
            System.out.println("Enter the amount to withdraw:");
            long withdraw_amount = Long.parseLong(scanner.nextLine());
            if (user.equals(currentUser)){
                if (withdraw_amount > 0 && withdraw_amount <= currentUser.getBalance())
                {
                    if (withdraw_amount % 100 == 0) {
                        double balance1 = currentUser.getBalance();
                        balance1 -= withdraw_amount;
                        currentUser.setBalance(balance1);

                        double balance2 = ATM.getAtmBalance();
                        balance2 -= withdraw_amount;
                        ATM.setAtmBalance(balance2);
                        for(Notes notes:ATM.getNotearray()) {
                            String amount = notes.getNote();
                            double count = notes.getNotecount();
                            switch(amount)
                            {
                                case "2000":
                                    System.out.println(amount+"the count of note"+count);
                                    break;
                                case "500":
                                    System.out.println(amount+"the count of note"+count);
                                    break;
                                case "200":
                                    System.out.println(amount+"the count of note"+count);
                                    break;
                            }
                        }




                        System.out.println(withdraw_amount + "is withdrawn successfully");
                        Transaction transaction = new Transaction(currentUser.getUsername(), withdraw_amount,"withdraw");
                        currentUser.setViewTransaction(transaction);
                    }
                }
                else {
                    System.out.println("Enter the amount value correctly");
                }
            }
            else
            {
                System.out.println("insufficient balance.");
            }
        }

    }
    public static void deposit(Scanner scanner,User currentUser)
    {
        for(User user:ATM.getUser())
        {
            System.out.println("Enter the amount to deposit:");
            long deposit_amount = Long.parseLong(scanner.nextLine());
            if(user.equals(currentUser))
            {
                if (deposit_amount % 100 == 0) {
                    double balance1 = currentUser.getBalance() + deposit_amount;
                    currentUser.setBalance(balance1);
                    double balance2 = ATM.getAtmBalance() + deposit_amount;
                    ATM.setAtmBalance(balance2);
                    System.out.println("Enter the denomination of 2000, 500, 200, 100 notes");
                    System.out.print("Rs.2000 X ");
                    long twothousandnotecount = Long.parseLong(scanner.nextLine());
                    System.out.print("Rs.500 X ");
                    long fiveHundrednotecount = Long.parseLong(scanner.nextLine());
                    System.out.print("Rs.200 X ");
                    long twoHundrednotecount = Long.parseLong(scanner.nextLine());
                    System.out.print("Rs.100 X ");
                    long oneHundrednotecount = Long.parseLong(scanner.nextLine());
                    long totalAmount = (2000 * twothousandnotecount) + (500 * fiveHundrednotecount) + (200 * twoHundrednotecount) + (100 * oneHundrednotecount);
                    if (totalAmount == deposit_amount) {
                        for (Notes notes:ATM.getNotearray())
                        {
                            String availablenote = notes.getNote();
                            switch(availablenote)
                            {
                                case "2000":
                                    notes.setNotecount(notes.getNotecount()+twothousandnotecount);
                                    break;
                                case "500":
                                    notes.setNotecount(notes.getNotecount()+fiveHundrednotecount);
                                    break;
                                case "200":
                                    notes.setNotecount(notes.getNotecount()+twoHundrednotecount);
                                    break;
                                case "100":
                                    notes.setNotecount(notes.getNotecount()+oneHundrednotecount);
                                    break;
                            }
                            System.out.println("Amount deposited successfully");
                        }
                        System.out.println("Rs."+deposit_amount+" deposited successfully");
                        Transaction transaction = new Transaction(currentUser.getUsername(), deposit_amount,"deposit");
                        currentUser.setViewTransaction(transaction);
                    } else {
                        System.out.println("enter the valid amount");
                    }
                }
            }
        }
    }
    public static void changePin(Scanner scanner, User currentUser)
    {

        System.out.println("Enter the current password:");
        String currentPin = scanner.nextLine();
        if (currentUser.getPassword().equals(currentPin)) {
            System.out.println("Enter the new password");
            String newPin = scanner.nextLine();
            currentUser.setPassword(newPin);
            System.out.println("Pin changed successfully");
        }
        System.out.println("The new password successfully changed");
    }

    public static void viewTransaction(User currentUser)
    {
        if(currentUser.getViewTransaction().isEmpty())
        {
            System.out.println("No Transaction found");
        }
        else {
            for(Transaction transaction: currentUser.getViewTransaction())
            {
                System.out.println("Performed by: "+transaction.getperformer());
                System.out.println("amount: "+transaction.getAmount());
                System.out.println("Type of Transaction: "+transaction.getTransactionType());
            }
        }
    }

    public static boolean checkUsername(String userid)
    {
        ArrayList<User> username = ATM.getUser();
        for(User individual: username)
        {
            if(individual.getUsername().equals(userid))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkUserpin(String userpin)
    {
        ArrayList<User>userpass = ATM.getUser();
        for(User individual: userpass)
        {
            if(individual.getPassword().equals(userpin))
            {
                return true;
            }
        }
        return false;
    }
}
