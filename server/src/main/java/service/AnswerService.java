package service;

import command.GetAnswersCommand;
import command.createTest.CreateAnswersCommand;
import database.dao.AnswerDao;
import entity.KnlgAnswers;
import entity.KnlgResults;
import response.GetAnswersResponse;
import response.Response;
import response.createTest.CreateAnswersResponse;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerService {
    private final AnswerDao answerDao;
    private static final AnswerService answerService = new AnswerService();

    private AnswerService() {
        answerDao = AnswerDao.getInstance();
    }

    public static AnswerService getInstance() {return answerService;}

    public Response createAnswers(CreateAnswersCommand command) throws SQLException {
        answerDao.createAnswers(command.getAnswers());
        return new CreateAnswersResponse();
    }
    public Response getAnswers(GetAnswersCommand command) throws SQLException {
        return new GetAnswersResponse(answerDao.getAnswers(command.getQuestions()));

    }

}
