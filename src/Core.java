import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Core extends JFrame {


    int height = 675;
    int width = 640;
    int unitWidth = 40;
    int fov = 80;
    double rayIncrement = 0.25; //change in proportion to verticalWidth
    double frameRate = 60.0;
    double timePerFrame = 1000000000.0/frameRate;
    double lastFrame;
    int verticalScalar = 20,verticalWidth=2; //change in proportion to rayIncrement (verticamWidth^-1 -> K * rayIncrement, 8 -> 1)
    double playerX=45;
    double playerY=45;
    int prevx=0, currentx;
    int speed = 5;

    public int getPlayerX() {
        return (int) playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return (int) playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

    int i=0;

    Core(){

        //init
        setBackground(Color.black);
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Raycaster3D");


        Gfx g = new Gfx(fov);
        add(g);
        g.setRayIncrement(rayIncrement);
        g.setVerticalScalar(verticalScalar);
        g.setVerticalWidth(verticalWidth);
        g.setX(getPlayerX());
        g.setY(getPlayerY());
        g.setUnitWidth(unitWidth);
        g.getW().readMap();
        g.getW().intMap();
        g.setEndA();
        g.setHeight(height);
        g.setWidth(width);


        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

//                setPlayerX(e.getX());
//                g.setX(getPlayerX());
//                setPlayerY(e.getY());
//                g.setY(getPlayerY()-40);

                currentx=e.getX();
                if(prevx>currentx){
                    g.startA = (g.startA + 1) % 360;
                    g.endA = g.startA + (fov / 2);
                }
                if(prevx<currentx){
                    g.startA = (360 + g.startA - 1) % 360;
                    g.endA = g.startA + (fov / 2);
                }
                prevx = currentx;


            }

            @Override
            public void mouseMoved(MouseEvent e) {


            }
        });


        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyChar()) {
                    case 'w' -> {
                        playerY += speed * (Math.cos(Math.toRadians(g.startA)));
                        g.setY(getPlayerY());
                        playerX += speed * (Math.sin(Math.toRadians(g.startA)));
                        g.setX(getPlayerX());
                    }
                    case 's' -> {
                        playerY -= speed * (Math.cos(Math.toRadians(g.startA)));
                        g.setY(getPlayerY());
                        playerX -= speed * (Math.sin(Math.toRadians(g.startA)));
                        g.setX(getPlayerX());
                    }
                    case 'a' -> {
                        g.startA = (g.startA + speed) % 360;
                        g.endA = g.startA + (fov / 2);

                    }
                    case 'd' -> {
                        g.startA = (360 + g.startA - speed) % 360;
                        g.endA = g.startA + (fov / 2);




                    }
                    case 'e' -> {
                        if (g.getTargetY() > 0 && g.getTargetY() < width && g.getTargetX() > 0 && g.getTargetX() < width) {
                            g.getW().map[(int) (g.getTargetY() / 40)][(int) (g.getTargetX() / 40)] = 0;
                        }
                    }
                    case 'q' -> {
                        if (g.getTargetY() > 0 && g.getTargetY() < width && g.getTargetX() > 0 && g.getTargetX() < width) {
                            g.getW().map[(int) (g.getTargetY() / 40)][(int) (g.getTargetX() / 40)] = 1;
                        }
                    }
                    case 'r' ->{
                        playerY=100;
                        playerX=100;
                        g.setX(getPlayerX());
                        g.setY(getPlayerY());
                    }
                    default -> {
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }



    public void loop(){
        while(true){
            if(System.nanoTime()-lastFrame>=timePerFrame){
                lastFrame = System.nanoTime();
                repaint();
//                i++;
            }
//            if(i>=frameRate){
//                System.out.println("FPS: " + i);
//                i = 0;
//            }
        }
    }

}
