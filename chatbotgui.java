import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class chatbotgui extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private chatbot chatbot;

    public chatbotgui() {
        super("Chatbot: Barney");
        setSize(500, 600);
        setResizable(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel startPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel welcomeLabel = new JLabel("Welcome to Chatbot: Barney");
        startPanel.add(welcomeLabel, constraints);

        constraints.gridy = 1;
        JLabel nameLabel = new JLabel("Enter your name:");
        startPanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        JTextField nameField = new JTextField(20);
        startPanel.add(nameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()) {
                    setContentPane(getChatPanel());
                    validate();
                } else {
                    JOptionPane.showMessageDialog(chatbotgui.this, "Please enter your name.");
                }
            }
        });
        startPanel.add(loginButton, constraints);

        constraints.gridx = 1;
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open registration dialog
                JOptionPane.showMessageDialog(chatbotgui.this, "Registration feature is not yet implemented.");
            }
        });
        startPanel.add(registerButton, constraints);

        setContentPane(startPanel);
        setVisible(true);
    }

    private JPanel getChatPanel() {
        JPanel chatPanel = new JPanel(new BorderLayout());

        // Add chat area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatPanel.add(scrollPane, BorderLayout.CENTER);

        // Add input field and send button
        inputField = new JTextField(30);
        inputField.addActionListener(this);
        sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        return chatPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText();
        chatArea.append("You: " + input + "\n");
        inputField.setText("");

        // Get response from Chatbot
        if (chatbot == null) {
            chatbot = new chatbot();
        }
        final String response = chatbot.getResponse(input);

        // Display response in chat area
        chatArea.append("Barney: " + response + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(chatbotgui::new);
    }
}
