public class Main {
    public static void main(String[] args) {


        Wall.mapToUse="map.ppm";
        new UserInterface();
        new Core().loop();

    }
}
