package game_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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

public Vector<String[]> readtest(String whichtest) {

    Vector<String[]> temp = new Vector<String[]>();
    whichtest += ".txt";

    String path = new String("C:\\tesztfileok\\");
    path += whichtest;
    try {
        File currenttest = new File(path);
        Scanner Filereader = new Scanner(currenttest);
        while (Filereader.hasNextLine()) {
            String temp2[] = Filereader.nextLine().split(",");
            temp.add(temp2);
        }
        Filereader.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();


    }
    return temp;
}

}
