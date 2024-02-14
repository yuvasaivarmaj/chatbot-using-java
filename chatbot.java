import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class chatbot {
    private Connection conn;
    private String currentUser;
    public chatbot() {
        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://mysqldb1:3306/chatbot3"; // Use the container name as the hostname
            String username = "";
            String password = "";
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database.");
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
    
    

    public void login() {
        Scanner scanner = new Scanner(System.in);
        String answer = "";

        System.out.print("Do you have an account? (yes/no): ");
        if (scanner.hasNextLine()) {
            answer = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison
        } else {
            System.out.println("No input found.");
            scanner.close();
            return; // Exit method if no input is found
        }

        try {
            if (answer.equals("yes")) {
                loginWithExistingAccount(scanner);
            } else if (answer.equals("no")) {
                registerNewAccount(scanner);
            } else {
                System.out.println("Invalid answer. Please answer 'yes' or 'no'.");
            }
        } finally {
            scanner.close();
        }
    }

    private void loginWithExistingAccount(Scanner scanner) {
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter your password: ");
        String password = scanner.nextLine();

        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                currentUser = rs.getString("username");
                System.out.println("Welcome back, " + currentUser + "!");
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    private void registerNewAccount(Scanner scanner) {
        System.out.print("Please enter a new username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter a new password: ");
        String password = scanner.nextLine();

        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("That username already exists. Please choose a different one.");
            } else {
                createUser(username, password);
                currentUser = username;
                System.out.println("Welcome, " + currentUser + "!");
            }
        } catch (SQLException e) {
            System.out.println("Error checking username: " + e.getMessage());
        }
    }

    private void createUser(String username, String password) {
        try {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public String getResponse(String message) {
        try {
            String sql = "SELECT response FROM responses WHERE question LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + message + "%");

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("response");
            } else {
                saveUnansweredQuestion(message);
                return "I'm sorry, I cannot answer that question right now. I've saved your question and will get back to you soon!";
            }
        } catch (Exception e) {
            System.out.println("Error retrieving response: " + e.getMessage());
            return "I'm sorry, something went wrong. Please try again later.";
        }
    }

    private void saveUnansweredQuestion(String message) {
        try {
            String sql = "INSERT INTO unanswered_questions (user, question) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentUser);
            stmt.setString(2, message);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving unanswered question: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        chatbot chatbot = new chatbot();
        chatbot.login();
    }
}
