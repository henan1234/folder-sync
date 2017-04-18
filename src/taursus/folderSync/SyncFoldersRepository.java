package taursus.folderSync;

import java.util.ArrayList;
import java.util.List;

public class SyncFoldersRepository implements ISyncFoldersRepository {
    ISyncFoldersRepositoryStorageStrategy storageStrategy = new SyncFoldersRepositoryFileStorageStrategy();
    List<ISyncFolder> syncFolders = new ArrayList<ISyncFolder>();
    

    protected static class SyncFoldersRepositoryLoader {
        private static final SyncFoldersRepository INSTANCE = new SyncFoldersRepository();
    }

    protected SyncFoldersRepository() {
        if (SyncFoldersRepositoryLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
        
        this.syncFolders = this.storageStrategy.retrive();
    }

    public static SyncFoldersRepository getInstance() {
        return SyncFoldersRepositoryLoader.INSTANCE;
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
        this.storageStrategy.save(this.syncFolders);
    }
}
