import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class DisplayGraphics extends JPanel {

    private class Rectangle
    {
        public int x;
        public int y;
        public int w;
        public int h;

        public Rectangle(int x, int y, int w, int h)
        {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
    }

    private class Line
    {
        public int x1;
        public int y1;
        public int x2;
        public int y2;

        public Line(int x1, int y1, int x2, int y2)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private ArrayList<Line> lines = new ArrayList<Line>();
    private boolean sketchOnly = false;

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        setBackground(Color.WHITE);
        //setOpaque(false);
        setForeground(Color.RED);
        if(!sketchOnly)
        {
            for (Rectangle r : rectangles) {
                g.fillRect(r.x, r.y, r.w, r.h);
            }
        }

        //setForeground(Color.BLUE);
        for (Line l: lines)
        {
            g.drawLine(l.x1, l.y1, l.x2, l.y2);
        }

        // Example drawing functions
        //g.drawString("Hello", 40, 40);
        //g.drawOval(30, 130, 50, 60);
        //g.fillOval(130, 130, 50, 60);
        //g.drawArc(30, 200, 40, 50, 90, 60);
        //g.fillArc(30, 130, 40, 50, 180, 40);

    }

    public void AddRectangle(int x, int y, int w, int h)
    {
        rectangles.add(new Rectangle(x, y, w, h));
    }

    public void AddLine(int x1, int y1, int x2, int y2)
    {
        lines.add(new Line(x1, y1, x2, y2));
    }

    public void ClearLines() {
        lines.clear();
    }

    public void SaveImage(){
        BufferedImage imagebuf=null;
        try {
            imagebuf = new Robot().createScreenCapture(bounds());
        } catch (AWTException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        sketchOnly = true;
        paint(graphics2D);
        sketchOnly = false;
        try {
            ImageIO.write(imagebuf,"jpeg", new File("save1.jpeg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
    }
}