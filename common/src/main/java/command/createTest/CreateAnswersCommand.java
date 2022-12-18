package command.createTest;

import command.Command;
import command.CommandDto;
import entity.KnlgAnswers;
import lombok.Data;

import java.util.ArrayList;
@Data
public class CreateAnswersCommand extends CommandDto {
    ArrayList<KnlgAnswers> answers;

    public CreateAnswersCommand(ArrayList<KnlgAnswers> answers) {
        this.command = Command.CREATE_ANSWERS;
        this.answers = answers;
    }
}
