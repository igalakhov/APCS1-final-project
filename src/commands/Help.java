package commands;

import java.util.HashMap;

public class Help implements ShellCommand{

    //commands that we can use
    private HashMap<String, ShellCommand> commandInfo;

    /*
        Sets the hashmap that this command uses to output info
        about commands

        @param in - the hashmap that we have to set
     */
    public void attachHashMap(HashMap<String, ShellCommand> in){
        commandInfo = in;
    }
    public int handleArgs(String[] args){
        //this command only takes in one argument
        if(args.length > 1){
            System.out.println("This command does not take more than 1 argument");
            return 0; //something went wrong
        }
        String commandName = args[0];
        if(commandInfo.containsKey(commandName)){
            System.out.println(commandInfo.get(commandName).getCommandPattern());
            System.out.println(commandInfo.get(commandName).getInfo());
        } else {
            System.out.println("This command does not exist");
        }
        return 1;
    }
    public int handleArgs() {
        //print pattern
        System.out.println("[command name]");
        System.out.println("[command pattern]");
        System.out.println("[command description]");
        System.out.println("");

        //iterate through hashmap and print info
        for(String command : commandInfo.keySet()){
            System.out.println(command);
            System.out.println(commandInfo.get(command).getCommandPattern());
            System.out.println(commandInfo.get(command).getInfo());
            System.out.println("");
        }
        return 1;
    }
    public String getCommandPattern() {
        return "help [commandname]";
    }
    public String getInfo(){
        return "Outputs help about a command";
    }
}
