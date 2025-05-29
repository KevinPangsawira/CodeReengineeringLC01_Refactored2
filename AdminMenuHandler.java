public class AdminMenuHandler {
    private InitializeContext context;
    private User admin;
    private AdminManager adminManager;

    public AdminMenuHandler(InitializeContext context, User admin) {
        this.context = context;
        this.admin = admin;
        this.adminManager = new AdminManager(context.movieManager, context.showtimeManager);
    }

    public void choiceMenuAdmin(){
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Register a New Admin");
            System.out.println("2. Manage Movies");
            System.out.println("3. Manage Showtimes");
            System.out.println("4. View Revenue Reports");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
    }

    public void displayMenu() {
        while (true) {
            choiceMenuAdmin();
            int choice = context.scanner.nextInt();
            context.scanner.nextLine();

            switch (choice) {
                case 1 -> registerNewAdmin();
                case 2 -> adminManager.manageMovies(context.scanner);
                case 3 -> adminManager.manageShowtimes(context.scanner);
                case 4 -> adminManager.displayRevenueReports(context.scanner);
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void registerNewAdmin() {
        System.out.print("Enter new Admin username: ");
        String username = context.scanner.nextLine();
        System.out.print("Enter new Admin password: ");
        String password = context.scanner.nextLine();
        context.userManager.addAdmin(admin, username, password);
    }
}
