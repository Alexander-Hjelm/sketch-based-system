import com.sun.org.apache.regexp.internal.RE;

import java.awt.*;
import java.util.ArrayList;

public class DisplayGraphics extends Canvas {

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

    private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

    public void paint(Graphics g)
    {
        setBackground(Color.WHITE);

        setForeground(Color.RED);

        for (Rectangle r: rectangles)
        {
            g.fillRect(r.x, r.y, r.w, r.h);
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
}