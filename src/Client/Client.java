package Client;

import javax.sound.sampled.Line;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import Info.Info;

public class Client {
    private Socket socket;
    private static final int PORT = 1500;
    private static final String IP = "127.0.0.1";
    private InputStream input;
    private OutputStream output;
    private Info info;
    public Client() {
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
                            info = (Info) reader.readObject();
                            switch (info.getType())
                            {
                                case 0:
                                    break;
                                case 1://request for file
                                    clientSender(info.setType());
                                    break;
                                case 2://alarm of a file being delivered to here
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
        switch (info.getType())
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

}