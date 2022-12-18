package command;

import entity.KnlgQuestions;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GetAnswersCommand extends CommandDto{
    ArrayList<KnlgQuestions> questions;
    public GetAnswersCommand(ArrayList<KnlgQuestions> questions) {
        this.command = Command.GET_ANSWERS;
        this.questions = questions;
    }
}
