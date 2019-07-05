package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Info implements Serializable {
    private int type;
    private byte[] fileByteCode;
    private String fileName;
    private String srcName;
    private String desName;
    static HashMap<String, OutputStream> usersOutputStream = new HashMap<>();
    static HashMap<String, InputStream> usersInputStream= new HashMap<>();
    public Info(String srcName,String desName,int type) {
        this.type = type;
        this.srcName = srcName;
        this.desName = desName;
    }

    public String getDesName() {
        return desName;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setFileByteCode(byte[] fileByteCode) {
        this.fileByteCode = fileByteCode;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getFileByteCode() {
        return fileByteCode;
    }

    public String getFileName() {
        return fileName;
    }


    public int getType() {
        return type;
    }
}
