package command.stuffManagment;

import command.Command;
import command.CommandDto;
import entity.Stuff;
import lombok.Data;

@Data
public class DeleteStuffCommand extends CommandDto {
    Stuff stuff;
    public DeleteStuffCommand(Stuff stuff){
        this.command = Command.DELETE_STUFF;
        this.stuff = stuff;
    }
}
