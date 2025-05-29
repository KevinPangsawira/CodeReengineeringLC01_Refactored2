import java.util.ArrayList;
import java.util.List;

public class BookingHandler {
    private InitializeContext context;
    private User user;

    public BookingHandler(InitializeContext context, User user) {
        this.context = context;
        this.user = user;
    }

    private Movie selectMovie() {
        context.movieManager.listMovies();
        System.out.print("Select the index of the movie you want to book: ");
        int movieIndex = context.scanner.nextInt();
        context.scanner.nextLine();
        Movie movie = context.movieManager.getMovie(movieIndex);
        if (movie == null) {
            System.out.println("Invalid movie selection.");
        }
        return movie;
    }

    private Showtime selectShowtime(Movie movie) {
        System.out.println("Available showtimes for " + movie.getTitle() + ":");
        context.showtimeManager.listShowtimesForMovie(movie);
        System.out.print("Select the index of the showtime you want to book: ");
        int showtimeIndex = context.scanner.nextInt();
        context.scanner.nextLine();
        Showtime showtime = context.showtimeManager.getShowtime(showtimeIndex);
        if (showtime == null) {
            System.out.println("Invalid showtime selection.");
        }
        return showtime;
    }

    private List<Seat> selectSeats(Showtime showtime) {
        List<Seat> availableSeats = showtime.getAvailableSeats();
        if (availableSeats.isEmpty()) {
            System.out.println("No available seats for the selected showtime.");
            return new ArrayList<>();
        }

        System.out.println("Available Seats:");
        for (Seat seat : availableSeats) {
            System.out.println(seat);
        }

        System.out.print("Enter the seat numbers you want to book (comma-separated, e.g., 1,2): ");
        String seatInput = context.scanner.nextLine();
        String[] seatNumbers = seatInput.split(",");

        List<Seat> seatsToBook = new ArrayList<>();
        for (String seatNumber : seatNumbers) {
            try {
                int seatNum = Integer.parseInt(seatNumber.trim());
                Seat seat = availableSeats.stream()
                        .filter(s -> s.getSeatNumber() == seatNum && !s.isBooked())
                        .findFirst()
                        .orElse(null);
                if (seat != null) {
                    seatsToBook.add(seat);
                } else {
                    System.out.println("Seat " + seatNum + " is not available. Skipping.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid seat number: " + seatNumber + ". Skipping.");
            }
        }

        return seatsToBook;
    }

    private void confirmBooking(Showtime showtime, List<Seat> selectedSeats, Payment payment) {
        Booking booking = new Booking(user, showtime, selectedSeats);
        booking.confirmBooking(payment);
    }

    public void makeBooking() {
        Movie movie = selectMovie();
        if (movie == null)
            return;

        Showtime showtime = selectShowtime(movie);
        if (showtime == null)
            return;

        List<Seat> selectedSeats = selectSeats(showtime);
        if (selectedSeats.isEmpty()) {
            System.out.println("No valid seats selected. Booking canceled.");
            return;
        }

        double totalCost = showtime.getPrice() * selectedSeats.size();
        System.out.println("Total cost: $" + totalCost);

        Payment payment = PaymentHandler.selectPaymentMethod(context.scanner, user, totalCost);
        if (payment == null) {
            System.out.println("Payment canceled. Booking could not be completed.");
            return;
        }

        confirmBooking(showtime, selectedSeats, payment);
    }


    public void cancelLatestBooking() {
        if (!user.getBookingHistory().isEmpty()) {
            Booking lastBooking = user.getBookingHistory().get(user.getBookingHistory().size() - 1);
            lastBooking.cancelBooking();
        } else {
            System.out.println("No bookings to cancel.");
        }
    }
}
