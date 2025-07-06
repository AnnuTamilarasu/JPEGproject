import javax.swing.*;

public class CreateImages {
    static final String basePath="C:/Users/anany/OneDrive/Desktop/Work stuff/JPEG hackathon/JPEG/";
    public CreateImages(String imagePath){
        newPanel(imagePath);
    }
    public static JPanel newPanel(String iP){
        JPanel gp = new JPanel();
        gp.add(newLabel(icon(iP)));
        return gp;
    }
    public static JLabel newLabel(ImageIcon i){
        JLabel l = new JLabel(i);
        return l;
    }
    public static ImageIcon icon(String path) {
        ImageIcon icon = new ImageIcon(path);
        if (icon.getIconWidth() == -1) {
            System.err.println("Failed to load: " + path);
        }
        return icon;
    }

}
