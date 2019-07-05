package Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server implements Runnable{
    private ServerSocket serverSocket;
    private static final int PORT = 1500;
    private HashMap<String, OutputStream> usersOutputStreams;
    public Server()
    {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int count =0;
        while (true)
        {
            try {
                System.out.println("Waiting for Client to connect");
                Socket socket = serverSocket.accept();
                count++;
                System.out.println("Client "+ count+" connected");
                System.out.println("Welcome "+socket.getOutputStream());
                new Thread(new ClientHandler(socket)).start();
                System.out.println("________________________________________________________");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
