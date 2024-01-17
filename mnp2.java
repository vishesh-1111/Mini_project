import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class mnp2 extends JFrame {
    private Map<String, Double> menuItems;
    private JTextArea billTextArea;
    private double totalAmount;

    public mnp2() {
     
        menuItems = new HashMap<>();
        menuItems.put("Burger", 150.99);
        menuItems.put("Pizza", 199.99);
        menuItems.put("Salad", 99.99);
        menuItems.put("Drink", 19.99);
        menuItems.put("tea",15.99);
        menuItems.put("coffee",15.99);
        menuItems.put("milkshake",40.99);
        menuItems.put("Pav bhajii",120.99);

     
        JLabel menuLabel = new JLabel("Menu:");
        JList<String> menuList = new JList<>(menuItems.keySet().toArray(new String[0]));
        JButton addItemButton = new JButton("Add Item");
        billTextArea = new JTextArea("Bill:\n");
        JButton calculateTotalButton = new JButton("Calculate Total");

       
        setLayout(new BorderLayout());

       
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.add(menuLabel, BorderLayout.NORTH);
        menuPanel.add(new JScrollPane(menuList), BorderLayout.CENTER);
        menuPanel.add(addItemButton, BorderLayout.SOUTH);

        add(menuPanel, BorderLayout.WEST);
        add(new JScrollPane(billTextArea), BorderLayout.CENTER);
        add(calculateTotalButton, BorderLayout.SOUTH);

        
        addItemButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                String selectedItem = menuList.getSelectedValue();
                if (selectedItem != null) {
                    double itemPrice = menuItems.get(selectedItem);
                    totalAmount += itemPrice;
                    billTextArea.append(selectedItem + " - " + itemPrice + "\n");
                }
            }
        });

        calculateTotalButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                billTextArea.append("Total Amount: " + totalAmount + "\n");
            }
        });

      
        setTitle("Restaurant Billing System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                new mnp2();
            }
        });
    }
}