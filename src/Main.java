import javax.swing.*;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main {

    private static final int WINDOW_SIZE_X = 200;
    private static final int WINDOW_SIZE_Y = 200;

    private static int[][] sketchImage = new int[WINDOW_SIZE_X][WINDOW_SIZE_Y];

    private static int mouseXPrev = -1;
    private static int mouseYPrev = -1;

    public static void main(String[] args) {

        // Swing example

        JFrame f=new JFrame();//creating instance of JFrame

        DisplayGraphics dg = new DisplayGraphics();
        f.add(dg);
        dg.AddRectangle(0, 0, 100, 100);
        dg.AddRectangle(100, 100, 50, 50);


        dg.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dummy
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseXPrev = e.getX();
                mouseYPrev = e.getY();
                System.out.println("Mouse pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released");

                // Clear image
                sketchImage = new int[WINDOW_SIZE_X][WINDOW_SIZE_Y];
                dg.ClearLines();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Dummy
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Dummy
            }
        });

        dg.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // Update sketch image
                if(x < WINDOW_SIZE_X && y < WINDOW_SIZE_Y) {
                    sketchImage[x][y] = 1;
                }
                dg.AddLine(x, y, mouseXPrev, mouseYPrev);

                dg.repaint();

                mouseXPrev = x;
                mouseYPrev = y;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                // Dummy
            }
        });

        // Add a button, example
        //JButton b=new JButton("click");//creating instance of JButton
        //b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        //f.add(b);//adding button in JFrame

        f.setSize(400,400);//400 width and 500 height
        //f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        // NN Example

        // create new perceptron network
        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
        // create training set
        DataSet trainingSet = new DataSet(2, 1);

        // add training data to training set (logical OR function)
        trainingSet.add(new DataSetRow(new double[]{0, 0}, new double[]{0}));
        trainingSet.add(new DataSetRow(new double[]{0, 1}, new double[]{1}));
        trainingSet.add(new DataSetRow(new double[]{1, 0}, new double[]{1}));
        trainingSet.add(new DataSetRow(new double[]{1, 1}, new double[]{1}));
        // learn the training set
        neuralNetwork.learn(trainingSet);
        // save the trained network into file
        neuralNetwork.save("or_perceptron.nnet");

        System.out.println("Initialization done");

    }
}
