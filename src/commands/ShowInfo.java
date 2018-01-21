package commands;

public class ShowInfo implements ShellCommand{
    public int handleArgs(String[] args) {
        return 0;
    }
    public int handleArgs(){

    }
    public String getCommandPattern() {
        return "showinfo ['averages/benchmark']";
    }
    public String getInfo() {
        return "Shows info about the current analysis. Options are as follows:" +
                "benchmark shows 3 tweets with lowest, median, and highest sentiment" +
                ", respectively. Averages show the average sentiment and emotion for all" +
                "tweets.";
    }

}
