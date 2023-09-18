package org.example.comands;

import org.example.beginningClasses.HumanBeing;
import org.example.managers.CommandExecutor;

public class AddCommand implements  Command{
    CommandExecutor commandExecutor;
    String description;
    String name;
    public AddCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    public void execute(HumanBeing human) {
        commandExecutor.add(human);
    }
    public String description(){
        return(name + ": " + description);
    }
}
