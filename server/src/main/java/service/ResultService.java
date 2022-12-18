package service;

import command.CreateResultCommand;
import database.dao.AnswerDao;
import database.dao.ResultDao;
import response.CreateResultResponse;
import response.Response;

import java.sql.SQLException;

public class ResultService {
    private final ResultDao resultDao;
    private static final ResultService resultService = new ResultService();

    private ResultService() {
        resultDao = ResultDao.getInstance();
    }

    public static ResultService getInstance() {return resultService;}
    public Response createResult(CreateResultCommand command) throws SQLException {
        resultDao.createResult(command.getResult());
        return new CreateResultResponse();
    }
}
