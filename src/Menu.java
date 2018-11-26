import javax.swing.*;
import java.awt.*;

public class Menu {

    private JFrame frame;
    private Container contentPane;

    public Menu(){
        frame = new JFrame("Event Organizer");

        contentPane = frame.getContentPane();

        frame.setSize(800,600);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane.add(login());

        frame.setVisible(true);
    }

    private JPanel login(){
        JPanel setupPanel = new JPanel();
        setupPanel.setLayout(null);

        JTextField username = new JTextField();
        JTextField password = new JTextField();
        JLabel label = new JLabel();
        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(e -> {
            String check = username.getText();
            String check2 = password.getText();
            if(!check.equals(DBLogin.checkUsername(check))){
                label.setText("No such Username");
            }
            else{
                if(check2.equals(DBLogin.checkPassword(check))){
                    label.setText("Congratz Faggot");
                }
                else{
                    label.setText("Wrong Password");
                }
            }
        });

        username.setBounds(300,200, 200, 20);
        password.setBounds(300,300,200,20);
        confirm.setBounds(300, 400, 200, 50);
        label.setBounds(400,100, 200, 20);

        setupPanel.add(username);
        setupPanel.add(password);
        setupPanel.add(confirm);
        setupPanel.add(label);

        return setupPanel;
    }
}
