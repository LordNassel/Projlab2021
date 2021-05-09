package game_test_functions;public class Comment_function {


    public void CommentPrinter(String[] command) {
        String temp = "";
        for(int i=1; i<command.length; i++) {
            temp+=command[i];
        }
        System.out.println(temp);

    }
}
