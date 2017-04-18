package taursus.folderSync;

public class SyncFoldersRepositoryFactory {
    public static ISyncFoldersRepository get() {
        return SyncFoldersRepository.getInstance();
    }
}
