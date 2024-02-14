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
            String url = "jdbc:mysql://172.17.0.2/chatbot3";
            String username = "root";
            String password = "Vijay@123";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you have an account? (yes/no): ");
        String answer = scanner.nextLine();
        
        if(answer.equalsIgnoreCase("yes")) {
            // User has an account, ask for username and password
            System.out.print("Please enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();
            
            // Check if user exists in the database
            try {
                String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
    
                if (rs.next()) {
                    // User exists, set the current user
                    currentUser = rs.getString("username");
                    System.out.println("Welcome back, " + currentUser + "!");
                } else {
                    System.out.println("Invalid username or password. Please try again.");
                    login();
                }
            } catch (SQLException e) {
                System.out.println("Error logging in: " + e.getMessage());
            }
        } else if(answer.equalsIgnoreCase("no")) {
            // User doesn't have an account, ask to register
            System.out.print("Would you like to register a new account? (yes/no): ");
            String registerAnswer = scanner.nextLine();
            
            if(registerAnswer.equalsIgnoreCase("yes")) {
                // User wants to register, ask for new username and password
                System.out.print("Please enter a new username: ");
                String username = scanner.nextLine();
                System.out.print("Please enter a new password: ");
                String password = scanner.nextLine();
                
                // Check if username already exists
                try {
                    String sql = "SELECT * FROM users WHERE username = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    ResultSet rs = stmt.executeQuery();
                    
                    if(rs.next()) {
                        System.out.println("That username already exists. Please choose a different one.");
                        login();
                    } else {
                        // Username doesn't exist, create a new user
                        createUser(username, password);
                        currentUser = username;
                        System.out.println("Welcome, " + currentUser + "!");
                    }
                } catch (SQLException e) {
                    System.out.println("Error checking username: " + e.getMessage());
                }
            } else {
                // User doesn't want to register, exit the program
                System.out.println("Goodbye!");
                System.exit(0);
            }
        } else {
            // Invalid input, ask again
            System.out.println("Invalid answer. Please answer 'yes' or 'no'.");
            login();
        }
        scanner.close();
    }
    
    private void createUser(String username2, String password2) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Please enter a password: ");
        String password = scanner.nextLine();
        scanner.close();
    
        try {
            // Check if the username already exists in the database
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                System.out.println("Sorry, that username is already taken. Please try again.");
            } else {
                // Insert the new user into the database
                sql = "INSERT INTO users (username, password) VALUES (?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
    
                // Set the current user to the new user
                currentUser = username;
    
                System.out.println("Registration successful. Welcome, " + currentUser + "!");
            }
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }
    
    public String getResponse(String message) {
        try {
            // Prepare a SQL statement to retrieve the response
            String sql = "SELECT response FROM responses WHERE question LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + message + "%");

            // Execute the query and retrieve the response
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("response");
            } else {
                // Save unanswered question in database
                sql = "INSERT INTO unanswered_questions (user, question) VALUES (?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, currentUser);
                stmt.setString(2, message);
                stmt.executeUpdate();
                
                return "I'm sorry, I cannot answer that question right now. I've saved your question and will get back to you soon!";
            }
        } catch (Exception e) {
            System.out.println("Error retrieving response: " + e.getMessage());
            return "I'm sorry, something went wrong. Please try again later.";
        }
    }


    public static void main(String[] args) {
        chatbot chatbot = new chatbot();
        chatbot.login();

        // Create a new instance of the ChatbotGUI class
        chatbotgui gui = new chatbotgui();

        // Show the GUI
        gui.setVisible(true);
    }
}
