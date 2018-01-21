package twitterwrapper;

import java.util.ArrayList;
import java.util.List;

/*
    Contains all the tweets that are currently loaded
    Its easier to put this in a wrapper because it allows us to easily store
    metadata about the search.
    Note that this class is a singleton because (why not?) we will never have more searches loaded at the same time.
*/
public class LoadedTweets {
    /* Initialize singleton */
    private static LoadedTweets myself = new LoadedTweets();

    //current search data
    private List<Tweet> curLoaded; //loaded tweets
    private String search; //current search
    private int numResults; //number of results
    private boolean isLoaded; //check if any tweets are loaded

    /*
        Private constructor because this is a singleton
     */
    private LoadedTweets(){
        curLoaded = new ArrayList<>();
        isLoaded = false;
    }

    //get myself
    public static LoadedTweets getInstance(){
        return myself;
    }

    /*
        Checks if any tweets are currently loaded
        (this is for commands, since it makes it much easier to work with if we put all of your
        println statements in one place)
        @return - true if tweets are loaded, false otherwise
     */
    public boolean checkLoaded(){ return isLoaded; }
    /*
        Set the params of the current tweet search
        Also clears the current loaded ArrayList
        @param search - the string of the search
        @param numResults - the number of results the search had
     */
    public void setParams(String search, int numResults){
        this.search = search;
        this.numResults = numResults;
    }
    /*
        Adds tweets to the current loaded array
        Note that setParams has to be called before
        @param toAdd - tweet to add
     */
    public void addTweet(Tweet toAdd){
        if(!isLoaded) isLoaded = true;
        curLoaded.add(toAdd);
    }

}
