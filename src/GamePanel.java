import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    int h = 760;
    private JButton pauseButton;
    JButton restart;
    InitializeGame game;
    static JPanel p;
    static JLabel xpLabel;
    static JLabel iconLabel;
    private static  final String bgImgPath = CreateImages.basePath + "sidePanel.png";
    private final String pausePath = CreateImages.basePath + "pause.png";
    private final String playPath = CreateImages.basePath + "play.png";
    //private final String replayPath = CreateImages.basePath + "replay.png";
    private boolean pauseIsClicked = false;

    public GamePanel(){
        //setBounds(150, 50, 1200,940);
        setBounds(0,0, 1200,h);
        setLayout(null);
        game = new InitializeGame(this);
        add(game);

        guidePanel();

        setVisible(true);
    }
    public void setupButton() {
        pauseButton = new JButton(CreateImages.icon(playPath));
        pauseButton.setBounds(130, 540, 110, 200);
        //pauseButton.setBackground(new Color(222, 172, 159));
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);
        pauseButton.setFocusPainted(false);
        pauseButton.addActionListener(this);
        //add playButton to background
        p.setLayout(null);
        p.add(pauseButton);
    }
    public void guidePanel(){
        p = new JPanel();
        p.setBounds(0, 0, 400, h);
        p.setLayout(null);
        add(p);

        xpLabel = new JLabel("0");
        xpLabel.setBounds(150,145, 110, 48);
        xpLabel.setOpaque(false);
        xpLabel.setFont(new Font("Arial", Font.BOLD, 30));
        xpLabel.setForeground(new Color(87, 62, 42));
        p.add(xpLabel);

        setupButton();

        iconLabel = CreateImages.newLabel(CreateImages.icon(bgImgPath));
        iconLabel.setBounds(0, 0, 398, h);
        p.add(iconLabel);
    }
    public void updateXP(int xpUpdate){

        xpLabel.setText(Integer.toString(xpUpdate));
    }
    public void updateButton(){

        System.out.print("RestartGame \n");
        pauseButton.setIcon(CreateImages.icon(CreateImages.basePath + "replay.png"));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pauseButton) {
            if (game.gameOver) {
                /* Restart the game */
                pauseButton.setIcon(CreateImages.icon(playPath));
                game.RestartGame();
                pauseIsClicked = false;
            }
            if (pauseIsClicked) {
                pauseIsClicked = false;
                game.PauseGame();
                pauseButton.setIcon(CreateImages.icon(playPath));
                System.out.print("PauseGame \n");

            } else {
                pauseIsClicked = true;
                game.ResumeGame();
                game.requestFocusInWindow();
                pauseButton.setIcon(CreateImages.icon(pausePath));
                System.out.print("StartGame \n");
            }
        }
    }
}