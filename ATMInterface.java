import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private List<String> transactionHistory;
    private String pinCode;
    public BankAccount(String pinCode) {
        this.balance = 1000.0; // Default initial balance
        this.transactionHistory = new ArrayList<>();
        this.pinCode = pinCode;
        transactionHistory.add(String.format("Initial deposit: %.2f", balance));
    }
    public double getBalance() {
        return balance;
    }
    public boolean validatePin(String pinCode) {
        return this.pinCode.equals(pinCode);
    }
    public boolean withdraw(double amount) {
        if (amount >= 500 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(String.format("Withdrew: %.2f", amount));
            return true;
        } else {
            return false;
        }
    }
    public boolean deposit(double amount) {
        if (amount >= 500) {
            balance += amount;
            transactionHistory.add(String.format("Deposited: %.2f", amount));
            return true;
        } else {
            return false;
        }
    }
    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
// Class to represent the ATM machine
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }
    public void withdraw(double amount) {
        if (amount >= 500) {
            if (account.withdraw(amount)) {
                System.out.printf("Withdrawal successful. Withdrew: %.2f\nCurrent balance: %.2f\n", amount, account.getBalance());
            } else {
                System.out.println("Insufficient funds or invalid amount. Withdrawal failed.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Amount must be 500 or more.");
        }
    }
    public void deposit(double amount) {
        if (amount >= 500) {
            if (account.deposit(amount)) {
                System.out.printf("Deposit successful. Deposited: %.2f\nCurrent balance: %.2f\n", amount, account.getBalance());
            } else {
                System.out.println("Invalid deposit amount. Deposit failed.");
            }
        } else {
            System.out.println("Invalid deposit amount. Amount must be 500 or more.");
        }
    }
    public void checkBalance() {
        System.out.printf("Current balance : %.2f\n", account.getBalance());
    }
    public void printTransactionHistory() {
        account.printTransactionHistory();
    }
}
// User interface for the ATM
public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set your PIN code: ");
        String pinCode = scanner.nextLine();
        BankAccount account = new BankAccount(pinCode); // Use default initial balance and set pin code
        ATM atm = new ATM(account);
        // Main loop for ATM operations
        while (true) {
            System.out.println("\n\t\t\t\t\t\tATM MACHINE:");
            System.out.println("* For Withdraw enter 1");
            System.out.println("* For Deposit enter 2");
            System.out.println("* For Checking Balance enter 3");
            System.out.println("* For Viewing Transaction History enter 4");
            System.out.println("* To Exit enter 5");
            System.out.print("\nEnter the option: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
                continue;
            }
            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter your PIN: ");
                scanner.nextLine(); // Consume the newline character
                String inputPin = scanner.nextLine();

                if (!account.validatePin(inputPin)) {
                    System.out.println("Invalid PIN. Please try again.");
                    continue;
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw : ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter amount to deposit : ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    atm.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM MACHINE. Visit Again !\nFor any other details, contact your branch.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please enter a correct option.");
            }
        }
    }
}
