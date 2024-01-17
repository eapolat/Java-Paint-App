import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {
    private JButton continueButton;
    private JTextField widthField;
    private JTextField heightField;

    public SettingsFrame() {

        continueButton = new JButton("Continue");
        widthField = new JTextField(5);
        heightField = new JTextField(5);

        this.setLayout(new FlowLayout());
        this.add(new JLabel("Width:"));
        this.add(widthField);
        this.add(new JLabel("Height:"));
        this.add(heightField);
        this.add(continueButton);


        continueButton.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
    
                try {
    
                    int width = Integer.parseInt(widthField.getText());
                    int height = Integer.parseInt(heightField.getText());
            

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(SettingsFrame.this, "Please enter valid numbers for width and height.");

                }
            }
        });

        
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getEnteredWidth() {
        try {
            return Integer.parseInt(widthField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid width.");
            return -1;
        }
    }

    public int getEnteredHeight() {
        try {
            return Integer.parseInt(heightField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid height.");
            return -1;
        }
    }
    
    public JButton getContinueButton() {

        return continueButton;
        
    }
}