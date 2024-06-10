package form.bankingapplication;

import java.util.ArrayList;
import java.util.Scanner;

public class Account {
    private String name, surname, password;
    private int idNumber;
    private int accountNumber;
    double amount;
    private static int lastAccountNumber = 1000; // Starting account number
    private static ArrayList<Account> accounts = new ArrayList<>();
    private TransactionHistory transactionHistory;

    public Account(String name, String surname, int idNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.password = password;
        this.amount = 0.0;
        this.accountNumber = ++lastAccountNumber;
        this.transactionHistory = new TransactionHistory();
    }

    public String getName() {
        return name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getAmount() {
        return amount;
    }

    public static void createAccount() {
        Scanner S = new Scanner(System.in);
        System.out.println("Enter your Name:");
        String name = S.nextLine();

        System.out.println("Enter your Surname:");
        String surname = S.nextLine();

        System.out.println("Enter your ID Number:");
        int idNumber = Integer.parseInt(S.nextLine());

        System.out.println("Enter a secure password:");
        String password = S.nextLine();

        Account newAccount = new Account(name, surname, idNumber, password);
        accounts.add(newAccount);

        System.out.println("Account created successfully!");
        System.out.println("Your account number is: " + newAccount.getAccountNumber());
        System.out.println("=====================================================");
    }

    public static void login() {
        Scanner S = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your Name:");
            String name = S.nextLine();

            System.out.println("Enter your Password:");
            String password = S.nextLine();

            boolean success = false;
            for (Account account : accounts) {
                if (account.getName().equals(name) && account.getPassword().equals(password)) {
                    System.out.println("Login successful! Welcome, " + name);
                    System.out.println("Your account number is: " + account.getAccountNumber());
                    success = true;
                    account.accountOptions();
                    break;
                }
            }

            if (success) {
                break;
            } else {
                System.out.println("Incorrect name or password. Please try again.");
            }
        }
        System.out.println("=====================================================");
    }

    public void accountOptions() {
        Scanner S = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. View Transaction History");
            System.out.println("5. Logout");
            System.out.println("=====================================================");

            int choice = S.nextInt();
            S.nextLine();

            Transaction transaction = new Transaction(this);
            switch (choice) {
                case 1:
                    System.out.println("Enter amount to deposit:");
                    double depositAmount = S.nextDouble();
                    S.nextLine();
                    transaction.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter amount to withdraw:");
                    double withdrawAmount = S.nextDouble();
                    S.nextLine();
                    transaction.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Enter recipient's account number:");
                    int recipientAccountNumber = S.nextInt();
                    S.nextLine();
                    System.out.println("Enter amount to transfer:");
                    double transferAmount = S.nextDouble();
                    S.nextLine(); // Consume the newline character
                    Account recipient = findAccountByNumber(recipientAccountNumber);
                    if (recipient != null) {
                        transaction.transfer(transferAmount, recipient);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 4:
                    transaction.viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
            System.out.println("=====================================================");
        }
    }

    public static Account findAccountByNumber(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public void addToTransactionHistory(String transaction) {
        this.transactionHistory.addTransaction(transaction);
    }

    public void viewTransactionHistory() {
        this.transactionHistory.viewTransactions(this.name);
    }
}
