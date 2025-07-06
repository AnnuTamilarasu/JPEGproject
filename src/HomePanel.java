import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener {
    private JLabel background;
    private JButton startButton;
    GamePanel gP;

    private final String bgImgPath = CreateImages.basePath + "home.png";
    private final String startPath = CreateImages.basePath + "homePlay.png";
    private final String missionImgPath = CreateImages.basePath + "missionPg.png";
    private boolean playIsClicked = false;
    private boolean startIsClicked = false;

    public HomePanel() {
        setBounds(0,0, 1200,765);
        setLayout(null);
        bg();
        gP = new GamePanel();
        setupButton(475, 220);
        setVisible(true);
    }

    public void setupButton(int xPos, int yPos) {
        startButton = new JButton(CreateImages.icon(startPath));
        startButton.setBounds(xPos,yPos, 380, 150);
        startButton.setBackground(new Color(222, 172, 159));
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.addActionListener(this);
        startButton.setContentAreaFilled(false);
        //add playButton to background
        background.setLayout(null);
        background.add(startButton);
    }
    public void bg() {

        background = CreateImages.newLabel(CreateImages.icon(bgImgPath));
        background.setBounds(0,0, 1200, 765);
        add(background);
    }
    public void createGamePanel(boolean isVisible){
        gP.setVisible(isVisible);
        add(gP);
    }
    public void missionPage(){
        background = CreateImages.newLabel(CreateImages.icon(missionImgPath));
        background.setBounds(0,0, 1200, 765);
        add(background);
        setupButton(450, 540);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!playIsClicked && !startIsClicked) {
            // First click: show mission page
            playIsClicked = true;
            remove(background);
            missionPage();
            revalidate();
            repaint();
        } else if (playIsClicked && !startIsClicked) {
            // Second click: start game
            startIsClicked = true;
            remove(background);
            createGamePanel(true);
            revalidate();
            repaint();
        }
    }
}
