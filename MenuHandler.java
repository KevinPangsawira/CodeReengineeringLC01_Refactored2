public class MenuHandler {
    private InitializeContext context;

    public MenuHandler(InitializeContext context) {
        this.context = context;
    }

    public void run() {
        while (true) {
            int choice = showMainMenu();
            switch (choice) {
                case 1 -> registerUser();
                case 2 -> login();
                case 3 -> exit();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private int showMainMenu() {
        System.out.println("\n--- Movie Ticket Booking System ---");
        System.out.println("1. Register as User");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        return context.scanner.nextInt();
    }

    private void registerUser() {
        context.scanner.nextLine();
        System.out.print("Enter username: ");
        String username = context.scanner.nextLine();
        System.out.print("Enter password: ");
        String password = context.scanner.nextLine();
        context.userManager.registerUser(username, password, "User");
    }

    private void login() {
        context.scanner.nextLine();
        System.out.print("Enter username: ");
        String username = context.scanner.nextLine();
        System.out.print("Enter password: ");
        String password = context.scanner.nextLine();

        User user = context.userManager.loginUser(username, password);
        if (user == null) return;

        if (user.getRole().equalsIgnoreCase("Admin")) {
            new AdminMenuHandler(context, user).displayMenu();
        } else {
            new UserMenuHandler(context, user).displayMenu();

        }
    }

    private void exit() {
        System.out.println("Thank you for using the Movie Ticket Booking System. Goodbye!");
        context.scanner.close();
        System.exit(0);
    }
}
