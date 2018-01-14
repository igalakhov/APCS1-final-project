package commands;
/*
    Exits the shell
 */
public class Exit implements ShellCommand {

    public int handleArgs(String[] args){
        System.out.println("This command cannot be used with arguments");
        System.out.println("Type \"help exit\" for more info");
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
