package org.example.comands;
import org.example.managers.CommandExecutor;

public class ExecuteScriptCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public ExecuteScriptCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.execute_script();
    }
    public String description(){
        return(name + ": " + description);
    }
}
