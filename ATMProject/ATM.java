package ATMProject;

import java.util.Arrays;
import ListofNotes.FiveHundred;
import ListofNotes.OneHundred;
import ListofNotes.TwoHundred;
import ListofNotes.TwoThousand;
import notes.Notes;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM
{
    public static ArrayList<Accounts>accounts = new ArrayList<>();//Static list to store admins and users
    public static ArrayList<Notes>noteList = new ArrayList<>(Arrays.asList(new TwoThousand("2000",0), new FiveHundred("500",0), new TwoHundred("200",0), new OneHundred("100",0)));//Static list to store availab;e notes in the ATMApp.ATM(2000,500,200,100)

    private static long atmbalance=0;//variable for storing the atm balance in double
    public static Scanner scanner = new Scanner(System.in);//declare the scanner class as static to use in all the class by calling through parameter

    public static ArrayList<Accounts> getAccounts() //Getter method to return the Account
    {
        return accounts;
    }
    public static void setNotesList(ArrayList<Notes> note) {   // Setter method to set the list of notes in the ATMApp.ATM

        ATM.noteList = note;

    }

    public static ArrayList<Notes> getNoteList()//Getter method to return the notelist
    {
        return noteList;
    }
    public static void setAtmBalance(long balance)//Setter method to set the ATM balance in the static variable
    {
        ATM.atmbalance = balance;
    }
    public static double getAtmBalance()//Getter method to return the value stored in the atmbalance variable
    {
        return atmbalance;
    }

      public static Accounts getCurrentUser(String userName)//static method for check whether the username is matching to the currentUser's username
    {

        for(Accounts individualUserid : ATM.getAccounts())//to pick out the user in the Accounts arraylist
        {
            if(individualUserid.getId().equals(userName))//check whether the given username is equal, if yes
            {
                return individualUserid;//returns the userid
            }
        }
        return null;//otherwise returns null
    }
    public static Accounts getCurrentAdmin(String adminName)//static method for check whether the userpassword is matching to the currentUser's password
    {
        for(Accounts individualAdminid : ATM.getAccounts())//to pick out the admin in the Accounts arraylist
        {
            if(individualAdminid.getId().equals(adminName))//check whether the given adminname is equal, if yes
            {
                return individualAdminid;//returns the adminid
            }
        }
        return null;//otherwise returns null
    }
}
