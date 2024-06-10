package form.bankingapplication;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<String> transactions;

    public TransactionHistory() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        this.transactions.add(transaction);
    }

    public void viewTransactions(String accountName) {
        System.out.println("Transaction History for " + accountName + ":");
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
        System.out.println("=====================================================");
    }
}
