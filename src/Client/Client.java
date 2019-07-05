package Client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

import Server.Info;

public class Client {
    private Socket socket;
    private static final int PORT = 1500;
    private static final String IP = "127.0.0.1";
    private InputStream input;
    private OutputStream output;
    private Info infoReceived;
    private String username;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    public Client(String username) {
        this.username = username;
        try {
            socket = new Socket(IP, PORT);
            input = socket.getInputStream();
            output = socket.getOutputStream();
            System.out.println("!");
            writer = new ObjectOutputStream(output);
            writer.flush();
            reader = new ObjectInputStream(input);
            System.out.println(":|");
            clientSender(new Info(username, "", 0));
            System.out.println("here");
            new Thread(() -> clientReceiver()).start();
            System.out.println("there!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientReceiver() {
        while (true) {
            try {
                infoReceived = (Info) reader.readObject();
                switch (infoReceived.getType()) {
                    case 0://
                        break;
                    case 1://request for file
                        System.out.println("request!");
                        Info info = new Info(username, infoReceived.getSrcName(), 2);
                        clientSender(info);
                        break;
                    case 2://alarm of a file being delivered to here
                        System.out.println("received!");
                        FileOutputStream fileOutput = new FileOutputStream("./Files/" + "a.mp3");
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

    public void clientSender(Info info) {
        try {
            switch (info.getType()) {
                case 0://telling to server that u've been added :)
                    System.out.println("123123123123");
                    writer.writeObject(new Info(username, "", 0));
                    System.out.println("------------------");
                    break;
                case 1://sending request
                    writer.writeObject(new Info(username,"mmd",1));
                    writer.flush();
                    System.out.println("tohooooooooo");
                    break;
                case 2://info must be sent
                    info.setFileByteCode(Files.readAllBytes(new File("C:/Users/acer/Music/01 Honey.mp3").toPath()));
                    writer.writeObject(info);
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setMessage(int type, String desName) {
        clientSender(new Info(username, desName, type));
        System.out.println("in func");
    }

}