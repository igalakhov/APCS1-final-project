package twitterwrapper;

import java.util.List;
import java.util.ArrayList;
/*
    Tweet Class
    This class is a bit more simplified from the purposes

 */
public class Tweet {
    private String body; //the text of the tweet
    private int sentiment; //sentiment value of the tweet (from watson)

    public boolean analyzed; //whether or not the tweet has been fed through watson

    private List<String> myMentions; //this tweets mentions
    private List<String> myHashtags; //this tweets hashtags
    /*
        Creates a new Tweet with a certain body
        @param body - the body text of the tweet. This text does not need to be cleaned because the constructor will
        automatically clean it to remove unwanted material
     */
    public Tweet(String body){
        //parsing that we need for everything
        body = body.replaceAll("\n", " ");
        body = body.replaceAll("\\s{2,}", " ");
        this.body = cleanText(body);

        //parse out the hashtags and mentions
        myHashtags = new ArrayList<>();
        myMentions = new ArrayList<>();

        for(String curPart : body.split(" ")){
            if(curPart.length() == 0) continue;
            String first = curPart.trim().substring(0, 1);
            if(first.equals("#")){
                myHashtags.add(curPart.trim());
            }
            if(first.equals("@")){
                myMentions.add(curPart.trim());
            }
        }
        System.out.println(this.body);
        System.out.println(myHashtags);
        System.out.println(myMentions);
    }
    /*
        Cleans text by parsing out urls, hashtags, and mentions
        @param in - text to clean
        @return - cleaned text
     */
    public static String cleanText(String in){
        String out = "";
        String temp[] = in.split(" ");

        //remove all links, mentions and hashtags
        for(String curPart : temp) {
            if(curPart.length() != 0 && curPart.substring(0, 1).equals("@")){
                continue;
            }
            if(curPart.length() != 0 &&curPart.substring(0, 1).equals("#")){
                continue;
            }
            if (curPart.indexOf("https") == -1) {
                out += curPart;
                out += " ";
            }
        }
        return out;
    }
    public enum properties {

    }
}
