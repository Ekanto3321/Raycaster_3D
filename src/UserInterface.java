import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {




    UserInterface(){

        //initializing UI Frame
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(1350,350);
        setTitle("Controls");
        JPanel ui = new JPanel();
        add(ui);
        ui.setLayout(null);

        //Components
        JButton b1= new JButton("toggle 2D/3D");
        ui.add(b1);
        b1.setBounds(20,10,150,30);

        JTextArea ta = new JTextArea();
        ui.add(ta);
        ta.setEditable(false);
        ta.setBounds(10,80,380,240);
        ta.setText("Basic Controls: \n" +
                "Use W to move forward\n" +
                "Use S to move backwards\n" +
                "Click and Drag to rotate or use A and D keys\n" +
                "Click the Toggle 2D/3D to switch between modes\n" +
                "Click R to reset player position in case you go out of bounds\n" +
                "\nMap editing mode: \n" +
                "In 2D view, allign the red line to the tile you want to edit\n" +
                "Click Q to add boundary\n" +
                "Click E to remove boundary\n" +
                "\nTo import custom map, create a 16x16 .ppm image file\n" +
                "Use pure black and white color values and edit \nthe 'mapToUse' variable in Main");


        JLabel l1= new JLabel("Instructions: ");
        ui.add(l1);
        l1.setBounds(125,50,150,30);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Gfx.toggle==0)Gfx.setToggle(1);
                else Gfx.setToggle(0);
            }
        });




    }


}
