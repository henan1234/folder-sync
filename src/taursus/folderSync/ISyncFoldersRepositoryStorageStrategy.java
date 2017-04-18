package taursus.folderSync;

import java.util.List;

public interface ISyncFoldersRepositoryStorageStrategy {
    public void save(List<ISyncFolder> folders);
    public List<ISyncFolder> retrive();
}
