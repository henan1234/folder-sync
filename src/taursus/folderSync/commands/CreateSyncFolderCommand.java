package taursus.folderSync.commands;

import taursus.commands.*;
import taursus.folderSync.ISyncFolder;
import taursus.folderSync.ISyncFoldersRepository.SyncFolderAlreadyExistsException;
import taursus.folderSync.SyncFolder;
import taursus.folderSync.SyncFoldersRepositoryFactory;
import taursus.folderSync.generators.PrivateKeyGenerator;
import taursus.folderSync.generators.PublicKeyGenerator;

public class CreateSyncFolderCommand extends Command {
    
    @Override
    public void exec() {
        ISyncFolder folder = SyncFolder.create(this.params.get("path"), PublicKeyGenerator.getInstance(), PrivateKeyGenerator.getInstance());
        
        try {
            SyncFoldersRepositoryFactory.get().save(folder);
        } catch (SyncFolderAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
