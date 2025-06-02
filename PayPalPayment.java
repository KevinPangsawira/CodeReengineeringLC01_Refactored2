public class PayPalPayment extends Payment {
    private String email;

    public PayPalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    @Override
    public boolean processPayment() {
        System.out.println("Processing PayPal payment for $" + amount + " via " + email + "...");
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("Receipt:");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Paid Amount: $" + amount);
        System.out.println("Payment Method: PayPal");
        System.out.println("PayPal Email: " + email);
    }
}