import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private List<Movie> movies;

    public MovieManager() {
        this.movies = new ArrayList<>();
    }

    // Add a new movie
    public void addMovie(String title, String genre, int duration, double rating) {
        Movie newMovie = new Movie(title, genre, duration, rating);
        movies.add(newMovie);
        System.out.println("Movie added successfully: " + title);
    }

    public void updateMovie(int index, MovieInput input) {
    if (index >= 0 && index < movies.size()) {
        Movie movie = movies.get(index);
        movie.setTitle(input.title);
        movie.setGenre(input.genre);
        movie.setDuration(input.duration);
        movie.setRating(input.rating);
        System.out.println("Movie updated successfully: " + input.title);
    } else {
        System.out.println("Invalid movie index.");
    }
}

    // Delete a movie
    public void deleteMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            Movie removedMovie = movies.remove(index);
            System.out.println("Movie deleted successfully: " + removedMovie.getTitle());
        } else {
            System.out.println("Invalid movie index.");
        }
    }

    public void searchMoviesByGenre(String genre) {
        System.out.println("Movies in genre: " + genre);
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(movie.getTitle() + " (" + movie.getDuration() + " mins, Rating: " + movie.getRating() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found for genre: " + genre);
        }
    }

    // Search for movies by minimum rating
    public void searchMoviesByRating(double minRating) {
        System.out.println("Movies with rating >= " + minRating);
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getRating() >= minRating) {
                System.out.println(movie.getTitle() + " (" + movie.getDuration() + " mins, Rating: " + movie.getRating() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found with rating >= " + minRating);
        }
    }

    // Search for movies by title keyword
    public void searchMoviesByTitle(String keyword) {
        System.out.println("Movies matching: " + keyword);
        boolean found = false;
        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(movie.getTitle() + " (" + movie.getDuration() + " mins, Rating: " + movie.getRating() + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found with title containing: " + keyword);
        }
    }

    // List all movies
    public void listMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
        } else {
            System.out.println("Movies:");
            for (int i = 0; i < movies.size(); i++) {
                Movie movie = movies.get(i);
                System.out.println(i + ". " + movie.getTitle() + " - " + movie.getGenre() + " (" + movie.getDuration() + " mins, Rating: " + movie.getRating() + ")");
            }
        }
    }

    // Get a movie by index
    public Movie getMovie(int index) {
        if (index >= 0 && index < movies.size()) {
            return movies.get(index);
        }
        return null;
    }
}