import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class javapro extends JFrame {

    public javapro() {
        setTitle("Restaurant Menu Display");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menuPanel = createMenuPanel();
        add(menuPanel);

        setLocationRelativeTo(null);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(3, 3));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu"));

        String[] itemNames = {"burger", "tea", "Coffee", "Pizza", "Salad", "Drink", "Milkshake", "Bun's", "Maggi"};
        String[] imagePaths = {"burger.jpg", "tea.jpg", "coffee.jpg", "pizza.jpg", "salad.jpg", "drink.jpg", "milkshake.jpg", "bun.jpg", "maggie.jpg"};

        for (int i = 0; i < itemNames.length; i++) {
            String itemName = itemNames[i];
            String imagePath = imagePaths[i];

            try {
                // Load the image
                ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
                Image originalImage = originalIcon.getImage();

                // Resize the image
                int newWidth = 100; // Set the desired width
                int newHeight = 100; // Set the desired height
                Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

                // Create a button with the resized image and text
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JButton button = new JButton(itemName, resizedIcon);
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);

                // Add ActionListener
                button.addActionListener(new MenuItemClickListener(itemName));

                // Add the button to the menuPanel
                menuPanel.add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return menuPanel;
    }

    private class MenuItemClickListener implements ActionListener {
        private String itemName;

        public MenuItemClickListener(String itemName) {
            this.itemName = itemName;
        }

        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(javapro.this, "You selected: " + itemName, "Menu Item Selected", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void captureAndSaveScreenshot() {
        try {
            // Create a volatile image
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage screenshot = gc.createCompatibleImage(getWidth(), getHeight(), Transparency.OPAQUE);

            // Paint the JFrame onto the image
            Graphics g = screenshot.getGraphics();
            paint(g);
            g.dispose();

            // Save screenshot to file
            File outputfile = new File("screenshot.png");
            ImageIO.write(screenshot, "png", outputfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            javapro menuDisplay = new javapro();
            menuDisplay.setVisible(true);

            // Capture and save screenshot (optional)
            EventQueue.invokeLater(menuDisplay::captureAndSaveScreenshot);
        });
    }
}
