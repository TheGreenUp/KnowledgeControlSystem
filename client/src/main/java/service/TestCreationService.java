package service;

import command.GetQuestionsCommand;
import command.GetThemeCommand;
import command.createTest.CreateAnswersCommand;
import command.createTest.CreateQuestionsCommand;
import command.createTest.CreateThemeCommand;
import entity.KnlgAnswers;
import entity.KnlgQuestions;
import entity.KnlgThemes;
import response.GetQuestionsReponse;
import response.GetThemeResponse;
import response.Response;
import response.createTest.CreateQuestionResponse;
import start.Client;

import java.util.ArrayList;
import java.util.Queue;

public class TestCreationService {

    public static Response createTheme(String themeText, String themeDescription) {
        KnlgThemes theme = new KnlgThemes(themeText, themeDescription);
        CreateThemeCommand command = new CreateThemeCommand(theme);
        Client.writeObject(command);
        return (Response) Client.readObject();
    }

    public static KnlgThemes getTheme(KnlgThemes theme) {
        GetThemeCommand command = new GetThemeCommand(theme);
        Client.writeObject(command);
        Response response = (Response) Client.readObject();
        if (response instanceof GetThemeResponse) {
            return ((GetThemeResponse) response).getTheme();
        }
        return null;
    }

    public static Response createQuestions(ArrayList<KnlgQuestions> questions) {
        CreateQuestionsCommand command = new CreateQuestionsCommand(questions);
        Client.writeObject(command);
        return (Response) Client.readObject();
    }

    public static ArrayList<KnlgQuestions> getQuestions(ArrayList<KnlgQuestions> questions){
        GetQuestionsCommand command = new GetQuestionsCommand(questions);
        Client.writeObject(command);
        Response response = (Response) Client.readObject();
        if (response instanceof GetQuestionsReponse)
            return ((GetQuestionsReponse) response).getQuestions();
        else return null;
    }

    public static Response createAnswers(ArrayList<KnlgAnswers> answers) {
        CreateAnswersCommand command = new CreateAnswersCommand(answers);
        Client.writeObject(command);
        return (Response) Client.readObject();
    }
}
