import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        String userHome = System.getProperty("user.home");
        System.out.println("User Home Directory: " + userHome);

        setBounds(170, 10, 1200, 795);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        add(new HomePanel());
        setVisible(true);
    }
}
