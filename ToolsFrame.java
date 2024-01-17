import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ToolsFrame extends JFrame {
    private JButton penButton;
    private PaintFrame paintFrame;
    private Controller controller;
    private JButton penSizeButton;
    private JButton clearButton;
    private JButton colorButton;
    private JButton laserButton;
    private JButton toleranceButton;



    public ToolsFrame(Controller controller, PaintFrame paintFrame) {

        this.setLayout(new FlowLayout());

        penSizeButton = new JButton("Pen Size");
        this.add(penSizeButton);

        penSizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter pen size:");
                try {
                    int size = Integer.parseInt(input);
                    if (size > 0) {
                        controller.changePenSize(size);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
                }
            }
        });

        clearButton = new JButton("Clear");
        this.add(clearButton);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintFrame.clearCanvas();
            }
        });

        penButton = new JButton("Pen");
        this.add(penButton);

        penButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        colorButton = new JButton("Color");
        this.add(colorButton);

        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color chosenColor = JColorChooser.showDialog(null, "Pick a Color!", Color.WHITE);
                if (chosenColor != null) {

                    controller.changePenColor(chosenColor);
                    controller.setTargetColor(chosenColor);
                }
            }
        });

        laserButton = new JButton("Laser");

        this.add(laserButton);

        laserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintFrame.setActiveTool(PaintFrame.Tool.LASER);
                paintFrame.activateLaserTool(PaintFrame.x, PaintFrame.y);

            }
        });

        toleranceButton = new JButton("Tolerance");

        this.add(toleranceButton);

        toleranceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
