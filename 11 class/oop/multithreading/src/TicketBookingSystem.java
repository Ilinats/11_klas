import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private int availableTickets;
    private final Lock lock = new ReentrantLock();
    private final PriorityBlockingQueue<CustomerRequest> queue;
    static final AtomicInteger requestCounter = new AtomicInteger(0);

    public TicketBookingSystem(int tickets) {
        this.availableTickets = tickets;
        this.queue = new PriorityBlockingQueue<>();
    }

    public void addRequest(CustomerRequest request) {
        queue.offer(request);
    }

    public void processRequests() {
        while (!queue.isEmpty()) {
            CustomerRequest request = queue.poll();
            if (request != null) {
                bookTicket(request.customerName, request.ticketsRequested);
            }
        }
    }

    private void bookTicket(String customerName, int ticketsRequested) {
        lock.lock();
        try {
            if (ticketsRequested <= availableTickets) {
                availableTickets -= ticketsRequested;
                System.out.println(customerName + " successfully booked " + ticketsRequested + " ticket(s).");
            } else {
                System.out.println(customerName + " failed to book " + ticketsRequested + " ticket(s). Only " + availableTickets + " left.");
            }
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableTickets() {
        return availableTickets;
    }
}