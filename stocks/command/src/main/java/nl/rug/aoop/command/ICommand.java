package nl.rug.aoop.command;

import java.util.Map;

/**
 * Command interface for executing.
 */
public interface ICommand {

    /**
     * executes a command.
     *
     * @param options list of commands.
     */
    void execute(Map<String, Object> options);
}
