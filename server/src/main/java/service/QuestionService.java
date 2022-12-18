package service;

import command.GetQuestionsCommand;
import command.createTest.CreateQuestionsCommand;
import database.dao.QuestionDao;
import response.GetQuestionsReponse;
import response.Response;
import response.createTest.CreateQuestionResponse;

import java.sql.SQLException;

public class QuestionService {
    private final QuestionDao questionDao;
    private static final QuestionService questionService = new QuestionService();

    private QuestionService() {
        questionDao = QuestionDao.getInstance();
    }

    public static QuestionService getInstance() {
        return questionService;
    }

    public Response createQuestions(CreateQuestionsCommand command) throws SQLException {
        questionDao.createQuestions(command.getQuestions());
        return new CreateQuestionResponse();
    }
    public Response getQuestions(GetQuestionsCommand command) throws SQLException {
        return new GetQuestionsReponse(questionDao.getQuestions(command.getQuestions()));
    }
    public Response getQuestionsByTheme(GetQuestionsCommand command) throws SQLException {
        return new GetQuestionsReponse(questionDao.getQuestions(command.getTheme()));
    }
}
