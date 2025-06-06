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
                    return; 
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   
    private void showTotalRevenue() {
        double totalRevenue = 0.0;
        for (Showtime showtime : showtimeManager.getShowtimes()) {
            totalRevenue += showtime.getTotalRevenue();
        }
        System.out.println("Total Revenue: $" + totalRevenue);
    }

    
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

   
    private void showRevenueByShowtime() {
        System.out.println("Revenue by Showtime:");
        for (Showtime showtime : showtimeManager.getShowtimes()) {
            System.out.println("Movie: " + showtime.getMovie().getTitle() + " | Showtime: " + showtime.getTime() +
                    " | Revenue: $" + showtime.getTotalRevenue());
        }
    }
    

   
 


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
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> handleAddMovie(scanner);
                case 2 -> handleUpdateMovie(scanner);
                case 3 -> handleDeleteMovie(scanner);
                case 4 -> movieManager.listMovies();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleAddMovie(Scanner scanner) {
        MovieInput input = readMovieInput(scanner);
        movieManager.addMovie(input.title, input.genre, input.duration, input.rating);
    }

    private void handleUpdateMovie(Scanner scanner) {
        movieManager.listMovies();
        System.out.print("Enter the index of the movie to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        MovieInput input = readMovieInput(scanner);
        movieManager.updateMovie(index, input);
    }

    private void handleDeleteMovie(Scanner scanner) {
        movieManager.listMovies();
        System.out.print("Enter the index of the movie to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        movieManager.deleteMovie(index);
    }

    private MovieInput readMovieInput(Scanner scanner) {
        System.out.print("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter duration (in mins): ");
        int duration = scanner.nextInt();
        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine(); 
        return new MovieInput(title, genre, duration, rating);
    }

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
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> handleAddShowtime(scanner);
                case 2 -> handleUpdateShowtime(scanner);
                case 3 -> handleDeleteShowtime(scanner);
                case 4 -> showtimeManager.listShowtimes();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleAddShowtime(Scanner scanner) {
        movieManager.listMovies();
        System.out.print("Enter the index of the movie for the showtime: ");
        int movieIndex = scanner.nextInt();
        scanner.nextLine();
        Movie movie = movieManager.getMovie(movieIndex);

        if (movie != null) {
            ShowtimeInput input = readShowtimeInput(scanner);
            showtimeManager.addShowtime(movie, input.time, input.seats, input.price);
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    private void handleUpdateShowtime(Scanner scanner) {
        showtimeManager.listShowtimes();
        System.out.print("Enter the index of the showtime to update: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        ShowtimeInput input = readShowtimeInput(scanner);
        showtimeManager.updateShowtime(index, input.time, input.seats, input.price);
    }

    private void handleDeleteShowtime(Scanner scanner) {
        showtimeManager.listShowtimes();
        System.out.print("Enter the index of the showtime to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        showtimeManager.deleteShowtime(index);
    }

    private ShowtimeInput readShowtimeInput(Scanner scanner) {
        System.out.print("Enter time (e.g., 18:00): ");
        String time = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();
        System.out.print("Enter ticket price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); 
        return new ShowtimeInput(time, seats, price);
    }

}