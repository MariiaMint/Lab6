package org.example.comands;
import org.example.beginningClasses.HumanBeing;
import org.example.managers.CommandExecutor;

public class AddIfMaxCommand implements Command{
    CommandExecutor commandExecutor;
    String description;
    String name;

    public AddIfMaxCommand(CommandExecutor commandExecutor, String description, String name) {
        this.commandExecutor = commandExecutor;
        this.description = description;
        this.name = name;
    }

    @Override
    public void execute(HumanBeing par) {
        commandExecutor.addIfMax(par);
    }
    public String description(){
        return(name + ": " + description);
    }
}
