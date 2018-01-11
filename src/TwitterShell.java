import java.util.Scanner;

public class TwitterShell {
    public static void main(String[] args) {

        //greeting message
	    System.out.println("Welcome to the twitter shell!");
	    System.out.println("Created By Ivan Galakhov, Jan. 2018");


	    //run the shell
        CommandHandler mainHandler = new CommandHandler(new Scanner(System.in));
        mainHandler.run();

        //we should never get here because mainHandler.run() exits the program
        System.out.println("If you see this, something went terribly wrong");
    }
}
