public class Main {
    public static void main(String[] args) {
        InitializeContext initializeContext = new InitializeContext();

        MenuHandler menuHandler = new MenuHandler(initializeContext);
        menuHandler.run();
    }

  
}
