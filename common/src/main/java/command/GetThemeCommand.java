package command;

import entity.KnlgThemes;
import lombok.Data;

@Data
public class GetThemeCommand extends CommandDto{
    KnlgThemes theme;
    public GetThemeCommand(KnlgThemes theme) {
        this.command = Command.GET_THEME;
        this.theme = theme;
    }
}
