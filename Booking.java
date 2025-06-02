import java.util.List;

public class Booking {
    private User user;
    private Showtime showtime;
    private List<Seat> seats;
    private boolean isCanceled = false;
    private Payment payment; // Add payment field

    public Booking(User user, Showtime showtime, List<Seat> seats) {
        this.user = user;
        this.showtime = showtime;
        this.seats = seats;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void confirmBooking(Payment payment) {
        boolean allSeatsAvailable = true;

       
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                System.out.println("Booking failed. Seat " + seat.getSeatNumber() + " is already booked.");
                allSeatsAvailable = false;
                break;
            }
        }

      
        if (allSeatsAvailable) {
            boolean paymentSuccess = payment.processPayment();
            if (paymentSuccess) {
               
                for (Seat seat : seats) {
                    seat.reserve();
                }
                this.payment = payment; 
              
                showtime.addRevenue(payment.getAmount());
                System.out.println("Booking confirmed for user " + user.getUsername());
                user.addBooking(this);
                isCanceled = false;
                payment.generateReceipt(); 
            } else {
                System.out.println("Payment failed. Booking could not be completed.");
            }
        } else {
            System.out.println("Booking failed. Please select different seats.");
        }
    }

    public void cancelBooking() {
        for (Seat seat : seats) {
            seat.release();
        }
        System.out.println("Booking cancelled for user " + user.getUsername());
        isCanceled = true;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public String getBookingSummary() {
    String status = isCanceled() ? "(Canceled)" : "(Active)";
    return "Movie: " + showtime.getMovie().getTitle() +
           ", Showtime: " + showtime.getTime() + " " + status;
    }

}