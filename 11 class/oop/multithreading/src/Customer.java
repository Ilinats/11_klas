import java.util.Random;

class Customer implements Runnable {
    private final TicketBookingSystem bookingSystem;
    private final String name;
    private final Random random = new Random();

    public Customer(TicketBookingSystem bookingSystem, String name) {
        this.bookingSystem = bookingSystem;
        this.name = name;
    }

    @Override
    public void run() {
        int ticketsRequested = random.nextInt(5) + 1;
        bookingSystem.addRequest(new CustomerRequest(name, ticketsRequested));
    }
}