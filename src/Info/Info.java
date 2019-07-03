package Info;
public class Info {
    private int type;
    private byte[] fileByteCode;
    private String fileName;

    public Info(int type) {
        this.type = type;
    }

    public Info() {
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
