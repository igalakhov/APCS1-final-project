package twitterwrapper;

import java.util.ArrayList;
import java.util.Collections;
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
        Prints out all hashtags
     */
    public void showHashtags(){
        List<String> uniqueHashtags = new ArrayList<>();
        for(Tweet t : curLoaded){
            for(String tag : t.getHashtags()){
                if(!uniqueHashtags.contains(tag)){
                    uniqueHashtags.add(tag);
                }
            }
        }
        Collections.sort(uniqueHashtags);
        int cur = 1;
        for(String hashtag : uniqueHashtags){
            System.out.print(hashtag + " ");
            if((cur % 5) == 0){
                System.out.println("");
            }
            cur++;
        }
        System.out.println("");
    }
    /*
        Prints out all mentions
     */
    public void showMentions(){
        List<String> uniqueMentions = new ArrayList<>();
        for(Tweet t : curLoaded){
            for(String mention : t.getMentions()){
                if(!uniqueMentions.contains(mention)){
                    uniqueMentions.add(mention);
                }
            }
        }
        Collections.sort(uniqueMentions);
        int cur = 1;
        for(String mention : uniqueMentions){
            System.out.print(mention + " ");
            if((cur % 5) == 0){
                System.out.println("");
            }
            cur++;
        }
        System.out.println("");
    }
    /*
        Prints out benchmarks
     */
    public void showBenchmarks(){
        List<Tweet> copy = new ArrayList<>(curLoaded);
        Collections.sort(copy);

        Tweet lo = copy.get(0);

        System.out.printf("Lowest Sentiment ( %.2f ):", lo.getSentiment());
        System.out.println(lo.getRawBody());
        System.out.println("");

        Tweet mid = copy.get((copy.size() / 2));
        System.out.printf("Median Sentiment ( %.2f ):", mid.getSentiment());
        System.out.println(mid.getRawBody());
        System.out.println("");

        Tweet hi = copy.get(copy.size() - 1);
        System.out.printf("Highest Sentiment ( %.2f ):", hi.getSentiment());
        System.out.println(hi.getRawBody());
        System.out.println("");
    }
    /*
        Prints out averages for all values for tweets
     */
    public void showAverages(){
        double avgSentiment = 0;
        double avgJoy = 0;
        double avgSadness = 0;
        double avgFear = 0;
        double avgDigust = 0;
        double avgAnger = 0;

        for(Tweet t : curLoaded){
            avgSentiment += t.getSentiment();
            avgJoy += t.getJoy();
            avgSadness += t.getSadness();
            avgFear += t.getFear();
            avgDigust += t.getDisgust();
            avgAnger += t.getAnger();
        }

        System.out.printf("Average Sentiment: %.2f", avgSentiment / curLoaded.size());
        System.out.println("");
        System.out.printf("Average Joy: %.2f", avgJoy / curLoaded.size());
        System.out.printf("Average Sadness: %.2f", avgSadness / curLoaded.size());
        System.out.printf("Average Fear: %.2f", avgFear / curLoaded.size());
        System.out.printf("Average Disgust: %.2f", avgDigust / curLoaded.size());
        System.out.printf("Average Anger: %.2f", avgAnger / curLoaded.size());
    }
    /*
        Private constructor because this is a singleton
     */
    private LoadedTweets(){
        curLoaded = new ArrayList<>();
        isLoaded = false;
    }
    /*
        Analyzes all tweets
     */
    public void analyzeTweets(){
        for(Tweet t : curLoaded){
            t.analyze();
        }
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
