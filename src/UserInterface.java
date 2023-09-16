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
        b1.setBounds(100,100,200,30);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Gfx.toggle==0)Gfx.setToggle(1);
                else Gfx.setToggle(0);
            }
        });


    }


}
