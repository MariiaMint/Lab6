package org.example.comands;

import org.example.managers.CommandExecutor;

public class InfoCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public InfoCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.info();
    }
    public String description(){
        return(name + ": " + description);
    }
}
