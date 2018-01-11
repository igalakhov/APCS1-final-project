import commands.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class CommandHandler {
    private Scanner input;
    private HashMap<String, ShellCommand> commandMap; // the map of all commands and their objects
    /*
       Creates a new handler to interpret user input
       @param input - a Scanner from which the handler
                      takes commands
    */
    public CommandHandler(Scanner input){

        //setup input
        this.input = input;

        //load the hashmap
        commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());

    }
    /*
       Runs the handler for however long it should run for
       During this time, the handler will take in and interpret the user commands
       Once the user inputs "exit", the System will exit with a status of 1
    */
    public void run(){
        while(true){
            System.out.print("> ");
            String raw = input.nextLine();
            String[] parsed = raw.split(" ");
            String commandName = parsed[0];
            String[] commandArgs = Arrays.copyOfRange(parsed, 1, parsed.length);
            //check if the command is valid
            if(commandMap.containsKey(commandName)){
                //get the command
                ShellCommand cur = commandMap.get(commandName);

                //run the command (tertiary expressions FTW!!!)
                int result = commandArgs.length == 0 ? cur.handleArgs() : cur.handleArgs(commandArgs);

                //maybe do something with the result here?


            } else {
                //let the user know they did something wrong
                System.out.println(commandName + " is not recognised as a command");
                System.out.println("type \"help\" for help");
            }
        }
    }
}