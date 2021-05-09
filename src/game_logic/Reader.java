package game_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Reader {
    String[] filelist;
    //A tesztek folderje ahonnan listï¿½zzuk a teszteket

    public Reader(){
        File workfolder = new File("TestFiles");
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
        //Kell egy "+txt" a w½s miatt
        whichtest += ".txt";

        String path = new String("TestFiles\\");
        path += whichtest;
        //a beolvasï¿½sï¿½s
        try {
            File currenttest = new File(path);
            Scanner Filereader = new Scanner(currenttest);
            while (Filereader.hasNextLine()) {
                //Feltï¿½ltï¿½m a vectortï¿½rat string[]-ekkel
                String temp2[] = Filereader.nextLine().split(",");
                temp.add(temp2);
            }
            Filereader.close();
        } catch (FileNotFoundException e) {
            //helpful message for the user :D :D
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return temp;
    }

}
