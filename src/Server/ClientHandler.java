package Network.Server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private InputStream input;
    private OutputStream output;
    private Info info;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public ClientHandler(Socket socket) {
        try {
            System.out.println("created");
            input = socket.getInputStream();
            output = socket.getOutputStream();
            writer = new ObjectOutputStream(output);
            reader = new ObjectInputStream(input);
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
                    case 0://a client is added
                        System.out.println("Source--> " + info.getSrcName());
                        info.usersObjectOutputStream.put(info.getSrcName(), writer);
                        info.usersObjectInputStream.put(info.getSrcName(), reader);
                        break;
                    case 1://request for a file
                        System.out.println("check");
                        if (searchForDesOutputStream(info.getDesName()) != null) {
                            System.out.println("check2");
                            System.out.println(info.getDesName());
                            writer = searchForDesOutputStream(info.getDesName());
                            writer.writeObject(info);
                        }
                        break;
                    case 2://Info must be sent
                        if (searchForDesOutputStream(info.getDesName()) != null) {
                            writer = searchForDesOutputStream(info.getDesName());
                            writer.writeObject(info);
                        }
                        break;
                    case 3:
                        break;
                }
                info.setType(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private ObjectOutputStream searchForDesOutputStream(String username) {
        for (String usernameHashmap : info.usersObjectOutputStream.keySet()) {
            if (username.equals(usernameHashmap))
                return info.usersObjectOutputStream.get(usernameHashmap);
        }
        return null;
    }
}
