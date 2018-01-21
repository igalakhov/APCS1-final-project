package commands;

import twitterwrapper.LoadedTweets;

public class ShowInfo implements ShellCommand{
    public int handleArgs(String[] args) {
        if(!LoadedTweets.getInstance().checkLoaded()){
            System.out.println("No tweets loaded");
            return 0; //something went wrong
        }
        if(args.length > 1){
            System.out.println("This command cannot be used with more than one argument");
            System.out.println("Type \"help showinfo\" for more info");
            return 0; //something went wrong
        }
        switch(args[0]){
            case "averages":
                LoadedTweets.getInstance().showAverages();
                break;
            case "benchmarks":
                LoadedTweets.getInstance().showBenchmarks();
                break;
            case "hashtags":
                LoadedTweets.getInstance().showHashtags();
                break;
            case "mentions":
                LoadedTweets.getInstance().showMentions();
                break;
            default:
                System.out.println(args[0] + " cannot be used as an option for this command");
                System.out.println("Type \"help showinfo\" for more info");
                return 0; //something went wrong
        }

        return 1;
    }
    public int handleArgs(){
        System.out.println("This command cannot be used with no arguments");
        System.out.println("Type \"help showinfo\" for more info");
        return 0; //something went wrong
    }
    public String getCommandPattern() {
        return "showinfo averages/benchmarks/mentions/hashtags";
    }
    public String getInfo() {
        return "Shows info about the current analysis. Options are as follows:" +
                "benchmarks shows 3 tweets with lowest, median, and highest sentiment" +
                ", respectively. Averages show the average sentiment and emotion for all" +
                "tweets. Mentions shows all mentions (as a set) used with the collected tweets" +
                "Hashtags shows all the hashtags (as a set) used with the collected tweets";
    }

}
