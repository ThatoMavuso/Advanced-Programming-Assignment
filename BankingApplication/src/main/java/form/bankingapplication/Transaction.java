package form.bankingapplication;

public class Transaction {
    private Account account;

    public Transaction(Account account) {
        this.account = account;
    }

    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            account.amount += depositAmount;
            System.out.println("Successfully deposited: " + depositAmount);
            System.out.println("New balance: " + account.amount);
            account.addToTransactionHistory("Deposited: " + depositAmount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        System.out.println("=====================================================");
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > 0 && account.amount >= withdrawAmount) {
            account.amount -= withdrawAmount;
            System.out.println("Successfully withdrew: " + withdrawAmount);
            System.out.println("New balance: " + account.amount);
            account.addToTransactionHistory("Withdrew: " + withdrawAmount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
        System.out.println("=====================================================");
    }

    public void transfer(double amount, Account recipient) {
        if (amount > 0 && account.amount >= amount) {
            account.amount -= amount;
            recipient.amount += amount;
            System.out.println("Transfer successful!");
            System.out.println("New balance for " + account.getName() + ": " + account.amount);
            System.out.println("New balance for recipient " + recipient.getName() + ": " + recipient.amount);
            account.addToTransactionHistory("Transferred: " + amount + " to " + recipient.getName());
            recipient.addToTransactionHistory("Received: " + amount + " from " + account.getName());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
        System.out.println("=====================================================");
    }

    public void viewTransactionHistory() {
        account.viewTransactionHistory();
    }
}
