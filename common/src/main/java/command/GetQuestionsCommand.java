package command;

import entity.KnlgQuestions;
import entity.KnlgThemes;
import lombok.Data;

import java.util.ArrayList;
@Data
public class GetQuestionsCommand extends CommandDto{
    ArrayList<KnlgQuestions> questions;
    KnlgThemes theme;

    public GetQuestionsCommand(ArrayList<KnlgQuestions> questions) {
        this.command = Command.GET_QUESTIONS_BY_QUESTIONS;
        this.questions = questions;
    }
    public GetQuestionsCommand(KnlgThemes theme) {
        this.command = Command.GET_THEME_QUESTIONS;
        this.theme = theme;
    }
}
