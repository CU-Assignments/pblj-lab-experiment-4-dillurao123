class TicketBookingSystem {
    private final boolean[] seats;  
    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats];  
    }

     public synchronized void bookSeat(int seatNumber, String customerName, boolean isVIP) {
        if (seatNumber < 1 || seatNumber > seats.length) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (seats[seatNumber - 1]) {
            System.out.println(customerName + ": Seat " + seatNumber + " is already booked!");
        } else {
            seats[seatNumber - 1] = true;  
            System.out.println(customerName + (isVIP ? " (VIP)" : "") + " booked seat " + seatNumber);
        }
    }

    public void displayStatus() {
        System.out.println("Booking Status:");
        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat " + (i + 1) + ": " + (seats[i] ? "Booked" : "Available"));
        }
    }
}

class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerName;
    private final boolean isVIP;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerName, boolean isVIP) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerName = customerName;
        this.isVIP = isVIP;
        if (isVIP) {
            this.setPriority(Thread.MAX_PRIORITY);  
        } else {
            this.setPriority(Thread.MIN_PRIORITY); 
        }
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerName, isVIP);
    }
}

public class TicketBookingSystemTest {
    public static void main(String[] args) {
         TicketBookingSystem system = new TicketBookingSystem(5);
        System.out.println("Test Case 1: No Seats Available Initially");
        system.displayStatus();

         System.out.println("\nTest Case 2: Successful Booking");
        BookingThread anish = new BookingThread(system, 1, "Anish", true);   
        BookingThread bobby = new BookingThread(system, 2, "Bobby", false);  
        BookingThread charlie = new BookingThread(system, 3, "Charlie", true);  
        anish.start();
        bobby.start();
        charlie.start();

        try {
            anish.join();
            bobby.join();
            charlie.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayStatus();

         System.out.println("\nTest Case 3: Thread Priorities (VIP First)");
        BookingThread bobbyLow = new BookingThread(system, 4, "Bobby", false); 
        BookingThread anishHigh = new BookingThread(system, 4, "Anish", true); 
        anishHigh.start();
        bobbyLow.start();

        try {
            anishHigh.join();
            bobbyLow.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayStatus();

         System.out.println("\nTest Case 4: Preventing Double Booking");
        BookingThread bobbyDouble = new BookingThread(system, 1, "Bobby", false);  t
        bobbyDouble.start();

        try {
            bobbyDouble.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayStatus();

         System.out.println("\nTest Case 5: Booking After All Seats Are Taken");
        BookingThread newUser = new BookingThread(system, 3, "New User", false);  
        newUser.start();

        try {
            newUser.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayStatus();

         System.out.println("\nTest Case 6: Invalid Seat Selection");
        BookingThread invalidSeat = new BookingThread(system, 0, "Invalid User", false);  
        invalidSeat.start();

        try {
            invalidSeat.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BookingThread outOfRangeSeat = new BookingThread(system, 6, "Out of Range User", false); 
        outOfRangeSeat.start();

        try {
            outOfRangeSeat.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         System.out.println("\nTest Case 7: Simultaneous Bookings (Concurrency Test)");
        BookingThread[] users = new BookingThread[10];
        for (int i = 0; i < 10; i++) {
            users[i] = new BookingThread(system, i % 5 + 1, "User " + (i + 1), i % 2 == 0); 
            users[i].start();
        }

        try {
            for (BookingThread user : users) {
                user.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.displayStatus();
    }
}
