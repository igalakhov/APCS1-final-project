package twitterwrapper;

/*
    Tweet Class
    This class is a bit more simplified from the purposes

 */
public class Tweet {
    private String body; //the text of the tweet
    private int sentiment; //sentiment value of the tweet (from watson)

    public boolean analyzed; //whether or not the tweet has been fed through watson

    /*
        Creates a new Tweet with a certain body
        @param body - the body text of the tweet. This text does not need to be cleaned because the constructor will
        automatically clean it to remove unwanted material
     */
    public Tweet(String body){
        this.body = body;
    }
    /*
        Cleans text by parsing out urls, hashtags, and mentions
        @param in - text to clean
        @return - cleaned text
     */
    public static String cleanText(String in){
        String out = in.replaceAll("@(https?://([-\\w\\.]+[-\\w])+(:\\d+)?(/([\\w/_\\.#-]*(\\?\\S+)?[^\\.\\s])?).*$)@", " ");
        return out;
    }
    public enum properties {

    }
}
