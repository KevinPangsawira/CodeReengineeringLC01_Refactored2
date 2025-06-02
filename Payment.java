public abstract class Payment {
    protected double amount;
    protected String transactionID;

    public Payment(double amount) {
        this.amount = amount;
        this.transactionID = generateTransactionID();
    }

   
    private String generateTransactionID() {
        return "TXN" + System.currentTimeMillis();
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

   
    public abstract boolean processPayment();

    public abstract void generateReceipt();
}