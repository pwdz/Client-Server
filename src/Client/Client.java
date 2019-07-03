package Client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

import Info.Info;
import com.sun.xml.internal.ws.server.ServerRtException;

public class Client {
    private Socket socket;
    private static final int PORT = 1500;
    private static final String IP = "127.0.0.1";
    private InputStream input;
    private OutputStream output;
    private Info infoReceived;
    private String username;

    public Client(String username) {
        this.username = username;
        try {
            socket = new Socket(IP, PORT);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            clientReceiver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientReceiver() {
        try {
            ObjectInputStream reader = new ObjectInputStream(input);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            infoReceived = (Info) reader.readObject();
                            switch (infoReceived.getType()) {
                                case 0://
                                    break;
                                case 1://request for file
                                    Info info = new Info(username, infoReceived.getSrcName(), 2);
                                    clientSender(info);
                                    break;
                                case 2://alarm of a file being delivered to here
                                    FileOutputStream fileOutput = new FileOutputStream("./Files/"+"a.mp3");
                                    fileOutput.write(infoReceived.getFileByteCode());
                                    break;
                                case 3:
                                    break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clientSender(Info info) {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(output);
            switch (info.getType()) {
                case 1://
                    break;
                case 2://info must be sent
                    writer.writeObject(info);
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}