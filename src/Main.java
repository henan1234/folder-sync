import java.util.Scanner;

import taursus.commands.*;
import taursus.folderSync.commands.CreateSyncFolderCommand;

public class Main {
    public static void main(String []args) {
        ICommandsRepository commandsRepository = new CommandsRepository();
        commandsRepository.addCommand("createSyncFolder", CreateSyncFolderCommand.class);
        
        Scanner s = new Scanner(System.in);
        
        while(true) {
            String cmdStr = s.nextLine();
            
            ICommand cmd = CommandParser.getInstance().parse(cmdStr, commandsRepository);
            
            if(cmd != null) {
                cmd.exec();
            } else {
                break;
            }
        }
    }
}
