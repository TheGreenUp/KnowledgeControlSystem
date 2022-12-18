package command.createTest;

import command.Command;
import command.CommandDto;
import entity.KnlgThemes;

public class CreateTestCommand extends CommandDto {
    KnlgThemes theme;
    public CreateTestCommand(KnlgThemes theme) {
        this.command = Command.CREATE_TEST;
        this.theme = theme;
    }

}
