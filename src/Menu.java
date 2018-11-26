import javax.swing.*;
import java.awt.*;

public class Menu { // har programmets struktur

    private JFrame frame; // primære window
    private Container contentPane; // det som er inden for vinduet

    public Menu(){
        frame = new JFrame("Event Organizer"); // new object af vinduet, og giver det title

        contentPane = frame.getContentPane(); // indsætter en container ind i container

        frame.setSize(800,600); // giver vinduet et størrelse

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // giver JFrame default settings. (tryk på kryds for at lukke programmet)

        contentPane.add(login()); // tilføjer vores method som en panel ind i vores container

        frame.setVisible(true); // false som default. derfor gør vi det visible
    }

    private JPanel login(){ // method som retunere et panel
        JPanel setupPanel = new JPanel(); // nyt objekt
        setupPanel.setLayout(null); // sætter layout til null for at kunne sætte tingene frit.

        JTextField username = new JTextField(); // newer et felt vi kan skrive i
        JTextField password = new JTextField();
        JLabel label = new JLabel(); // output text felt.
        JButton confirm = new JButton("Confirm"); // laver knap med title
        confirm.addActionListener(e -> { // bruger lambda udtryk for at simplificere actionlistener method
            String check = username.getText(); // tager det tekst der står i textfeltet
            String check2 = password.getText();
            if(!check.equals(DBLogin.checkUsername(check))){  // bruger checkUsername methode for at checke om username stemmer over ens med det som er skrevet i tekstfeltet
                label.setText("No such Username"); // opdaterer label methode
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

        // koordinater og højde/bredde til tekst og knapper
        username.setBounds(300,200, 200, 20);
        password.setBounds(300,300,200,20);
        confirm.setBounds(300, 400, 200, 50);
        label.setBounds(400,100, 200, 20);

        // tilføjer alt til panelet
        setupPanel.add(username);
        setupPanel.add(password);
        setupPanel.add(confirm);
        setupPanel.add(label);

        return setupPanel;
    }
}
