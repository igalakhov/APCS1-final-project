package twitterwrapper;

public class abc {
    private static abc ourInstance = new abc();

    public static abc getInstance() {
        return ourInstance;
    }

    private abc() {
    }
}
