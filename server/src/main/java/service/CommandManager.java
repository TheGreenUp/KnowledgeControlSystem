package service;
import command.*;
import command.createTest.CreateAnswersCommand;
import command.createTest.CreateQuestionsCommand;
import command.createTest.CreateThemeCommand;
import command.stuffManagment.AddStuffCommand;
import command.stuffManagment.DeleteStuffCommand;
import command.stuffManagment.UpdateStuffCommand;
import response.Response;

import java.sql.SQLException;

public class CommandManager {

    private static final CommandManager commandManager = new CommandManager();

    private CommandManager() {
    }

    public static CommandManager getInstance() {
        return commandManager;
    }

    public Response execute(CommandDto commandDto) throws SQLException {
        Response response;
        switch (commandDto.getCommand()) {
            case LOGIN -> response = LoginService.getInstance().login((LoginCommand) commandDto);

            case STUFF_LIST -> response = StuffService.getInstance().getAllStuff();
            case ADD_STUFF -> response = StuffService.getInstance().addStuff((AddStuffCommand) commandDto);
            case UPDATE_STUFF -> response = StuffService.getInstance().updateStuff((UpdateStuffCommand) commandDto);
            case DELETE_STUFF -> response = StuffService.getInstance().deleteStuff((DeleteStuffCommand) commandDto);

            case CREATE_THEME -> response = ThemeService.getInstance().createTheme((CreateThemeCommand) commandDto);
            case GET_THEME -> response = ThemeService.getInstance().getTheme((GetThemeCommand) commandDto);
            case GET_ALL_THEMES -> response = ThemeService.getInstance().getAll();

            case CREATE_QUESTIONS -> response = QuestionService.getInstance().createQuestions((CreateQuestionsCommand) commandDto);
            case GET_QUESTIONS_BY_QUESTIONS -> response = QuestionService.getInstance().getQuestions((GetQuestionsCommand) commandDto);
            case GET_THEME_QUESTIONS -> response = QuestionService.getInstance().getQuestionsByTheme((GetQuestionsCommand) commandDto);

            case CREATE_ANSWERS -> response = AnswerService.getInstance().createAnswers((CreateAnswersCommand) commandDto);
            case GET_ANSWERS -> response = AnswerService.getInstance().getAnswers((GetAnswersCommand) commandDto);

            case CREATE_RESULT -> response = ResultService.getInstance().createResult((CreateResultCommand) commandDto);

            default -> throw new RuntimeException("Команда не поддерживаестя " + commandDto.getCommand());
        }
        return response;
    }
}
