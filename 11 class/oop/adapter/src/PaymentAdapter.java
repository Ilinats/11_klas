class PaymentAdapter implements PaymentProcessor {
    private final LegacyPaymentSystem legacyPaymentSystem;

    public PaymentAdapter(LegacyPaymentSystem legacyPaymentSystem) {
        this.legacyPaymentSystem = legacyPaymentSystem;
    }

    @Override
    public void processPayment(double amount) {
        int convertedAmount = (int) Math.round(amount);
        legacyPaymentSystem.makePayment(convertedAmount);
    }
}