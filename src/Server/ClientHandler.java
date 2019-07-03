package Server;

import Info.Info;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private InputStream input;
    private OutputStream ouput;
    private Info info;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

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
            while (true) {
                info = (Info) reader.readObject();
                switch (info.getType()) {
                    case 0:
                        break;
                    case 1://
                        break;
                    case 2://Info must be sent
                        searchForDesOutputStream(info.getDesName());
                         
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
    private OutputStream searchForDesOutputStream(String username)
    {

    }
}
