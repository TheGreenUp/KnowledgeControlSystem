package command.createTest;

import command.Command;
import command.CommandDto;
import entity.KnlgThemes;
import lombok.Data;

@Data
public class CreateThemeCommand extends CommandDto {
    KnlgThemes theme;

    public CreateThemeCommand(KnlgThemes theme) {
        this.command = Command.CREATE_THEME;
        this.theme = theme;
    }
}
