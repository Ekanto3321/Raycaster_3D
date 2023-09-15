import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Wall {

    public String []img = new String[256*3];

    //Sample map
//    public int []m = {
//            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
//            1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,
//            1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,
//            1,0,0,0,1,0,0,0,0,1,1,1,1,0,0,1,
//            1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,
//            1,0,0,0,1,1,1,0,0,1,0,0,1,1,1,1,
//            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
//            1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,
//            1,0,0,0,0,1,1,1,1,1,0,1,0,0,0,1,
//            1,0,0,0,0,1,0,0,0,1,0,1,0,0,0,1,
//            1,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,
//            1,1,1,1,1,1,1,0,0,1,0,0,0,0,0,1,
//            1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,
//            1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,1,
//            1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,
//            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
//    };
    int mapUnit = 16;
    public int [][]map = new int[mapUnit][mapUnit]; //Displayed map
    public String [] intMap = new String[256]; //Intermediate data while parsing
    public int []m2 = new int[256]; //The map that is loaded


    //Initializing map with the read values
    public void intMap(){
        int k=0;

        for (int i = 0; i < mapUnit; i++) {
            for (int j = 0; j < mapUnit; j++) {
                map[i][j]=m2[k];
                k++;
            }

        }
    }


    //reading map from the file (.ppm size 16x16px only accepts pure black and white images)
    public void readMap(){

        try {
            for (int i = 0; i < 256; i++) {
                intMap[i]="";
            }
            BufferedReader br = new BufferedReader(new FileReader("map.ppm"));
            System.out.println("read successfully!");
            for (int i = 0; i < 4; i++)br.readLine();//Flushing the metadata of .ppm file (4 lines)

            for (int i = 0; (img[i] = br.readLine())!=null; i++)if(i==767)break;//loading to program (pre intermediate step)

            int k = 0;

            //translating read data to intermediate values and assigning to the loader
            for (int i = 0; i < 768; i+=3) {
                for (int j = 0; j < 3; j++) {
                    intMap[k]+=(img[i+j]);
                }
                if(intMap[k].equals("000")){
                    m2[k]=0;
                }
                else if(intMap[k].equals("255255255")) m2[k]=1;
                k++;
            }

            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
