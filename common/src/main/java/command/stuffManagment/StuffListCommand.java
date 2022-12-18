package command.stuffManagment;

import command.Command;
import command.CommandDto;

public class StuffListCommand extends CommandDto {
    public StuffListCommand() {
        this.command = Command.STUFF_LIST;
    }
}
