package taursus.folderSync;

import java.util.List;

public interface ISyncFoldersRepository {
    public class SyncFolderAlreadyExistsException extends Exception { }
    
    public List<ISyncFolder> getAll();
    public ISyncFolder getByPublicKey(String key);
    public ISyncFolder getByPath(String path);
    public void save(ISyncFolder folder) throws SyncFolderAlreadyExistsException;
}
