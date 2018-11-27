import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

public class Menu { // har programmets struktur

    private JFrame frame; // primære window
    private Container contentPane; // det som er inden for vinduet
    private DBLogin loginAccess = new DBLogin();
    private ArrayList<Arrangement> allArrangements = loginAccess.getArrangements();
    private boolean isPowerUser;

    public Menu() {
        frame = new JFrame("Event Organizer"); // new object af vinduet, og giver det title

        contentPane = frame.getContentPane(); // indsætter en container ind i vinduet

        frame.setSize(800, 600); // giver vinduet et størrelse

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // giver JFrame default settings. (tryk på kryds for at lukke programmet)

        contentPane.add(login()); // tilføjer vores method som en panel ind i vores container

        /////////For Testing//////////////
        //contentPane.add(secretaryPanel());

        frame.setVisible(true); // false som default. derfor gør vi det visible
    }

    private JPanel login() { // method som retunere et panel
        JPanel setupPanel = new JPanel(); // nyt objekt af en Panel type til at kunne indsætte "content" i vores frame
        setupPanel.setLayout(null); // sætter layout til null for ikke at være låst af Javas standard layouts og kunne rykke ting frit rundt

        //Newer 2 textfields til username og password, med koordinatsæt og størrelse
        JTextField usernameText = new JTextField();
        usernameText.setBounds((frame.getWidth()/2)-(200/2), 225, 200, 20);
        JTextField passwordText = new JTextField();
        passwordText.setBounds((frame.getWidth()/2)-(200/2), 275, 200, 20);

        //Laver en Error label uden tekst da denne skal skiftes alt efter hvilken fejl der forekommer senere
        //Opretter og tilføjer også yderligere labels til panelet
        JLabel LError = makeLabel("", -1, 310, 200, 20, 18); // Error label
        LError.setForeground(Color.RED);

        setupPanel.add(makeLabel("PlanOrgan", -1, 100, 250, 100, 50));
        setupPanel.add(makeLabel("Username", -1, 200, 200, 20, 18));
        setupPanel.add(makeLabel("Password", -1, 250, 200, 20, 18));


        //Laver en knap med koordinatsæt og størrelse
        JButton confirm = makeButton("Confirm", (frame.getWidth()/2)-(200/2), 350, 200, 50, 20);
        confirm.addActionListener(e -> { // bruger lambda udtryk for at simplificere actionlistener method
            String usernameInput = usernameText.getText(); // tager det tekst der står i textfeltet
            String passwordInput = passwordText.getText();

            if (!loginAccess.checkUsername(usernameInput)) {  // bruger checkUsername methode for at checke om username stemmer over ens med det som er skrevet i tekstfeltet
                LError.setText("No such Username"); // opdaterer label methode
            } else {
                if (loginAccess.checkPassword(usernameInput, passwordInput)) {
                    if(loginAccess.isPowerUser(usernameInput)){
                        isPowerUser = true;
                    }
                    else {
                        isPowerUser = false;
                    }
                    changePanel(setupPanel, secretaryPanel());
                } else {
                    LError.setText("Wrong Password");
                }
            }
        });

        // tilføjer vores textfelter og knap til panelet
        setupPanel.add(usernameText);
        setupPanel.add(passwordText);
        setupPanel.add(confirm);
        setupPanel.add(LError);

        //Returnerer vores panel så det kan indsættes i vinduets container
        return setupPanel;
    }

    private JPanel secretaryPanel(){
        JPanel setupPanel = new JPanel();
        setupPanel.setLayout(null);

        setupPanel.add(makeLabel("Arrangement", 50, 50, 200, 20, 18));
        JButton createButton = makeButton("Opret", 50, 100, 100, 25, 14);
        JButton importButton = makeButton("Importer", 125, 100, 100, 25, 14);
        JButton exportButton = makeButton("Eksporter", 200, 100, 100, 25, 14);

        //Tilføjer labels med navnene fra alle vores arrangementer og tilføjer de tre billedeknapper per navn til yderligere funktion
        for(int i = 0; i < allArrangements.size(); i++){
            setupPanel.add(makeLabel(allArrangements.get(i).getName(), 50, 200+(50*i), 200, 20, 18));
            setupPanel.add(makeImageButton(300, 200+(50*i), 20, 20, "resources/recycle_bin_20_20.png"));
            setupPanel.add(makeImageButton(322, 200+(50*i), 20, 20, "resources/tools_20_20.png"));
            setupPanel.add(makeImageButton(344, 200+(50*i), 20, 20, "resources/inspect_20_20.png"));
        }

        setupPanel.add(createButton);
        setupPanel.add(importButton);
        setupPanel.add(exportButton);

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
    //Metode til at skifte panel og opdatere vores vindue
    private void changePanel(JPanel current, JPanel next){
        contentPane.remove(current);
        contentPane.add(next);
        frame.revalidate();
        frame.repaint();
    }
    //Metode til at lave billedeknapper
    private JButton makeImageButton(int x, int y, int width, int height, String path){
        JButton imageButton = null;
        try {
            BufferedImage buttonIcon = ImageIO.read(new File(path));
            imageButton = new JButton(new ImageIcon(buttonIcon));
            imageButton.setBounds(x, y, width, height);
            imageButton.setBorder(BorderFactory.createEmptyBorder());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return imageButton;
    }
}