import javax.swing.*;
import java.awt.*;

public class Gfx extends JPanel{
    Gfx(int fov){
        this.fov = fov;
    }

    Wall w = new Wall();

    int fov,unitWidth,rayIncrement, verticalScalar;

    public void setVerticalScalar(int verticalScalar) {
        this.verticalScalar = verticalScalar;
    }

    public void setRayIncrement(int rayIncrement) {
        this.rayIncrement = rayIncrement;
    }

    public void setUnitWidth(int unitWidth) {
        this.unitWidth = unitWidth;
    }

    public Wall getW() {
        return w;
    }

    int x,y,n=0;
    int startA,endA, col;

    public void setEndA() {
        endA = startA+(fov/2);
    }

    int height;
    int width;

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    int targetX;
    int targetY;
    public int getTargetX() {
        return targetX;
    }
    public int getTargetY() {
        return targetY;
    }

    int x1,y1,x2,y2,dist,distA;
    public static int toggle;

    public static void setToggle(int t) {
        toggle = t;
    }

    @Override
    protected void paintComponent(Graphics g) {

        //2D Drawing
        if(toggle==0) {
            for (int i = 0; i < w.mapUnit; i++) {
                for (int j = 0; j < w.mapUnit; j++) {
                    if (w.map[i][j] == 1) {
                        g.setColor(Color.BLUE);
                        g.fillRect((j * width / w.mapUnit), (i * width / w.mapUnit), 39, 39);
                    } else {
                        g.setColor(Color.BLACK);
                        g.fillRect((j * width / w.mapUnit), (i * width / w.mapUnit), 39, 39);
                    }
                }

            }

            //setting color and drawing character
            g.setColor(Color.lightGray);
            g.drawOval(x - 10, y - 10, 20, 20);

            //drawing rays
            for (int i = startA - fov / 2; i < endA; i += rayIncrement) {
                x1 = x;
                y1 = y;
                x2 = x + (int) (1 * Math.sin(Math.toRadians(i)));
                y2 = y + (int) (1 * Math.cos(Math.toRadians(i)));
                if (x1 < width && x1 > (0) && y1 < width && y1 > (0) && x2 < width && x2 > (0) && y2 < width && y2 > (0)) {
                    n = 0;
                    while ((w.map[(int) (y2 / 40)][(int) (x2 / 40)] != 1)) {
                        x2 = x1 + (int) (n * Math.sin(Math.toRadians(i)));
                        y2 = y1 + (int) (n * Math.cos(Math.toRadians(i)));
                        if (x2 >= width || x2 <= (0) || y2 >= width || y2 <= (0)) break;
                        n += 1;
                    }
                    g.drawLine(x1, y1, x2, y2);

                }
            }
            //drawing the direction vector
            g.setColor(Color.RED);
            g.drawLine(x, y, x + (int) (50 * Math.sin(Math.toRadians(startA))), y + (int) (50 * Math.cos(Math.toRadians(startA))));
            targetX = x + (int) (50 * Math.sin(Math.toRadians(startA)));
            targetY = y + (int) (50 * Math.cos(Math.toRadians(startA)));

        }
        //3D drawing
        if(toggle==1) {
            g.setColor(new Color(173, 216, 230));
            g.fillRect(0,0,width,width/2);
            g.setColor(new Color(52, 66, 51));
            g.fillRect(0,width/2,width,width/2);
            for (int i = endA,inc=0; i >startA - fov / 2; i -= rayIncrement,inc++) {
                x1 = x;
                y1 = y;
                x2 = x + (int) (1 * Math.sin(Math.toRadians(i)));
                y2 = y + (int) (1 * Math.cos(Math.toRadians(i)));

                if (x1 < width && x1 > (0) && y1 < width && y1 > (0) && x2 < width && x2 > (0) && y2 < width && y2 > (0)) {
                    n = 0;
                    while ((w.map[(int) (y2 / unitWidth)][(int) (x2 / unitWidth)] != 1)) {
                        x2 = x1 + (int) (n * Math.sin(Math.toRadians(i)));
                        y2 = y1 + (int) (n * Math.cos(Math.toRadians(i)));
                        if (x2 >= width || x2 <= (0) || y2 >= width || y2 <= (0)) break;
                        n += 1;
                    }
                    dist = (int) Math.sqrt(((Math.abs(x2)-Math.abs(x1))*(Math.abs(x2)-Math.abs(x1)))+((Math.abs(y2)-Math.abs(y1))*(Math.abs(y2)-Math.abs(y1))));
                    distA= ((int)(dist*Math.cos(Math.toRadians(startA-i))));
                    //ADD A COLOR GRADIENT LATER
                    col = Math.abs(200-(dist/100)*40);
                    g.setColor(new Color(col, col, col));
                    // raw Euclidean distance
//                    g.fillRect(inc*8,(width/2)-((int)(((double)905/dist)* verticalScalar)/2),8,(int)(((double)905/dist)* verticalScalar));

                    //adjusted distance
                    g.fillRect(inc*8,(width/2)-((int)(((double)905/distA)* verticalScalar)/2),8,(int)(((double)905/distA)* verticalScalar));

                }

            }
            g.setColor(Color.RED);
            g.drawOval(width/2-10,width/2-10,20,20);
            g.drawLine((width/2),(width/2)-10,(width/2),(width/2)+10);
            g.drawLine((width/2)-10,(width/2),(width/2)+10,(width/2));


        }






        //backups
//        for (int i = startA-fov/2; i < endA; i+=5){
//            int x1 = x+10;
//            int y1 = y+10;
//            int x2 = x+(int)(100*Math.sin(Math.toRadians(i)));
//            int y2= y+(int)(100*Math.cos(Math.toRadians(i)));
//            g.drawLine(x1,y1, x2, y2);
//        }

        //backups2
//        for (int i = startA-fov/2; i < endA; i++){
//            int x1 = x;
//            int y1 = y;
//            int x2 = x+(int)(100*Math.sin(Math.toRadians(i)));
//            int y2 = y+(int)(100*Math.cos(Math.toRadians(i)));
//            if(x1<width&&x1>(0)&&y1<width&&y1>(0)){
//                for (int j = 0; j < w.mapUnit; j++) {
//                    for (int k = 0; k < w.mapUnit ; k++) {
//                        if(x2>=(unitWidth*(k+1))-unitWidth&&x2<=(unitWidth*(k+1))
//                                &&y2>=(unitWidth*(j+1))-unitWidth&&y2<=(unitWidth*(j+1))
//                                &&w.map[j][k]==0){
//                            g.drawLine(x1,y1, x2, y2);
//                        }
//                    }
//                }
//
//            }
//        }


        //Backups 3
//        for (int i = startA-fov/2; i < endA; i+=10){
//            int x1 = x;
//            int y1 = y;
//            int x2 = x+(int)(1*Math.sin(Math.toRadians(i)));
//            int y2 = y+(int)(1*Math.cos(Math.toRadians(i)));
//            if(x1<width&&x1>(0)&&y1<width&&y1>(0)&&x2<width&&x2>(0)&&y2<width&&y2>(0)){
//                for (int j = 0; j < w.mapUnit; j++) {
//                    for (int k = 0; k < w.mapUnit ; k++) {
//                        int n=0;
//                        while((w.map[(int) (y2 / 40)][(int) (x2 / 40)] != 1)){
//                            x2 = x1+ (int)(n*Math.sin(Math.toRadians(i)));
//                            y2 = y1+ (int)(n*Math.cos(Math.toRadians(i)));
//                            n++;
//                        }
//                        g.drawLine(x1,y1, x2, y2);
//
//                    }
//                }
//
//            }
//        }




    }
}


