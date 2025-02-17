public class Main {
    public static void main(String[] args) {
        PaymentProcessor paymentProcessor = new PaymentAdapter(new LegacyPaymentSystem());
        paymentProcessor.processPayment(50.50);
    }
}