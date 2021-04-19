package game_test_functions;public class Comment_function {


    public void CommentPrinter(String[] command) {
        String temp = "";
        //igen i=1, a commandot magát nem akarom kiírni
        for(int i=1; i<command.length; i++) {
            //erre is biztos van könyvtári fgv de gondolom ugyanezt csinálná -> utánanézni
            temp+=command[i];
        }
        System.out.println(temp);

    }
}
