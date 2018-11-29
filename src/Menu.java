import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

public class Menu implements MenuInterface { // har programmets struktur

    private JFrame frame; // primære window
    private Container contentPane; // det som er inden for vinduet
    private DBLogin loginAccess = new DBLogin();
    private DBArrangement arrangementData = new DBArrangement();
    private DBEvent eventData = new DBEvent();
    private ArrayList<Arrangement> allArrangements = arrangementData.getArrangements();
    private boolean isPowerUser;

    public Menu() {
        frame = new JFrame("Event Organizer"); // new object af vinduet, og giver det title

        contentPane = frame.getContentPane(); // indsætter en container ind i vinduet

        frame.setSize(800, 600); // giver vinduet et størrelse

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // giver JFrame default settings. (tryk på kryds for at lukke programmet)

        /////////Disable for testing//////
        contentPane.add(login()); // tilføjer vores method som en panel ind i vores container

        /////////Enable for Testing///////////
        //contentPane.add(secretaryPanel());

        /////////Enable for Testing///////////
        //contentPane.add(arrangementPanel());

        /////////Enable for Testing///////////
        //contentPane.add(createArrangement());

        frame.setVisible(true); // false som default. derfor gør vi det visible
    }

    public JPanel login() { // method som retunere et panel
        JPanel loginPanel = new JPanel(); // nyt objekt af en Panel type til at kunne indsætte "content" i vores frame
        loginPanel.setLayout(null); // sætter layout til null for ikke at være låst af Javas standard layouts og kunne rykke ting frit rundt

        //Newer 2 textfields til username og password, med koordinatsæt og størrelse
        JTextField usernameInput = makeTextField(-1, 225, 200, 20);
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds((frame.getWidth()/2)-(200/2), 275, 200, 20);

        //Laver en Error label uden tekst da denne skal skiftes alt efter hvilken fejl der forekommer senere
        //Opretter og tilføjer også yderligere labels til panelet
        JLabel LError = makeLabel("", -1, 310, 200, 20, 18); // Error label
        LError.setForeground(Color.RED);

        loginPanel.add(makeLabel("PlanOrgan", -1, 100, 250, 100, 50));
        loginPanel.add(makeLabel("Username", -1, 200, 200, 20, 18));
        loginPanel.add(makeLabel("Password", -1, 250, 200, 20, 18));


        //Laver en knap med koordinatsæt og størrelse
        JButton confirm = makeButton("Confirm", (frame.getWidth()/2)-(200/2), 350, 200, 50, 20);
        confirm.addActionListener(e -> { // bruger lambda udtryk for at simplificere actionlistener method

            if (!loginAccess.checkUsername(usernameInput.getText())) {  // bruger checkUsername methode for at checke om username stemmer over ens med det som er skrevet i tekstfeltet
                LError.setText("No such Username"); // opdaterer label methode
            } else {
                if (loginAccess.checkPassword(usernameInput.getText(), String.valueOf(passwordInput.getPassword()))) {
                    if(loginAccess.isPowerUser(usernameInput.getText())){
                        isPowerUser = true;
                    }
                    else {
                        isPowerUser = false;
                    }
                    changePanel(loginPanel, overview());
                } else {
                    LError.setText("Wrong Password");
                }
            }
        });

        // tilføjer vores textfelter og knap til panelet
        loginPanel.add(usernameInput);
        loginPanel.add(passwordInput);
        loginPanel.add(confirm);
        loginPanel.add(LError);

        //Returnerer vores panel så det kan indsættes i vinduets container
        return loginPanel;
    }

    public JPanel overview(){
        JPanel overviewPanel = new JPanel();
        overviewPanel.setLayout(null);

        if(isPowerUser) {
            if(allArrangements.size() == 0) {
                overviewPanel.add(makeLabel("Ingen Aktive Arrangementer", 50, 30, 250, 20, 18));
            } else {
                overviewPanel.add(makeLabel("Arrangementer:", 50, 30, 200, 20, 18));
            }

            JButton createButton = makeButton("Opret", 600, 150, 100, 25, 14);
            createButton.addActionListener(e -> {
                changePanel(overviewPanel, modifyArrangement(-1));
            });
            JButton exportButton = makeButton("Eksporter", 600, 200, 100, 25, 14);

            //Tilføjer labels med navnene fra alle vores arrangementer og tilføjer de tre billedeknapper per navn til yderligere funktion
            for (int i = 0; i < allArrangements.size(); i++) {
                final int arrayIndex = i;
                overviewPanel.add(makeLabel("" + allArrangements.get(i).getId(), 30, 80 + (35 * i), 200, 20, 14));
                overviewPanel.add(makeLabel(allArrangements.get(i).getName(), 55, 80 + (35 * i), 200, 20, 14));
                JButton recycleButton = makeImageButton(400, 80 + (35 * i), 20, 20, "resources/recycle_bin_20_20.png");

                recycleButton.addActionListener(e -> {
                    arrangementData.deleteArrangement(allArrangements.get(arrayIndex).getId());
                    allArrangements.remove(arrayIndex);
                    changePanel(overviewPanel, overview());
                });
                JButton toolsButton = makeImageButton(422, 80 + (35 * i), 20, 20, "resources/tools_20_20.png");
                toolsButton.addActionListener(e -> {
                    changePanel(overviewPanel, modifyArrangement(arrayIndex));
                });
                JButton inspectButton = makeImageButton(444, 80 + (35 * i), 20, 20, "resources/inspect_20_20.png");
                inspectButton.addActionListener(e -> {
                    allArrangements.get(arrayIndex).addEvents(eventData.getEvents(allArrangements.get(arrayIndex).getId()));
                    changePanel(overviewPanel, arrangementInfo(arrayIndex));

                });

                overviewPanel.add(makeLabel("________________________________________", 25, 68 + (35 * i), 600, 50, 20));

                overviewPanel.add(recycleButton);
                overviewPanel.add(toolsButton);
                overviewPanel.add(inspectButton);
            }

            overviewPanel.add(createButton);
            overviewPanel.add(exportButton);
        }
        overviewPanel.add(backButton("Log Ud", overviewPanel, login()));
        JButton importButton = makeButton("Importer", 600, 250, 100, 25, 14);
        importButton.addActionListener(e -> {

        });

        overviewPanel.add(importButton);

        return overviewPanel;
    }

