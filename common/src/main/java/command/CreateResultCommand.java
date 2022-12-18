package command;

import entity.KnlgResults;
import lombok.Data;

@Data
public class CreateResultCommand extends CommandDto{
    KnlgResults result;

    public CreateResultCommand(KnlgResults result) {
        this.command = Command.CREATE_RESULT;
        this.result = result;
    }
}
