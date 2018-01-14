package commands;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

/*
    Loads Tweets
    Some code adapted from:
    https://goo.gl/RmGzri
 */
public class LoadTweets implements  ShellCommand{

    //API access keys (can't be modified)
    private static final String CONSUMER_KEY = "9rLsxJwACZmXIu7YkbdqrDSCw";
    private static final String CONSUMER_SECRET = "fpFNIacTK9RTm171efQHVDunLU2dsn7HkML9uFXZT7CpH192Hf";

    //API connection
    private static Twitter conn = null;

    /*
        Attempt and connect to twitter api
        @return - 1 if successful, 0 if mistake is made
     */
    public static int connectToAPI(){
        OAuth2Token token = getToken();

        //check if something goes wrong
        if(token == null){
            return 0; //something wen't wrong
        }

        ConfigurationBuilder cb = new ConfigurationBuilder();

        //configure parameters
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_SECRET);

        cb.setOAuth2TokenType(token.getTokenType());
        cb.setOAuth2AccessToken(token.getAccessToken());

        //initialize the API connection (so we can use it when loading tweets later)
        conn =  new TwitterFactory(cb.build()).getInstance();

        return 1;
    }
    /*
       Returns an oAuth token
       @return valid oAuth token or null if something went wrong
     */
    private static OAuth2Token getToken(){

        OAuth2Token out = null; //the token that we will return

        ConfigurationBuilder cb = new ConfigurationBuilder();

        //configure parameters
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_SECRET);

        //try to generate a token
        try {
            out = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
        } catch (Exception e){
            //something went wrong
        }
        return out;
    }

    public int handleArgs(){
        System.out.println("This command cannot be used with no arguments");
        System.out.println("Type \"help loadtweets\" for more info");
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
