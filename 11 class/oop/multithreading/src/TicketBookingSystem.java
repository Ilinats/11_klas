import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private AtomicInteger availableTickets;
    private final Lock lock = new ReentrantLock();

    public TicketBookingSystem(int tickets) {
        this.availableTickets = new AtomicInteger(tickets);
    }

    public void bookTicket(String customerName, int ticketsRequested) {
        if (lock.tryLock()) {
            try {
                if (ticketsRequested <= availableTickets.get()) {
                    availableTickets.addAndGet(-ticketsRequested);
                    System.out.println(customerName + " успешно резервира " + ticketsRequested + " билет(а).");
                } else {
                    System.out.println(customerName + " неуспешен опит за резервиране на " + ticketsRequested + " билет(а). Остават само " + availableTickets.get() + ".");
                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(customerName + " не можа да резервира поради зает ресурс.");
        }
    }

    public int getAvailableTickets() {
        return availableTickets.get();
    }
}