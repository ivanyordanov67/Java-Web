public class Main {

    public static void main(String[] args) {

        int port = 8000;

        Server server = new Server(port);

        System.out.println();


        server.run();
    }
}