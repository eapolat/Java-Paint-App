import javax.swing.*;
import javax.tools.Tool;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class PaintFrame extends JFrame {
    private BufferedImage canvas;
    private Graphics2D g2d;
    private int penSize=5;
    private Color penColor = Color.BLACK;
    private boolean[][] visited;
    private Tool activeTool;
    public static int mouseX, mouseY;
    public static int x, y;


    public enum Tool {
        PEN, ERASER, LASER,
        
    }



    public PaintFrame() {

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        this.g2d = canvas.createGraphics();

/*         this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isLaserToolActive()) {
                    int x = e.getX();
                    int y = e.getY();
                    if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
                        Color clickedColor = new Color(canvas.getRGB(x, y));
                        activateLaserTool(x, y, clickedColor);
                    }
                }
            }
        }); */

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  
                x = e.getX();  
                y = e.getY();
                //activateLaserTool(x, y);
                
            }
        
        });

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


/*     public void useLaserTool() {

            this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isLaserToolActive()) {
                    int x = e.getX();
                    int y = e.getY();
                    if (x >= 0 && x < canvas.getWidth() && y >= 0 && y < canvas.getHeight()) {
                        Color clickedColor = new Color(canvas.getRGB(x, y));
                        activateLaserTool(x, y, clickedColor);
                    }
                }
            }
        });

    } */

    public void setMousePosition(int x, int y) {
        this.mouseX = x;
        this.mouseY = y;
    }

/*     public void activateLaserTool(int x, int y, Color clickedColor) {
        if (paintFrame != null) {
            paintFrame.activateLaserTool(x, y, clickedColor);
        }
    } */


    public void setActiveTool(Tool tool) {
        this.activeTool = tool;
    }

    public boolean isLaserToolActive() {
        return activeTool == Tool.LASER;
    }

    public void initializeCanvas(int width, int height) {

        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = canvas.createGraphics();
        g2d.setPaint(Color.BLACK);

        setupDrawingListeners();

    }

    private void setupDrawingListeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                draw(e.getX(), e.getY());
            }
        });
    }

    private void draw(int x, int y) {
        if (g2d != null) {
            g2d.fillOval(x - penSize / 2, y - penSize / 2, penSize, penSize);
            repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (canvas != null) {
            g.drawImage(canvas, 0, 0, this);
        }
    }

    public void setPenSize(int size) {
        this.penSize = size;
        if (g2d != null) {
            g2d.setStroke(new BasicStroke(penSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
    }

    public void clearCanvas() {
        if (canvas != null) {

            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g2d.setPaint(Color.BLACK); 
            repaint();
        }
    }

    public void setPenColor(Color newColor) {

        this.penColor = newColor;
        g2d.setColor(newColor);
    }

    public void activateLaserTool(int x, int y) {
        Color startColor = new Color(canvas.getRGB(x, y));
        visited = new boolean[canvas.getWidth()][canvas.getHeight()];
        paintLaser(x, y, startColor);

        setActiveTool(PaintFrame.Tool.LASER);
    }

    public void paintLaser(int x, int y, Color startColor) {
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return;
        }
        if (visited[x][y]) {
            return;
        }

        Color currentColor = new Color(canvas.getRGB(x, y));
        if (!currentColor.equals(startColor)) {
            return;
        }

        canvas.setRGB(x, y, penColor.getRGB());
        visited[x][y] = true;

        paintLaser(x, y - 1, startColor);
        paintLaser(x, y + 1, startColor); 

        g2d.drawImage(canvas, 0, 0, null);

    }

    private boolean isColorSimilar(Color c1, Color c2) {

        return c1.equals(c2);
    }

    public void setTool(String tool) {

    }
}
