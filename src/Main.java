
import javax.swing.*;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Swing example

        JFrame f=new JFrame();//creating instance of JFrame

        DisplayGraphics dg = new DisplayGraphics();
        dg.AddRectangle(0, 0, 100, 100);
        dg.AddRectangle(100, 100, 50, 50);

        f.add(dg);

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
    }
}
