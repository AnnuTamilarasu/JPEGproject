import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InitializeGame extends JPanel implements KeyListener{
    private final int ROWS = 7;
    private final int COLS = 3;
    JPanel[][] hiddenGridPanels;
    RandomizeObstacles randomizeObstacles;

    InitializeGame newGame;
    GamePanel g;

    int playerRow = ROWS-1;
    int playerCol = 1;
    boolean gameOver = false;
    boolean gamePaused = false;
    JPanel playerPanel;
    String playerPath= CreateImages.basePath + "raccoon.png";
    static String gridPath = CreateImages.basePath + "gridBg.png";
    public InitializeGame(GamePanel p) {
        this.g = p;
        setBounds(398,0,1200-398, 760);
        setLayout(new GridLayout(ROWS,COLS)); // 3 rows, 1 column
        hiddenGridPanels= new JPanel[ROWS][COLS];

        initializeGrid();
        initializePlayer();
        playerKeyActions();
        randomizeObstacles = new RandomizeObstacles(hiddenGridPanels, this,p);
    }


    public void StopGame () {
        randomizeObstacles.Stop();
    }

    public void PauseGame () {
        randomizeObstacles.Stop();
    }

    public void ResumeGame () {
        if (gamePaused) {
            //randomizeObstacles.clearGridCells();
            gamePaused = false;
            requestFocusInWindow();
            //newGame.requestFocusInWindow();
            System.out.print("Done");
        }
        else {
            randomizeObstacles.Start();
            requestFocusInWindow();
        }

    }

    public void RestartGame(){
        StopGame();
        randomizeObstacles.clearGridCells();
        gamePaused=false;
        gameOver=false;
        System.out.print("restart");
        playerCol = 1;
        updatePlayerPos();
        requestFocusInWindow();
    }

    public void initializeGrid(){
        for (int row = 0; row<ROWS; row++){
            for (int col = 0; col<COLS; col++){
                hiddenGridPanels[row][col] = newGridPanel(row,col);
                this.add(hiddenGridPanels[row][col]);
            }
        }
    }
    public void initializePlayer(){
        // Create the player
        playerPanel = new JPanel();
        playerPanel.add(CreateImages.newLabel(CreateImages.icon(playerPath)));
        playerPanel.setBounds(0,0,100,98);
    }
    public void playerKeyActions(){
        // Set up keyboard controls
        addKeyListener(this);
        setFocusable(true);
        // Place the player in starting position
        updatePlayerPos();
        requestFocusInWindow();
    }
    public void updatePlayerPos() {

        for (int col = 0; col < COLS; col++) {
            hiddenGridPanels[ROWS-1][col].removeAll();
        }

        // Add player to current position
        hiddenGridPanels[playerRow][playerCol].add(playerPanel);

        // Refresh the display
        revalidate();
        repaint();
    }

    public int getPlayerPos() {
        return playerCol;
    }

    public void gameOver () {
        JOptionPane.showMessageDialog(this, "GameOver");
        gameOver  = true;
        g.updateButton();
    }

    public static JPanel newGridPanel(int r, int c){
        JPanel gp = new JPanel();
        //gp.add(CreateImages.newLabel(CreateImages.icon(gridPath)));
        gp.setBackground(new Color(224, 216, 172));
        gp.setBorder(BorderFactory.createLineBorder(new Color(242, 207, 117),3));

        return gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                if (playerCol > 0) {
                    playerCol--; // Move left
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (playerCol < COLS - 1) {
                    playerCol++; // Move right
                }
                break;
        }
        updatePlayerPos();
    }
    @Override
    public void keyReleased(KeyEvent e) {/* Not used but required*/}
    @Override
    public void keyTyped(KeyEvent e){/* Not used but required*/}
}