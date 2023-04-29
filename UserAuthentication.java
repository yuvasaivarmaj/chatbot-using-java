import java.sql.*;

public class UserAuthentication {
    private Connection conn;

    public UserAuthentication() {
        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/chatbot3";
            String username = "root";
            String password = "Vijay@123";
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            // Prepare a SQL statement to retrieve the user's password
            String sql = "SELECT password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            // Execute the query and retrieve the user's password
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving password: " + e.getMessage());
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            // Prepare a SQL statement to insert the new user into the database
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query and return true if successful
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public void saveQuestion(String username, String question) {
        try {
            // Prepare a SQL statement to insert the new question into the database
            String sql = "INSERT INTO questions (username, question) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, question);

            // Execute the query
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error saving question: " + e.getMessage());
        }
    }

    public String getUnansweredQuestions(String username) {
        try {
            // Prepare a SQL statement to retrieve the user's unanswered questions
            String sql = "SELECT question FROM questions WHERE username = ? AND answer IS NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            // Execute the query and build a string of unanswered questions
            ResultSet rs = stmt.executeQuery();
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString("question")).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Error retrieving unanswered questions: " + e.getMessage());
            return "";
        }
    }
}
