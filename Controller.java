import javax.swing.SwingUtilities;
import java.awt.*;


public class Controller {

    private SettingsFrame settingsFrame;
    private ToolsFrame toolsFrame;
    private PaintFrame paintFrame;
    private Controller controller;
    private static Controller instance;
    private int mouseX, mouseY;
    private Color targetColor;



    public Controller( PaintFrame paintFrame, SettingsFrame settingsFrame) {

        toolsFrame = new ToolsFrame(this, paintFrame);


        this.settingsFrame = settingsFrame;
        this.paintFrame = paintFrame;

        settingsFrame.setVisible(true);



        settingsFrame.getContinueButton().addActionListener(e -> {
            int width = settingsFrame.getEnteredWidth();
            int height = settingsFrame.getEnteredHeight();
            
            if (width > 0 && height > 0) {
                openDrawingFrames(width, height);
            }
        });
    }

    public void setTargetColor(Color color) {
        this.targetColor = color;
    }


    private void openDrawingFrames(int width, int height) {
        paintFrame.initializeCanvas(width, height);
        paintFrame.setSize(width, height);
        toolsFrame.setVisible(true);
        paintFrame.setVisible(true);
        settingsFrame.setVisible(false);
    }

    public void changePenSize(int size) {
        paintFrame.setPenSize(size);
    }

    public static Controller getInstance(PaintFrame paintFrame, SettingsFrame settingsFrame) {
        if (instance == null) {
            instance = new Controller(paintFrame, settingsFrame);
        }
        return instance;
    }

    public void changePenColor(Color chosenColor) {
        if (paintFrame != null) {
            paintFrame.setPenColor(chosenColor);
        }
    }


    public static void main(String[] args) {

        PaintFrame paintFrame = new PaintFrame();
        SettingsFrame settingsFrame = new SettingsFrame();
        Controller controller = Controller.getInstance(paintFrame, settingsFrame);
        System.out.println("Controller in MainApplication: " + controller);
        ToolsFrame toolsFrame = new ToolsFrame(controller, paintFrame);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller(paintFrame, settingsFrame);
            }
        }); 
    }

}
