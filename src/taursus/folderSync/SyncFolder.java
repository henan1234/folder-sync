package taursus.folderSync;

import java.io.Serializable;

import taursus.folderSync.generators.IKeyGenerator;

public class SyncFolder implements ISyncFolder, Serializable {
    private String publicKey;
    private String privateKey;
    private String path;
    
    protected SyncFolder() { }

    public static SyncFolder create(String path, IKeyGenerator publicKeyGenerator, IKeyGenerator privateKeyGenerator) {
        SyncFolder folder = new SyncFolder();
        folder.setPath(path);
        folder.setPrivateKey(privateKeyGenerator.generate());
        folder.setPublicKey(publicKeyGenerator.generate());
        return folder;
    }
    
    @Override
    public String getPublicKey() {
        return this.publicKey;
    }
    
    @Override
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }
    
    
}
