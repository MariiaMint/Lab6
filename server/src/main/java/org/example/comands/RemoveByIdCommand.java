package org.example.comands;

import org.example.managers.CommandExecutor;

public class RemoveByIdCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public RemoveByIdCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.removeBId(par);
    }

    public String description() {
        return(name + ": " + description);
    }
}
