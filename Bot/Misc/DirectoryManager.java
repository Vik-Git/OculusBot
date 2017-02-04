package Bot.Misc;

import java.io.File;

/**
 * Created by Vik on 1/12/2016.
 */
public class DirectoryManager {

    private File homeDirectory = new File(Config.userDirectory+Config.home);


    public void createAllDirectories(){
        homeDirectory.mkdir();
        for(int i = 0; i < Config.subDirectories.length;i++){
            File subDir = new File(homeDirectory+"/"+Config.subDirectories[i]);
            subDir.mkdir();
        }
        System.out.println("All directories created");
    }

    public boolean checkDirectory(String sDir){
        if(new File(sDir).exists()) {
            return true;
        }
        return false;
    }

}
