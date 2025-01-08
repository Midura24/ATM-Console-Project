package ATMProject;

import notes.Notes;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminAction
{
    @Override
    public static void adminlogin(Scanner scanner)
    {
       label: while(true) {
            int i = 1;
            System.out.println("Enter the admin Id:");
            String adminId = scanner.nextLine();
            if (checkAdminname(adminId)) {
                while (i <= 3) {
                    System.out.println("enter the admin password:");
                    String adminPassword = scanner.nextLine();
                    if (checkAdminpin(adminPassword)) {
                    Admin currentAdmin = ATM.getCurrentAdmin(adminId);
                    System.out.println("login successfully");
                    ATM.adminoption(scanner,currentAdmin);
                    break label;
                    } else {
                        System.out.println("invalid password");
                        i++;
                    }
                }
            }
            else {
                System.out.println("Enter the correct admin id!!");
                break;
            }
        }
    }
    public static void addAdmin(Scanner scanner)
    {
        System.out.println("Enter the admin id:");
        String adminid = scanner.nextLine();
        System.out.println("Enter the admin password:");
        String adminpassword = scanner.nextLine();
        ATM.getAdmin().add(new Admin(adminid,adminpassword));
        System.out.println("Login successfully");
    }
    public static void adduser(Scanner scanner)
    {
        System.out.println("Enter user name:");
        String enterUsername = scanner.nextLine();
        System.out.println("enter user pin:");
        String enterUserpin = scanner.nextLine();
        ATM.getUser().add(new User(enterUsername,enterUserpin));
        System.out.println("Added successfully");
    }
    public static void deleteUser(Scanner scanner) {
        if(!ATM.getUser().isEmpty())
        {
            System.out.println("The users are:");
            int i=1;
            for(User user:ATM.getUser())
            {
                System.out.println(i+". "+user.getUsername());
                i++;
            }
            System.out.println("Enter the user name to remove from the list:");
            int removechoice = Integer.parseInt(scanner.nextLine());
            ATM.getUser().remove(removechoice-1);
            System.out.println("removed successfully");
        }
    }

    public static void adminDeposit(Scanner scanner,Admin currentAdmin) {
        System.out.println("Enter the deposit amount:");
        String amount = scanner.nextLine();
        long deposit_amount = Long.parseLong(amount);
        double balance = ATM.getAtmBalance() + deposit_amount;
        ATM.setAtmBalance(balance);
        if (deposit_amount % 100 == 0) {
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
                System.out.println("Rs."+deposit_amount+"deposited successfully");
                Transaction transaction = new Transaction(currentAdmin.getAdminId(),deposit_amount,"Deposit");
                currentAdmin.addTransactionHistory(transaction);
            } else {
                System.out.println("enter the valid amount");
            }
        }
    }
    public static void viewAllUser()
    {
        ArrayList<User>alluser = ATM.getUser();
        if(!alluser.isEmpty())
        {
            System.out.println("Users list");
            int i=1;
            for (User user:alluser)
            {
                System.out.println(i+":"+user.getUsername());
                i++;
            }
        }
        else
        {
            System.out.println("No account is added");
        }
    }
    public static void transactionhistory(Admin currentAdmin)
    {
       if(currentAdmin.getTransactionHistory().isEmpty()) {
           System.out.println("No transaction done");
       }
       else
       {
           for(Transaction transaction:currentAdmin.getTransactionHistory())
           {
               System.out.println("Performed by: "+transaction.getperformer());
               System.out.println("amount: "+transaction.getAmount());
               System.out.println("Type of Transaction: "+transaction.getTransactionType());
           }
       }
    }

    public static boolean checkAdminname(String adminid)
    {
        ArrayList<Admin>adminname = ATM.getAdmin();
        for(Admin individual: adminname)
        {
            if(individual.getAdminId().equals(adminid))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkAdminpin(String adminpin)
    {
        ArrayList<Admin>adminpass = ATM.getAdmin();
        for(Admin individual: adminpass)
        {
            if(individual.getAdminpass().equals(adminpin))
            {
                return true;
            }
        }
        return false;
    }
}
