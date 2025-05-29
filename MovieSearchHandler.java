import java.util.Scanner;

public class MovieSearchHandler {
    public Scanner scanner;
    public MovieManager movieManager;

    public MovieSearchHandler(Scanner scanner, MovieManager movieManager) {
        this.scanner = scanner;
        this.movieManager = movieManager;
    }

    public void searchMovies() {
        System.out.println("Search Movies By:");
        System.out.println("1. Genre");
        System.out.println("2. Rating");
        System.out.println("3. Title Keyword");
        System.out.print("Enter your choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1 -> {
                System.out.print("Enter genre to search: ");
                String genre = scanner.nextLine();
                movieManager.searchMoviesByGenre(genre);
            }
            case 2 -> {
                System.out.print("Enter minimum rating (0 to 10): ");
                double rating = scanner.nextDouble();
                scanner.nextLine();
                movieManager.searchMoviesByRating(rating);
            }
            case 3 -> {
                System.out.print("Enter title keyword to search: ");
                String keyword = scanner.nextLine();
                movieManager.searchMoviesByTitle(keyword);
            }
            default -> System.out.println("Invalid choice.");
        }
    }
}
