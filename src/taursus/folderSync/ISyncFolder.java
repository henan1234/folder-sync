package taursus.folderSync;

public interface ISyncFolder {
    public String getPublicKey();
    public void setPublicKey(String key);
    public String getPrivateKey();
    public void setPrivateKey(String key);
    public String getPath();
    public void setPath(String path);
}
