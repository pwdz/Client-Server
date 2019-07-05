package Server;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            reader = new ObjectInputStream(input);
            while (true) {
                info = (Info) reader.readObject();
                switch (info.getType()) {
                    case 0://a client is added
                        System.out.println("Source--> " + info.getSrcName());
                        info.usersOutputStream.put(info.getSrcName(), output);
                        info.usersInputStream.put(info.getSrcName(), input);
                        break;
                    case 1://request for a file
                        System.out.println("check");
                        if (searchForDesOutputStream(info.getDesName()) != null) {
                            System.out.println("check2");
                            writer = new ObjectOutputStream(searchForDesOutputStream(info.getDesName()));
                            writer.writeObject(info);
                        }
                        break;
                    case 2://Info must be sent
                        if (searchForDesOutputStream(info.getDesName()) != null) {
                            writer = new ObjectOutputStream(searchForDesOutputStream(info.getDesName()));
                            writer.writeObject(info);
                        }
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

    private OutputStream searchForDesOutputStream(String username) {
        for (String usernameHashmap : info.usersOutputStream.keySet()) {
            if (username.equals(usernameHashmap))
                return info.usersOutputStream.get(usernameHashmap);
        }
        return null;
    }
}
