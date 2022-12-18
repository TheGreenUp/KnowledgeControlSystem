package command.stuffManagment;

import command.Command;
import command.CommandDto;
import entity.Stuff;
import lombok.Data;

@Data
public class AddStuffCommand extends CommandDto {
    private final Stuff stuff;

    public AddStuffCommand(Stuff stuff) {
        this.command = Command.ADD_STUFF;
        this.stuff = stuff;
    }
}
