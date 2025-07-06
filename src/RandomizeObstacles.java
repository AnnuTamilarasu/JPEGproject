import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomizeObstacles {
    private Timer timer;
    static int time = 0;
    private final int ROWS = 7;
    private final int COLS = 3;
    private final Random rand = new Random();
    int xp=0;
    GamePanel panel;
    JPanel[][] grid;
    private final String timbit = CreateImages.basePath+ "timbit1.png";
    public RandomizeObstacles(JPanel[][] g, InitializeGame init, GamePanel p) {
        grid = g;
        int delay = 700; // delay in milliseconds
        this.panel = p;
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;

                // Move obstacles down by 1 row
                for (int row = ROWS - 1; row > 0; row--) {
                    for (int col = 0; col < COLS; col++) {
                        if (row == (ROWS-1)) {
                            if (init.getPlayerPos() != col) {
                                grid[row][col].removeAll();
                            }
                        }
                        // stops the game when obstacle and player position collides
                        if (grid[row - 1][col].getComponentCount() > 0) {
                            Component obstacle = grid[row - 1][col].getComponent(0);
                            grid[row - 1][col].removeAll();
                            grid[row][col].add(obstacle);

                            if (row == (ROWS-1) && init.getPlayerPos() == col) {
                                //init stands for InitializeGame parameter
                                init.gameOver();
                                timer.stop();//stops timer when collision occurs

                                xp = 0;
                                panel.updateXP(0);
                                return;
                            }

                            if(row == (ROWS-1)){
                                if(init.getPlayerPos()!= col && grid[row][col].getComponentCount()>0){
                                    xp++;//increases xp for every obstacle that reaches row 6 without hitting the player
                                    panel.updateXP(xp);
                                }
                            }
                        }
                    }
                }

                // clears the top row before placing new obstacles
                for (int col = 0; col < COLS; col++) {
                    grid[0][col].removeAll();
                }

                // adds a randomized number of obstacles in each row(either 1 or 2)
                int newObstacles = rand.nextInt(2) + 1;
                int placed = 0;
                while (placed < newObstacles) {
                    //adds each obstacle in a random column in row 0
                    int randomCol = rand.nextInt(COLS);
                    if (grid[0][randomCol].getComponentCount() == 0) {
                        JLabel obstacle = new JLabel(CreateImages.icon(timbit));
                        obstacle.setOpaque(true);
                        grid[0][randomCol].add(obstacle);
                        placed++;
                    }

                }
                // Refresh grid
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        grid[row][col].revalidate();
                        grid[row][col].repaint();
                    }
                }
            }
        });
    }
    public void Start() {
        if (timer != null) {
            timer.restart();
        }
    }
    public void Stop() {
        if (timer != null) {
            timer.stop();
        }
    }
    public void clearGridCells(){
        for(int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col].removeAll();
            }
        }
    }
}