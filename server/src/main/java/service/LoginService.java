package service;

import command.LoginCommand;
import database.dao.StuffDao;
import entity.Stuff;
import exception.UserNotFoundException;
import response.ErrorResponse;
import response.LoginResponse;
import response.Response;

import java.sql.SQLException;

public class LoginService {

    private final StuffDao stuffDao;
    private static final LoginService loginService = new LoginService();

    private LoginService() {
        stuffDao = StuffDao.getInstance();
    }

    public static LoginService getInstance() {
        return loginService;
    }

    public Response login(LoginCommand loginCommand) throws SQLException {
        try {
            Stuff stuff = stuffDao.getByEmailAndPassword(loginCommand.getEmail(), loginCommand.getPassword());
            return new LoginResponse(stuff);
        } catch (UserNotFoundException stuffNotFoundException) {
            return new ErrorResponse("Пользователь не найден");
        }
    }
}
