package form.bankingapplication;

import java.util.Scanner;

public class BankingApplication {
    static int option;

    public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        while (true) {
            WelcomePage();
            System.out.println("Enter your choice: ");
            option = S.nextInt();
            S.nextLine(); // Consume the newline character
            switch (option) {
                case 1:
                    Account.createAccount();
                    break;
                case 2:
                    Account.login();
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }

    public static void WelcomePage() {
        System.out.println("#######################################################");
        System.out.println("Welcome to the Bank of South Africa (BASA)");
        System.out.println("Choose an option to proceed:");
        System.out.println("1. Create a new Bank Account");
        System.out.println("2. Log in to Existing Bank Account");
        System.out.println("########################################################");
    }
}
