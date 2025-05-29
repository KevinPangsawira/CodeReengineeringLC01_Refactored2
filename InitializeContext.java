import java.util.Scanner;

public class InitializeContext {
    public Scanner scanner = new Scanner(System.in);
    public UserManager userManager = new UserManager();
    public MovieManager movieManager = new MovieManager();
    public ShowtimeManager showtimeManager = new ShowtimeManager();

    public InitializeContext() {
        userManager.addUserDirectly(new User("admin", "admin123", "Admin"));
    }
}
