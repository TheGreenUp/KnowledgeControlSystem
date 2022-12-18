package command.createTest;

import command.Command;
import command.CommandDto;
import entity.KnlgAnswers;
import entity.KnlgQuestions;
import lombok.Data;

import java.util.ArrayList;
@Data
public class CreateQuestionsCommand extends CommandDto {
    ArrayList<KnlgQuestions> questions;
    public CreateQuestionsCommand(ArrayList<KnlgQuestions> questions){
        this.command = Command.CREATE_QUESTIONS;
        this.questions = questions;

    }
}
