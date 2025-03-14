public class TicketBookingApp {
    public static void main(String[] args) {
        int totalTickets = 10;
        TicketBookingSystem bookingSystem = new TicketBookingSystem(totalTickets);

        Thread[] customers = new Thread[8];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Thread(new Customer(bookingSystem, "Клиент-" + (i + 1)));
        }

        for (Thread customer : customers) {
            customer.start();
        }

        for (Thread customer : customers) {
            try {
                customer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Оставащи билети: " + bookingSystem.getAvailableTickets());
    }
}