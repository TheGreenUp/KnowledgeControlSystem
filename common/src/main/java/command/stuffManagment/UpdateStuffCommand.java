package command.stuffManagment;

import command.Command;
import command.CommandDto;
import entity.Stuff;
import lombok.Data;

@Data
public class UpdateStuffCommand extends CommandDto {
    Stuff stuff;
    public UpdateStuffCommand(Stuff stuff){
        this.command = Command.UPDATE_STUFF;
        this.stuff = stuff;
    }
}
