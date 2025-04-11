import java.util.*;

class Train {
    int trainNumber;
    String trainName;
    String arrival;
    String departure;
    int seatsAvailable;

    Train(int trainNumber, String trainName, String arrival, String departure, int seatsAvailable) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.arrival = arrival;
        this.departure = departure;
        this.seatsAvailable = seatsAvailable;
    }

    void displayInfo() {
        System.out.println("Train No: " + trainNumber + " | Name: " + trainName +
            " | Arrival: " + arrival + " | Departure: " + departure +
            " | Seats: " + seatsAvailable);
    }
}

class Booking {
    String passengerName;
    int trainNumber;

    Booking(String passengerName, int trainNumber) {
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
    }
}

public class TrainTicketBooking {
    static ArrayList<Train> trains = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        addDummyTrains();

        int choice;
        do {
            System.out.println("\n--- Train Ticket Booking System ---");
            System.out.println("1. View Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewTrains();
                    break;
                case 2:
                    bookTicket(sc);
                    break;
                case 3:
                    cancelTicket(sc);
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    System.out.println("Thank you for using the Train Ticket Booking System.");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    static void addDummyTrains() {
        trains.add(new Train(101, "Express A", "08:00 AM", "10:00 AM", 5));
        trains.add(new Train(102, "Express B", "11:00 AM", "01:30 PM", 3));
        trains.add(new Train(103, "Express C", "02:00 PM", "04:00 PM", 2));
    }

    static void viewTrains() {
        System.out.println("\n--- Available Trains ---");
        for (Train t : trains) {
            t.displayInfo();
        }
    }

    static void bookTicket(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter train number to book: ");
        int trainNumber = sc.nextInt();

        for (Train t : trains) {
            if (t.trainNumber == trainNumber) {
                if (t.seatsAvailable > 0) {
                    t.seatsAvailable--;
                    bookings.add(new Booking(name, trainNumber));
                    System.out.println("Ticket booked successfully for " + name);
                } else {
                    System.out.println("No seats available on this train.");
                }
                return;
            }
        }

        System.out.println("Train not found!");
    }

    static void cancelTicket(Scanner sc) {
        System.out.print("Enter your name to cancel booking: ");
        String name = sc.nextLine();

        Iterator<Booking> iterator = bookings.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Booking b = iterator.next();
            if (b.passengerName.equalsIgnoreCase(name)) {
                for (Train t : trains) {
                    if (t.trainNumber == b.trainNumber) {
                        t.seatsAvailable++;
                        break;
                    }
                }
                iterator.remove();
                System.out.println("Booking cancelled for " + name);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No booking found under the name " + name);
        }
    }

    static void viewBookings() {
        System.out.println("\n--- All Bookings ---");
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.println("Passenger: " + b.passengerName + ", Train No: " + b.trainNumber);
            }
        }
    }
}