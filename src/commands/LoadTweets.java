package commands;

import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;
import twitterwrapper.LoadedTweets;
import twitterwrapper.Tweet;

import java.util.ArrayList;
import java.util.List;

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
        return 0; //something went wrong
    }
    public int handleArgs(String[] args){
        //make sure there is a good number of parameters
        if(args.length > 2){
            System.out.println("This command cannot be used with more than 2 arguments");
            System.out.println("Type \"help loadtweets\" for more info");
            return 0; //something went wrong
        }
        //figure out the parameters
        String search = args[0];

        int numTweets = 100;
        if(args.length == 2) {
            try {
                numTweets = Integer.parseInt(args[1]);
                if(numTweets <= 0 || numTweets > 500){
                    System.out.println("Invalid number of tweets");
                    System.out.println("Type \"help loadtweets\" for more info");
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + args[1] + "\" cannot be parsed as an integer");
                return 0; // something went wrong
            }
        }

        //feedback
        System.out.println("Attempting to load " + numTweets + " Tweets using search \"" + search + "\"...");

        /*
            Begin loading tweets
            Since the Twitter API allows a maximum of 100 tweets per request
            We need to operate in multiple batches
         */

        //polymorphism !!!
        //Array list with search results
        List<Status> searchResults = new ArrayList<Status>();

        long maxId = -1;

        int numFound = 0; //number of tweets we found so far

        searchLoop:
        while(searchResults.size() < numTweets) {

            //setup query
            Query q = new Query(search + "+exclude:retweets exclude:replies"); //options because retweets + replies mess up data
            q.setCount(numTweets);
            q.resultType(Query.RECENT);
            q.setLang("en");
            q.setMaxId(maxId);

            try {
                QueryResult tempResults = conn.search(q);
                //if we found no tweets, we're done
                if(tempResults.getTweets().size() == 0){
                    break searchLoop;
                }
                for (Status curStatus : tempResults.getTweets()) {
                    if(maxId == -1 || curStatus.getId() < maxId){
                        maxId = curStatus.getId();
                    }
                    //add tweet
                    searchResults.add(curStatus);
                    //increment count
                    numFound++;
                    //if we found all our tweets, we're done
                    if(searchResults.size() >= numTweets){
                        break searchLoop;
                    }
                }
            } catch (Exception e) {
                System.out.println("Something went wrong...");
                return 0; //something went wrong
            }
        }
        System.out.println("Found " + numFound + " out of " + numTweets + " requested tweets.");

        if(searchResults.size() == 0){
            return 1; //no tweets found, but everything went okay :^)
        }
        //add tweets to currently loaded tweets
        LoadedTweets.getInstance().setParams(search, numFound);
        for(Status s : searchResults){
            LoadedTweets.getInstance().addTweet(new Tweet(s.getText()));
        }

        System.out.println("Tweets successfully loaded.");
        return 1;
    }
    public String getCommandPattern(){
        return "loadtweets search [numtweets]";
    }
    public String getInfo(){
        return "Loads a certain number of tweets with a certain search. \n" +
                "The maximum number of tweets is 500, the minimum is 1. \n" +
                "If no number of tweets is specified, 100 tweets are loaded. \n";
    }

}