//     2D drawing backup


//    @Override
//    protected void paintComponent(Graphics g) {
//
//        for (int i = 0; i < w.mapUnit; i++) {
//            for (int j = 0; j < w.mapUnit; j++) {
//                if (w.map[i][j] == 1) {
//                    g.setColor(Color.BLUE);
//                    g.fillRect((j * width / w.mapUnit), (i * width / w.mapUnit), 39, 39);
//                } else {
//                    g.setColor(Color.BLACK);
//                    g.fillRect((j * width / w.mapUnit), (i * width / w.mapUnit), 39, 39);
//                }
//            }
//
//        }
//
//        //setting color and drawing character
//        g.setColor(Color.lightGray);
//        g.drawOval(x - 10, y - 10, 20, 20);
//
//        //drawing rays
//        for (int i = startA - fov / 2; i < endA; i += 5) {
//            int x1 = x;
//            int y1 = y;
//            int x2 = x + (int) (1 * Math.sin(Math.toRadians(i)));
//            int y2 = y + (int) (1 * Math.cos(Math.toRadians(i)));
//            if (x1 < width && x1 > (0) && y1 < width && y1 > (0) && x2 < width && x2 > (0) && y2 < width && y2 > (0)) {
//                for (int j = 0; j < w.mapUnit; j++) {
//                    for (int k = 0; k < w.mapUnit; k++) {
//                        n = 0;
//                        while ((w.map[(int) (y2 / 40)][(int) (x2 / 40)] != 1)) {
//                            x2 = x1 + (int) (n * Math.sin(Math.toRadians(i)));
//                            y2 = y1 + (int) (n * Math.cos(Math.toRadians(i)));
//                            n += 1;
//                        }
//                        g.drawLine(x1, y1, x2, y2);
//
//                    }
//                }
//
//            }
//        }
//        //drawing the direction vector
//        g.setColor(Color.RED);
//        g.drawLine(x, y, x + (int) (100 * Math.sin(Math.toRadians(startA))), y + (int) (100 * Math.cos(Math.toRadians(startA))));
//        targetX = x + (int) (100 * Math.sin(Math.toRadians(startA)));
//        targetY = y + (int) (100 * Math.cos(Math.toRadians(startA)));
//    }
//}


//3D Backup

//if(toggle==1) {
//        g.setColor(Color.BLUE);
//        for (int i = startA - fov / 2,inc=0; i < endA; i += rayIncrement,inc++) {
//        x1 = x;
//        y1 = y;
//        x2 = x + (int) (1 * Math.sin(Math.toRadians(i)));
//        y2 = y + (int) (1 * Math.cos(Math.toRadians(i)));
//
//        if (x1 < width && x1 > (0) && y1 < width && y1 > (0) && x2 < width && x2 > (0) && y2 < width && y2 > (0)) {
//        n = 0;
//        while ((w.map[(int) (y2 / unitWidth)][(int) (x2 / unitWidth)] != 1)) {
//        x2 = x1 + (int) (n * Math.sin(Math.toRadians(i)));
//        y2 = y1 + (int) (n * Math.cos(Math.toRadians(i)));
//        if (x2 >= width || x2 <= (0) || y2 >= width || y2 <= (0)) break;
//        n += 1;
//        }
//        dist = (int) Math.sqrt(((Math.abs(x2)-Math.abs(x1))*(Math.abs(x2)-Math.abs(x1)))+((Math.abs(y2)-Math.abs(y1))*(Math.abs(y2)-Math.abs(y1))));
//        g.fillRect(inc*8,(width/2+35)-(dist/2),8,(int)(((double)dist/905)*width));
//                    System.out.println(dist);
//
//        }
//
//        }
//
//
//        }