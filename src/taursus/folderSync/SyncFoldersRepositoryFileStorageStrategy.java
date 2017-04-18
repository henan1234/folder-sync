package taursus.folderSync;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SyncFoldersRepositoryFileStorageStrategy implements ISyncFoldersRepositoryStorageStrategy {
    protected String pathToRepositoryFile = "./.syncFolders";
    
    @Override
    public void save(List<ISyncFolder> folders) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.pathToRepositoryFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            out.writeObject(folders);
            
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ISyncFolder> retrive() {
        try {
            FileInputStream fileIn = new FileInputStream(this.pathToRepositoryFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<ISyncFolder> folders = (List<ISyncFolder>) in.readObject();
            
            in.close();
            fileIn.close();
            
            return folders;
         } catch(IOException | ClassNotFoundException e) {
            return new ArrayList<ISyncFolder>();
         }
    }

}
