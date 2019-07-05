import Server.Server;

public class Svr {
    public static void main(String[] args) {
        Server server=new Server();
        new Thread(server).start();
    }
}
