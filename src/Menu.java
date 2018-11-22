import javax.swing.*;
import java.awt.*;

public class Menu {

    private JFrame frame;
    private Container contentPane;
    private JPanel setupPanel;

    public Menu(){
        frame = new JFrame("Event Organizer");
        setupPanel = new JPanel();
        setupPanel.setLayout(null);

        contentPane = frame.getContentPane();

        frame.setSize(800,600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.add(setupPanel);
        login();

        frame.setVisible(true);
    }

    public void login(){
        JTextField username = new JTextField();
        JTextField password = new JTextField();
        JLabel label = new JLabel();
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            String check = username.getText();
            label.setText("Test: " + DBController.Controller("SELECT PASSWORD FROM LogIn WHERE USERNAME = 'Testing'"));
        });

        username.setBounds(300,200, 200, 20);
        password.setBounds(300,300,200,20);
        confirm.setBounds(300, 400, 200, 50);
        label.setBounds(400,100, 200, 20);

        setupPanel.add(username);
        setupPanel.add(password);
        setupPanel.add(confirm);
        setupPanel.add(label);
    }
}
