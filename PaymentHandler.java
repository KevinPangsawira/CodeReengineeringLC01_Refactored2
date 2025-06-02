import java.util.Scanner;

public class PaymentHandler {

    public static void showMenuPayment(){
        System.out.println("\nChoose Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Wallet");
        System.out.print("Enter your choice: ");
    }

    public static Payment selectPaymentMethod(Scanner scanner, User user, double totalAmount) {
        showMenuPayment();
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); 

        Payment payment;
        switch (paymentChoice) {
            case 1:
                System.out.print("Enter Credit Card Number (e.g., 1234-5678-9012-3456): ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter Card Holder Name: ");
                String cardHolderName = scanner.nextLine();
                System.out.print("Enter Expiry Date (MM/YY): ");
                String expiryDate = scanner.nextLine();
                System.out.print("Enter CVV: ");
                String cvv = scanner.nextLine();

               
                payment = new CreditCardPayment(totalAmount, cardNumber, cardHolderName, expiryDate, cvv);
                break;
            case 2:
                System.out.print("Enter PayPal email: ");
                String email = scanner.nextLine();
                payment = new PayPalPayment(totalAmount, email);
                break;
            case 3:
                System.out.print("Enter Wallet ID: ");
                String walletID = scanner.nextLine();
                payment = new WalletPayment(totalAmount, walletID);
                break;
            default:
                System.out.println("Invalid choice, payment canceled.");
                payment = null;
        }

        return payment;
    }
}
