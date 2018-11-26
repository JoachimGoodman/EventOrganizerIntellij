import javax.swing.*;
import java.awt.*;

public class Menu { // har programmets struktur

    private JFrame frame; // primære window
    private Container contentPane; // det som er inden for vinduet

    public Menu() {
        frame = new JFrame("Event Organizer"); // new object af vinduet, og giver det title

        contentPane = frame.getContentPane(); // indsætter en container ind i vinduet

        frame.setSize(800, 600); // giver vinduet et størrelse

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // giver JFrame default settings. (tryk på kryds for at lukke programmet)

        contentPane.add(login()); // tilføjer vores method som en panel ind i vores container

        frame.setVisible(true); // false som default. derfor gør vi det visible
    }

    private JPanel login() { // method som retunere et panel
        JPanel setupPanel = new JPanel(); // nyt objekt af en Panel type til at kunne indsætte "content" i vores frame
        setupPanel.setLayout(null); // sætter layout til null for ikke at være låst af Javas standard layouts og kunne rykke ting frit rundt

        //Newer 2 textfields til username og password, med koordinatsæt og størrelse
        JTextField username = new JTextField();
        username.setBounds((frame.getWidth()/2)-(200/2), 225, 200, 20);
        JTextField password = new JTextField();
        password.setBounds((frame.getWidth()/2)-(200/2), 275, 200, 20);

        //Laver en Error label uden tekst da denne skal skiftes alt efter hvilken fejl der forekommer senere
        JLabel LError = makeLabel("", -1, 310, 200, 20, 18); // Error label
        LError.setForeground(Color.RED);

        //Laver en knap med koordinatsæt og størrelse
        JButton confirm = makeButton("Confirm", (frame.getWidth()/2)-(200/2), 350, 200, 50, 20);
        confirm.addActionListener(e -> { // bruger lambda udtryk for at simplificere actionlistener method
            String check = username.getText(); // tager det tekst der står i textfeltet
            String check2 = password.getText();
            if (!check.equals(DBLogin.checkUsername(check))) {  // bruger checkUsername methode for at checke om username stemmer over ens med det som er skrevet i tekstfeltet
                LError.setText("No such Username"); // opdaterer label methode
            } else {
                if (check2.equals(DBLogin.checkPassword(check))) {
                    contentPane.remove(setupPanel);
                    contentPane.add(navigationPane());
                    frame.revalidate();
                    frame.repaint();
                } else {
                    LError.setText("Wrong Password");
                }
            }
        });

        // tilføjer vores textfelter, knap og yderligere labels
        setupPanel.add(username);
        setupPanel.add(password);
        setupPanel.add(confirm);
        setupPanel.add(LError);

        //Title label
        setupPanel.add(makeLabel("PlanOrgan", -1, 100, 250, 100, 50));
        //Username label
        setupPanel.add(makeLabel("Username", -1, 200, 200, 20, 18));
        //Password label
        setupPanel.add(makeLabel("Password", -1, 250, 200, 20, 18));

        return setupPanel;
    }

    private JPanel navigationPane(){
        JPanel setupPanel = new JPanel();
        setupPanel.setLayout(null);

        return setupPanel;
    }












    //Metode til nemt at lave labels
    private JLabel makeLabel(String title, int x, int y, int width, int height, int size) {
        JLabel label = new JLabel(title);

        if(x == -1){
            label.setBounds((frame.getWidth()/2)-(width/2), y, width, height);
        }else{
            label.setBounds(x, y, width, height);
        }
        label.setFont(new Font("Arial", Font.PLAIN, size));

        return label;
    }

    //Metode til nemt at lave knapper
    private JButton makeButton(String title, int x, int y, int width, int height, int size) {
        JButton button = new JButton(title);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.PLAIN, size));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        return button;
    }
}
