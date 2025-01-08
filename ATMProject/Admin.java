package ATMProject;

import java.util.ArrayList;

public class Admin {
    private String adminId;
    private String adminpass;
    public ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public Admin(String adminId, String adminpass) {
        this.setAdminId(adminId);
        this.setAdminpass(adminpass);
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass;
    }

    public String getAdminpass() {
        return adminpass;
    }
    public ArrayList<Transaction> getTransactionHistory()
    {
        return transactionHistory;
    }
    public void addTransactionHistory(Transaction transaction)
    {
        transactionHistory.add(transaction);
    }
}

