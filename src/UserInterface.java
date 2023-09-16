import javax.swing.*;

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
        JButton b1= new JButton("a button");
        ui.add(b1);
        b1.setBounds(100,100,100,30);


    }


}
