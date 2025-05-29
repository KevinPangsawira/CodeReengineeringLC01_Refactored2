import java.util.ArrayList;
import java.util.List;

public class Showtime {
    private Movie movie;
    private String time;
    private List<Seat> seats;
    private double price; // New field for ticket price
    private double totalRevenue;

    public Showtime(Movie movie, String time, int totalSeats, double price) {
        this.movie = movie;
        this.time = time;
        this.price = price;
        this.seats = new ArrayList<>();
        this.totalRevenue = 0.0;

        // Initialize the seats
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i)); // Create a seat with seat number i
        }
    }

    // Getter and Setter for price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Calculate total bookings for this showtime
    public int getTotalBookings() {
        int bookedSeats = 0;
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                bookedSeats++;
            }
        }
        return bookedSeats;
    }

    // Track total revenue for analytics
    public void addRevenue(double amount) {
        this.totalRevenue += amount;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Getter methods
    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }

    // Setter methods to update showtime details
    public void setTime(String time) {
        this.time = time;
    }

    public void setAvailableSeats(int totalSeats) {
        this.seats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            seats.add(new Seat(i));
        }
    }
}