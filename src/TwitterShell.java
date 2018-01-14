import java.util.*;

public class TwitterShell {
    public static void main(String[] args) {
        //greeting message
	    System.out.println("Welcome to the twitter shell!");
	    //I like it how this editor spell checks my last name
	    System.out.println("Created By Ivan Galakhov, Jan. 2018");

	    //attempt to connect to Twitter API
        System.out.print("Connecting to Twitter API");
        for(int i = 0; i < 2; i++){
            System.out.print(".");
            try {
                Thread.sleep(250);
            } catch(Exception e) {

            }
        }
        System.out.println(".");
        //check if something went wrong
        if(CommandHandler.establishTwitterConnection() == 0){
            System.out.println("Something went wrong");
            System.exit(1);
        }
        System.out.println("Connection Established!");

	    //run the shell
        CommandHandler mainHandler = new CommandHandler(new Scanner(System.in));

        mainHandler.run();
        
        //we should never get here because mainHandler.run() exits the program
        System.out.println("If you see this, something went terribly wrong");
    }
}
