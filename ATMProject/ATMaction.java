package ATMProject;

import java.util.Scanner;

public class ATMaction {

    public static void start() throws CloneNotSupportedException//method to start the application
    {
        while(true)//to start when the start() method is called
        {
            System.out.println("1.Admin\n2.User\nEnter the access:");
            Scanner scanner = new Scanner(System.in);
            int access_input = Integer.parseInt(scanner.nextLine());//storing the choice in a variable by using wrapper class to get the output as string and converting into integer
            switch (access_input)//if the user choice is 1(admin)
            {
                case 1:
                    ATM.accounts.add(new Admin("admin", "admin12"));//set a default value for admin id and admin password
                    Admin currentadmin = AdminAction.adminlogin(scanner);//for admin login
                    if(currentadmin!=null) {// if it returns the object, then it will show the admin options
                        ATMaction.adminoption(scanner, currentadmin);
                    }
                    break;
                case 2:
                    User currentUser = UserAction.userlogin(scanner);//for user login
                    if(currentUser!=null)//if it returns the object, then it will show the user options
                    {
                        ATMaction.useroptions(scanner,currentUser);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");//if the userchoice is other than 1 and 2 then it will print the output as invalid choice
                    break;//breaks the while loop
            }
        }
    }

    public static void adminoption(Scanner scanner,Admin currentAdmin)//adminoption() is used for performing the operations of admin using the parameter scanner and currentAdmin
    {
        while(true)//if the method is called then it will perform this loop
        {
            System.out.println("1.Add admin account\n2.add the user account\n3.Delete user account\n4.Deposit Amount to ATM\n5.View transaction\n6.View All Users\n7.check ATM Balance\n8.Continue with main menu\nEnter the choice:");//getting the choice from the admin
            int adminchoice = Integer.parseInt(scanner.nextLine());
            switch (adminchoice) {
                case 1:
                    AdminAction.addAdmin(scanner);// to execute the function
                    break;
                case 2:
                    AdminAction.adduser(scanner);//to add user
                    break;
                case 3:
                    AdminAction.deleteUser(scanner);//to delete user
                    break;
                case 4:
                    AdminAction.adminDeposit(scanner,currentAdmin);//to deposit amount by admin
                    break;
                case 5:
                    AdminAction.viewAllTransaction(scanner);//to view transaction of both user and admin
                    break;
                case 6:
                    AdminAction.viewAllUser();// to view all the users
                    break;
                case 7:
                    System.out.println("Amount Balance in ATM" + ATM.getAtmBalance());//to check the atm balance
                    break;
                case 8:
                    return;// return to the start() method
                default:
                    System.out.println("enter the valid choice to access");
                    break;// if the choice is not match
            }
        }
    }

    public static void useroptions(Scanner scanner,User currentUser) throws CloneNotSupportedException // to perform user options
    {
        while (true)// if the function called is true, it will execute the loop
        {
            System.out.println("1.check balance\n2.withdraw\n3.deposit\n4.change pin\n5.view transaction\n6.continue with main menu\nEnter your choice");
            int userchoice = Integer.parseInt(scanner.nextLine());
            switch(userchoice)
            {
                case 1://if the choice is 1 it checks the current user's balance
                   UserAction.checkbalance(currentUser);
                    break;
                case 2:// if the option is 2 it used to withdraws the amount
                    UserAction.withdraw(scanner, currentUser);
                    break;
                case 3:// if the option is 3 it used to deposit the amount
                    UserAction.deposit(scanner, currentUser );
                    break;
                case 4:// if the option is 4 it used to change the current user's password
                    UserAction.changePin(scanner);
                    break;
                case 5:// if the option is 5 it checks the current user's transactions
                    UserAction.viewTransaction(currentUser);
                    break;
                case 6:// if the option is 6 it will exit the loop
                    return;
                default:// if the option is other than these then it display the given statement and break the loop
                    System.out.println("enter the valid choice to access");
                    break;
            }
        }
    }
}
