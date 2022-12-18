package service;

import command.GetThemeCommand;
import command.createTest.CreateThemeCommand;
import database.dao.ThemeDao;
import response.GetAllThemesResponse;
import response.GetThemeResponse;
import response.Response;
import response.createTest.CreateThemeResponse;

import java.sql.SQLException;

public class ThemeService {
    private final ThemeDao themeDao;
    private static final ThemeService themeService = new ThemeService();

    private ThemeService() {
        themeDao = ThemeDao.getInstance();
    }

    public static ThemeService getInstance() {
        return themeService;
    }
    public Response createTheme(CreateThemeCommand command) throws SQLException {
        themeDao.createTheme(command.getTheme().getThemeText(), command.getTheme().getDescription());
        return new CreateThemeResponse();
    }
    public Response getTheme(GetThemeCommand command) throws SQLException {
        return new GetThemeResponse(themeDao.getTheme(command.getTheme().getThemeText(),command.getTheme().getDescription()));
    }
    public Response getAll() throws SQLException {
        return new GetAllThemesResponse(themeDao.getAll());
    }
}
