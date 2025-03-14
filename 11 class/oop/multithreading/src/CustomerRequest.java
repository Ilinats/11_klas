class CustomerRequest implements Comparable<CustomerRequest> {
    String customerName;
    int ticketsRequested;
    int requestOrder;

    public CustomerRequest(String customerName, int ticketsRequested) {
        this.customerName = customerName;
        this.ticketsRequested = ticketsRequested;
        this.requestOrder = TicketBookingSystem.requestCounter.incrementAndGet();
    }

    @Override
    public int compareTo(CustomerRequest other) {
        return Integer.compare(this.requestOrder, other.requestOrder);
    }
}
