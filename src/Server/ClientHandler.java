package Server;

import Info.Info;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private InputStream input;
    private OutputStream ouput;
    private Info info;
    public ClientHandler(Socket socket) {
        try {
            input = socket.getInputStream();
            ouput = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            ObjectInputStream reader = new ObjectInputStream(input);
            ObjectOutputStream writer = new ObjectOutputStream(ouput);
            while (true) {
                info = (Info) reader.readObject();
                switch (info.getType())
                {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
