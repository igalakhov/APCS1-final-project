package commands;

/*
    Interface of a ShellCommand,
    we need this for the polymorphism in the
    hash map in the ShellCommand handler \o/
 */
public interface ShellCommand {
    /*
    First function handles the ShellCommand based on arguments
    Second function handles the ShellCommand if it has no arguments
    For the sake of the program, we have to define both when implementing this interface

    @param args - array of arguments for the ShellCommand
    @return - int, 1 is ShellCommand was successful, 0 otherwise
     */
    int handleArgs(String[] args);
    int handleArgs();

    /*
    @return - the ShellCommand pattern of the ShellCommand (note that this does not print anything)
     */
    String getCommandPattern();

    /*
      @return - returns info about the ShellCommand
     */
    String getInfo();
}
