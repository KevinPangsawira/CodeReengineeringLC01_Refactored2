public class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment() {
       
        System.out.println("Processing credit card payment for $" + getAmount() + "...");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Card Holder: " + cardHolderName);
       
        System.out.println("Payment Successful!");
        return true;
    }

    @Override
    public void generateReceipt() {
        System.out.println("Receipt:");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Paid Amount: $" + amount);
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Payment Method: Credit Card");
    }
}