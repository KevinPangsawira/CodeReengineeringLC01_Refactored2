public class UserMenuHandler {
    private InitializeContext context;
    private User user;
    private BookingHandler bookingHandler;
    private MovieSearchHandler movieSearchHandler;

    public UserMenuHandler(InitializeContext context, User user) {
        this.context = context;
        this.user = user;
        this.bookingHandler = new BookingHandler(context, user);
        this.movieSearchHandler = new MovieSearchHandler(context.scanner, context.movieManager);
    }

    public void choiceMenuUser(){
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Booking History");
            System.out.println("2. Search for Movies");
            System.out.println("3. Make a Booking");
            System.out.println("4. Cancel a Booking");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
    }

    public void displayMenu() {
        while (true) {
            choiceMenuUser();
            int userChoice = context.scanner.nextInt();
            context.scanner.nextLine(); 

            switch (userChoice) {
                case 1 -> viewBookingHistory();
                case 2 -> movieSearchHandler.searchMovies();
                case 3 -> bookingHandler.makeBooking();
                case 4 -> bookingHandler.cancelLatestBooking();
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewBookingHistory() {
        user.viewBookingHistory();
    }
}
