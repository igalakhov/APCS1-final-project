package commands;


/*

 */
public class Exit implements ShellCommand {

    public int handleArgs(String[] args){
        System.out.println("This command does not take in any arguments");
        System.out.println("Type in \"help exit\" for more info");
        return 0; //because something went wrong
    }
    public int handleArgs(){
        System.exit(1);
        return 1; //should never return this, really
    }
    public String getCommandPattern(){
        return "exit";
    }
    public String getInfo(){
        return "Exits the shell with a return status of 1";
    }
}