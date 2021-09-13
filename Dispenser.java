/**
 * Dispenser creates a graphical user interface using the ArrayStack and JPanel classes.
 *
 * @author dickson o. oloo
 * @reg SCT211-0768/2016
 * @version (a version number or a date)
 */
// Create an AWT-based application.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Dispenser extends JPanel
{
    // instance variables
    private static ArrayStack stack;
    private static JFrame frame;

    /**
     * Constructor for objects of class Dispenser
     */
    public Dispenser()
    {
        // initialise instance variables
        stack = new ArrayStack();
    }
    
    public void paintComponent(Graphics W) {

        W.setColor(Color.red);
        W.fillRect(19, 19, 100, 310);
        // If the stack is empty just draw the spring
        if (!stack.isEmpty())
        {
            int count = stack.getCount();
            // Else draw the candies in the stack using green rectangles
            for(int i=0; i<count; i++)
            {
                // Set the graphic color
                W.setColor( Color.green);
                // Draw the Rectangle shape w=99px h=30px
                W.fillRect(20, (i*30)+20, 99, 28);
                // Put text into rectangle
                String text = "candy"+(count-i);
                W.setColor(Color.black);
                W.drawString(text, 65,((i*30)+40));
            }            
        }
        // Implement the spring in the remaining space
        // The first spring starts at the height below the last candy
        int candyHeight =20+stack.getCount()*30;
        // After the first spring line draw alternating
        // diagonal lines of height 30 and horizontal lines
        // representing the spring.
        while (candyHeight < 320){
            W.setColor(Color.black);
            int height = 320- candyHeight;
            for (int i=1; i<=10; i++){
                // horizontal line  w=120px h=0
                W.drawLine(20, candyHeight,120, candyHeight);
                // height for the diagonal = 30 px
                candyHeight += height/10;
                // Diagonal line of h=30px w=120px
                W.drawLine(19, candyHeight-(height/10),120, candyHeight);
                W.drawLine(19, candyHeight,120, candyHeight);
            }
        }
    }

    // Create the window.
    public static void main(String args[]) {
        // Initialize the JFrame class
        frame = new JFrame("Candy Dispenser!");
        // Set the JFrame width and height
        frame.setSize(700, 400);
        // Create a JPanel object from a Dispenser class instance
        JPanel panel = new Dispenser();
        // Set the height and width for the JPanel
        panel.setSize(700, 400);
        // Create a label to output messages from events
        JLabel msg = new JLabel();
        // Set the label size and position
        msg.setBounds(200, 100, 300, 30);
        // Create buttons for the dispenser and the event listeners
        // The "Size" button asks for the number of items in the dispenser (Stack)
        JButton size = new JButton("Size");
        // Set the button position and size
        size.setBounds(140, 170, 100, 25);
        // The event listener below activates when the Size button is clicked
        size.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                // Print the number of items in the stack
                msg.setText("size is "+stack.getCount()+" candies.");
            }
        });
        // The Push button pushes an item to the stack
        JButton push = new JButton("Push");
        push.setBounds(140, 200, 100, 25);
        push.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                if(stack.isFull())
                {
                    // If the stack is full output a message
                    msg.setText("The dispenser is full.");  
                }else{
                    // else push an item to the stack and repaint the frame
                    stack.push();
                    frame.repaint();
                }  
            }
        });
        // The Pop button removes the last item from the stack
        JButton pop = new JButton("Pop");
        pop.setBounds(280, 200, 100, 25);
        pop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                if(stack.isEmpty())
                {
                    // IF stack is empty output a message
                    msg.setText("The dispenser is empty.");  
                }else{
                    // Else pop() from the stack object
                    stack.pop();
                    frame.repaint();
                }  
            }
        });
        // The top button prints the number of the candy at the top of the stack eg "c6"
        JButton top = new JButton("Top");
        top.setBounds(280, 250, 100, 25);
        top.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                if(stack.isEmpty())
                {
                    msg.setText("The dispenser is empty.");  
                }else{
                    msg.setText("The top candy is candy"+stack.getCount());
                }  
            }
        });
        // The IsEmpty Button checks whether the stack is empty or not
        JButton isEmpty = new JButton("Is Empty");
        isEmpty.setBounds(280, 300, 100, 25);
        isEmpty.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                if(stack.isEmpty())
                {
                    msg.setText("TRUE");  
                }else{
                    msg.setText("FALSE");  
                }  
            }
        });
        // Add the msg label
        frame.add(msg);
        //add action buttons to the frame
        frame.add(size);
        frame.add(push);
        frame.add(pop);
        frame.add(top);
        frame.add(isEmpty);
        // Added the JPanel isntance
        frame.add(panel);
        // Display the JFrame
        frame.setVisible(true);
        
    }
      
}
