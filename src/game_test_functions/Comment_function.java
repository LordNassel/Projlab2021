package game_test_functions;public class Comment_function {


    public void CommentPrinter(String[] command) {
        String temp = "";
        //igen i=1, a commandot mag�t nem akarom ki�rni
        for(int i=1; i<command.length; i++) {
            //erre is biztos van k�nyvt�ri fgv de gondolom ugyanezt csin�ln� -> ut�nan�zni
            temp+=command[i];
        }
        System.out.println(temp);

    }
}