    public JPanel arrangementInfo(int arrayIndex){
        JPanel arrangementInfoPanel = new JPanel();
        arrangementInfoPanel.setLayout(null);

        JButton createButton = makeButton("Opret", 650, 25, 100, 25, 14);
        arrangementInfoPanel.add(makeLabel(allArrangements.get(arrayIndex).getName(), 50, 50, 200, 20, 18));
        arrangementInfoPanel.add(makeLabel("Deltagere: " + allArrangements.get(arrayIndex).getParticipants(), 50, 75, 200, 20, 16));
        arrangementInfoPanel.add(makeLabel("Events:", 50, 100, 200, 20, 16));

        for(int i = 0; i < allArrangements.get(arrayIndex).getEvents().size(); i++){
            arrangementInfoPanel.add(makeLabel(allArrangements.get(arrayIndex).getEvents().get(i).getName(), 200, 200+(35*i), 200, 20, 20));
        }

        arrangementInfoPanel.add(backButton("Tilbage", arrangementInfoPanel, overview()));

        arrangementInfoPanel.add(createButton);



        return arrangementInfoPanel;
    }

    public JPanel modifyArrangement(int arrayIndex){
        JPanel modifyArrangementPanel = new JPanel();
        modifyArrangementPanel.setLayout(null);

        JTextField nameInput = makeTextField(-1, 75, 200, 20);
        JTextField participantsInput = makeTextField(-1, 125, 200, 20);

        modifyArrangementPanel.add((makeLabel("Arrangement Navn", -1, 50, 200, 20, 18)));
        modifyArrangementPanel.add((makeLabel("Antal Deltagere", -1, 100, 200, 20, 18)));

        JButton confirm = makeButton("Bekræft",-1, 175, 150, 20, 20);
        confirm.addActionListener(e -> {
            if(arrayIndex == -1) {
                arrangementData.createArrangement(nameInput.getText(), Integer.parseInt(participantsInput.getText()));
            } else {
                arrangementData.editArrangement(nameInput.getText(), Integer.parseInt(participantsInput.getText()), allArrangements.get(arrayIndex).getId());
            }
            allArrangements = arrangementData.getArrangements();
            changePanel(modifyArrangementPanel, overview());
        });

        modifyArrangementPanel.add(nameInput);
        modifyArrangementPanel.add(participantsInput);
        modifyArrangementPanel.add(confirm);
        modifyArrangementPanel.add(backButton("Anuller", modifyArrangementPanel, overview()));

        return modifyArrangementPanel;
    }

    public JPanel createEvent(){
        JPanel createEventPanel = new JPanel();
        createEventPanel.setLayout(null);



        return createEventPanel;
    }

    public JPanel updateEvent(int arrayIndex){
        JPanel updateEventPanel = new JPanel();
        updateEventPanel.setLayout(null);



        return updateEventPanel;
    }

    public JPanel eventInfo(int arrayIndex){
        JPanel eventInfoPanel = new JPanel();
        eventInfoPanel.setLayout(null);



        return eventInfoPanel;
    }










    //Metode til nemt at lave labels, hvis x koordinatet sættes til -1 bliver lablet automatisk sat i midten af skærmen
    private JLabel makeLabel(String title, int x, int y, int width, int height, int size) {
        JLabel label = new JLabel(title);

        if(x == -1){
            label.setBounds((frame.getWidth()/2)-(width/2), y, width, height);
        } else{
            label.setBounds(x, y, width, height);
        }
        label.setFont(new Font("Arial", Font.PLAIN, size));

        return label;
    }
    //Metode til nemt at lave knapper, ligesom med label metode kan de automatisk blive centreret ved at sætte x til -1
    private JButton makeButton(String title, int x, int y, int width, int height, int size) {
        JButton button = new JButton(title);
        if(x == -1) {
            button.setBounds((frame.getWidth()/2)-(width/2), y, width, height);
        } else{
            button.setBounds(x, y, width, height);
        }
        button.setFont(new Font("Arial", Font.PLAIN, size));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);

        return button;
    }
    //Udvidet metode til at lave tilbage knap, ved at bruge den eksisterende metode
    private JButton backButton(String title, JPanel current, JPanel next){
        JButton backButton = makeButton(title, 600, 500, 100, 30, 20);
        backButton.addActionListener(e -> {
            changePanel(current, next);
        });
        return backButton;
    }
    //Metode til at skifte panel og opdatere vores vindue
    private void changePanel(JPanel current, JPanel next){
        contentPane.remove(current);
        contentPane.add(next);
        frame.revalidate();
        frame.repaint();
    }
    //Metode til at lave tekstfelter, ligesom med label metode kan de automatisk blive centreret ved at sætte x til -1
    private JTextField makeTextField(int x, int y, int width, int height){
        JTextField textField = new JTextField();
        if(x == -1){
            textField.setBounds((frame.getWidth()/2)-(width/2), y, width, height);
        } else {
            textField.setBounds(x, y, width, height);
        }

        return textField;
    }
    //Metode til at lave en billedeknap
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