package taursus.folderSync;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SyncFoldersRepository implements ISyncFoldersRepository {
    
    List<ISyncFolder> syncFolders = new ArrayList<ISyncFolder>();
    protected String pathToRepositoryFile = "./.syncFolders";
    
    public SyncFoldersRepository() {
        this.syncFolders = readFromRepositoryFile();
    }
    
    @Override
    public List<ISyncFolder> getAll() {
        return this.syncFolders;
    }

    @Override
    public ISyncFolder getByPublicKey(String key) {
        for(ISyncFolder syncFolder : this.syncFolders) {
            if(syncFolder.getPublicKey() == key) {
                return syncFolder;
            }
        }
        
        return null;
    }
    
    @Override
    public ISyncFolder getByPath(String path) {
        for(ISyncFolder syncFolder : this.syncFolders) {
            if(syncFolder.getPath() == path) {
                return syncFolder;
            }
        }
        
        return null;
    }

    @Override
    public void save(ISyncFolder folder) throws SyncFolderAlreadyExistsException {
        if(getByPath(folder.getPath()) != null || getByPublicKey(folder.getPublicKey()) != null) {
            throw new SyncFolderAlreadyExistsException();
        }
        
        this.syncFolders.add(folder);
        saveToRepositoryFile();
    }
    
    protected List<ISyncFolder> readFromRepositoryFile() {
        try {
            FileInputStream fileIn = new FileInputStream(this.pathToRepositoryFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<ISyncFolder> folders = (List<ISyncFolder>) in.readObject();
            
            in.close();
            fileIn.close();
            
            return folders;
         } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<ISyncFolder>();
         }
    }
    
    protected void saveToRepositoryFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.pathToRepositoryFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(this.syncFolders);
            
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
