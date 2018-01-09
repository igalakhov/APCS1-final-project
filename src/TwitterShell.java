import java.util.Scanner;

public class TwitterShell {
    public static void main(String[] args) {

        //greeting message
	    System.out.println("Welcome to the twitter shell!");
	    System.out.println("Created By Ivan Galakhov, Jan. 2018");

	    //test with user input
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("> ");
            String in = scanner.nextLine();
            if(in.equals("exit")){
                break;
            }
        }


    }
}
