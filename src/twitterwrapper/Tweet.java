package twitterwrapper;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.*;

import java.util.List;
import java.util.ArrayList;
/*
    Tweet Class
    This class is a bit more simplified from the purposes

 */
public class Tweet {
    private String body; //the text of the tweet
    private double mySentiment; //sentiment value of the tweet (from watson)
    //emotions (enum would be better here, to be honest)
    private double mySadness;
    private double myJoy;
    private double myFear;
    private double myDisgust;
    private double myAnger;

    public boolean analyzed; //whether or not the tweet has been fed through watson

    private List<String> myMentions; //this tweets mentions
    private List<String> myHashtags; //this tweets hashtags

    private static NaturalLanguageUnderstanding service; //IBM service
    private static final String USERNAME = "fdedbc34-cb43-4787-9308-4f323f3f9c11"; //IBM service login
    private static final String PASSWORD = "0ISekPiKsBDX"; //IBM service password

    //IBM service options (don't need to update these again)
    private static final Features features = new Features.Builder().emotion(
            new EmotionOptions.Builder().build()
    ).sentiment(
       new SentimentOptions.Builder().build()
    ).build();
    /*
        These functions return values of the tweet
        @return requested value
     */
    public double getSentiment(){return mySentiment;};
    public double getJoy(){return myJoy;};
    public double getFear(){return myFear;};
    public double getDisgust(){return myDisgust;};
    public double getAnger(){return myAnger;};
    /*
       Analyzes the tweet
    */
    public void analyze(){
        AnalyzeOptions params = new AnalyzeOptions.Builder().text(body).features(features).language("en").build();
        AnalysisResults results = service.analyze(params).execute();

        EmotionScores emotions = results.getEmotion().getDocument().getEmotion();

        myJoy = emotions.getJoy();
        myFear = emotions.getFear();
        myDisgust = emotions.getDisgust();
        myAnger = emotions.getFear();

        mySentiment = results.getSentiment().getDocument().getScore();
    }

    /*
        Connects to IBM API
        1 is successful, 0 if something went wrong
     */
    public static int connectToAPI(){
        service = new NaturalLanguageUnderstanding(
          NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                USERNAME,
                PASSWORD
        );
        //the watson API provides no way of testing if login is valid, lets hope these are correct?
        return 1;
    }
    /*
        Creates a new Tweet with a certain body
        @param body - the body text of the tweet. This text does not need to be cleaned because the constructor will
        automatically clean it to remove unwanted material
     */
    public Tweet(String body){
        //parsing that we need for everything
        body = body.replaceAll("\n", " "); //new lines
        body = body.replaceAll("\\s{2,}", " "); //large amount of spaces
        body = body.trim(); //left and right spaces

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
