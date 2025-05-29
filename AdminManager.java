import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminManager {
    private MovieManager movieManager;
    private ShowtimeManager showtimeManager;

    public AdminManager(MovieManager movieManager, ShowtimeManager showtimeManager) {
        this.movieManager = movieManager;
        this.showtimeManager = showtimeManager;
    }

    // Display total revenue with options
    public void displayRevenueReports(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Revenue Reports ---");
            System.out.println("1. Total Revenue");
            System.out.println("2. Revenue by Movie");
            System.out.println("3. Revenue by Showtime");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTotalRevenue();
                    break;
                case 2:
                    showRevenueByMovie();
                    break;
                case 3:
                    showRevenueByShowtime();
                    break;
                case 4:
                    return; // Exit the revenue reports menu loop and go back to Admin menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Calculate and display total revenue
    private void showTotalRevenue() {
        double totalRevenue = 0.0;
        for (Showtime showtime : showtimeManager.getShowtimes()) {
            totalRevenue += showtime.getTotalRevenue();
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    // Calculate revenue by movie
    private void showRevenueByMovie() {
        Map<Movie, Double> movieRevenueMap = new HashMap<>();

        for (Showtime showtime : showtimeManager.getShowtimes()) {
            Movie movie = showtime.getMovie();
            double revenue = movieRevenueMap.getOrDefault(movie, 0.0);
            movieRevenueMap.put(movie, revenue + showtime.getTotalRevenue());
        }

        System.out.println("Revenue by Movie:");
        for (Map.Entry<Movie, Double> entry : movieRevenueMap.entrySet()) {
            System.out.println(entry.getKey().getTitle() + ": $" + entry.getValue());
        }
    }

    // Calculate revenue by showtime
    private void showRevenueByShowtime() {
        System.out.println("Revenue by Showtime:");
        for (Showtime showtime : showtimeManager.getShowtimes()) {
            System.out.println("Movie: " + showtime.getMovie().getTitle() + " | Showtime: " + showtime.getTime() +
                    " | Revenue: $" + showtime.getTotalRevenue());
        }
    }

    // Method to manage movies
    public void manageMovies(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Movies ---");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. List Movies");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter duration (in mins): ");
                    int duration = scanner.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    movieManager.addMovie(title, genre, duration, rating);
                    break;

                case 2:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new genre: ");
                    genre = scanner.nextLine();
                    System.out.print("Enter new duration (in mins): ");
                    duration = scanner.nextInt();
                    System.out.print("Enter new rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();
                    movieManager.updateMovie(index, title, genre, duration, rating);
                    break;

                case 3:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie to delete: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    movieManager.deleteMovie(index);
                    break;

                case 4:
                    movieManager.listMovies();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to manage showtimes
    public void manageShowtimes(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manage Showtimes ---");
            System.out.println("1. Add Showtime");
            System.out.println("2. Update Showtime");
            System.out.println("3. Delete Showtime");
            System.out.println("4. List Showtimes");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    movieManager.listMovies();
                    System.out.print("Enter the index of the movie for the showtime: ");
                    int movieIndex = scanner.nextInt();
                    scanner.nextLine();
                    Movie movie = movieManager.getMovie(movieIndex);
                    if (movie != null) {
                        System.out.print("Enter time (e.g., 18:00): ");
                        String time = scanner.nextLine();
                        System.out.print("Enter number of seats: ");
                        int seats = scanner.nextInt();
                        System.out.print("Enter ticket price: ");
                        double price = scanner.nextDouble(); // New input for ticket price
                        scanner.nextLine();
                        showtimeManager.addShowtime(movie, time, seats, price); // Pass the price to addShowtime
                    } else {
                        System.out.println("Invalid movie index.");
                    }
                    break;

                case 2:
                    showtimeManager.listShowtimes();
                    System.out.print("Enter the index of the showtime to update: ");
                    int index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new time: ");
                    String time = scanner.nextLine();
                    System.out.print("Enter new number of seats: ");
                    int seats = scanner.nextInt();
                    System.out.print("Enter new ticket price: ");
                    double price = scanner.nextDouble(); // New input for ticket price
                    scanner.nextLine();
                    showtimeManager.updateShowtime(index, time, seats, price); // Pass the price to updateShowtime
                    break;

                case 3:
                    showtimeManager.listShowtimes();
                    System.out.print("Enter the index of the showtime to delete: ");
                    index = scanner.nextInt();
                    scanner.nextLine();
                    showtimeManager.deleteShowtime(index);
                    break;

                case 4:
                    showtimeManager.listShowtimes();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Calculate and display total revenue from all bookings
    private void displayTotalRevenue() {
        double totalRevenue = 0.0;

        // Sum up revenue from all showtimes
        for (Showtime showtime : showtimeManager.getShowtimes()) {
            totalRevenue += showtime.getTotalRevenue();
        }

        System.out.println("Total Revenue: $" + totalRevenue);
    }

    
}