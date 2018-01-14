package commands;


import twitter4j.auth.OAuth2Token;

public class LoadTweets implements  ShellCommand{
    static int x = 10;

    /*
        Attempt and connect to twitter api
        @return - 1 if successful, 0 if mistake is made
     */
    public static int connectToAPI(){


        return 1;
    }
    /*
       Returns an oAuth token
       @return valid oAuth token or null if something went wrong
     */
    private static OAuth2Token getToken(){
        OAuth2Token out = null; //the token that we will return


        return null;
    }

    public int handleArgs(){
        System.out.println("This command cannot be used with no arguments");
        return 0;
    }
    public int handleArgs(String[] args){
        return 1;
    }
    public String getCommandPattern(){
        return null;
    }
    public String getInfo(){
        return null;
    }

}
