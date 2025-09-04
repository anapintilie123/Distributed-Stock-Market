package nl.rug.aoop.command;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * handles all the commands from messages.
 */
@Slf4j
public class CommandHandler {

    @Getter
    private Map<String, ICommand> commandMap;

    /**
     * gg.
     * @param newCommandName gg.
     * @param newCommand hh.
     * constructor.
     */
    public CommandHandler(String newCommandName,ICommand newCommand) {
        initCommandMap(newCommandName,newCommand);
    }

    private void initCommandMap(String newCommandName,ICommand newCommand) {
        commandMap = new HashMap<>();
        this.registerCommand(newCommandName, newCommand);
    }

    /**
     * adds a command to the command map.
     *
     * @param name name of command.
     * @param command the command.
     */
    public void registerCommand(String name, ICommand command) {
        if(command == null){
            throw new IllegalArgumentException();
        }
        if(name == null){
            throw new IllegalArgumentException();
        }
        this.commandMap.put(name, command);
    }

    /**
     * Executes the command.
     *
     * @param cmdName any command name.
     * @param options option map for commands.
     */
    public void executeCommand(String cmdName,Map<String, Object> options) {
        if(cmdName == null){
            throw new IllegalArgumentException();
        }

        if(commandMap.size()==0){
            log.error("command map is empty");
        }
        if(commandMap.get(cmdName) ==null) {
            throw new IllegalArgumentException();
        }
        commandMap.get(cmdName).execute(options);
    }
}
