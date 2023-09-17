public class Main {
    public static void main(String[] args) {


        Wall.mapToUse="test.ppm";
        new UserInterface();
        new Core().loop();

    }
}
