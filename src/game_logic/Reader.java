package game_logic;

import java.io.File;
import java.util.Vector;

public class Reader {
    String[] filelist;
    //A tesztek folderje ahonnan listázzuk a teszteket

public Reader(){
    File workfolder = new File("C:\\tesztfileok");
    filelist = workfolder.list();
}

public String[] magicfiles(){
    String temp[];
    temp = filelist;
    for (int i=0; i<temp.length; i++){
        temp[i] = temp[i].split(".txt")[0];
    }
    return temp;
}

public String[] readtest(String whichtest){

    String[][] temp = new String[][];
    whichtest += ".txt";

    String path = new String("C:\tesztfileok");
    path+=whichtest;

    File currettest = new File (path);
    return temp;
}


}
