package commands;
import twitterwrapper.Tweet;

public class AnalyzeTweets implements ShellCommand{

    /*
        Attempt and Connect to the API. Static because we don't need to have multiple connections. This just calls a function
        in the Tweet class that connect, but it placed here for consistency.
        @return - 1 if successful, 0 if mistake is made
     */
    public static int connectToAPI(){
        return Tweet.connectToAPI(); //pass it on, pass it back
    }
    public int handleArgs() {
        return 0;
    }
    public int handleArgs(String[] args) {
        System.out.println("This command does not take in any arguments");
        System.out.println("Type \"help analyzetweets\" for more info");
        return 0; //something went wrong
    }
    public String getCommandPattern() {
        return "analyzetweets";
    }
    public String getInfo() {
        return "Analyzes the current loaded tweets. Note that tweeets must be loaded before they can" +
                "be analyzed";
    }
}
