package org.example.comands;

import org.example.managers.CommandExecutor;

public class ShowCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public ShowCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(String par) {
        commandExecutor.show();
    }
    public String description(){
        return(name + ": " + description);
    }
}

