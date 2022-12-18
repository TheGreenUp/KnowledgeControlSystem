package database.dao;

import database.ConnectionManager;
import entity.KnlgThemes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThemeDao {
    private static final ThemeDao themeDao = new ThemeDao();
    private final ConnectionManager connectionManager;

    public static ThemeDao getInstance() {
        return themeDao;
    }

    private ThemeDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    //
    public static final String CREATE_THEME = "INSERT INTO knlg_themes(name, description) VALUES(?,?)";
    public static final String GET_THEME = "SELECT * FROM knlg_themes WHERE name = ? AND description = ?";
    public static final String GET_ALL = "SELECT * FROM knlg_themes";

    //

    public void createTheme(String name, String description) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_THEME);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.executeUpdate();
        }
    }

    public KnlgThemes getTheme(String name, String description) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_THEME);
            statement.setString(1, name);
            statement.setString(2, description);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new KnlgThemes(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            } else return null;
        }
    }
    public ArrayList<KnlgThemes> getAll() throws SQLException {
        ArrayList<KnlgThemes> themes = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                KnlgThemes theme = new KnlgThemes(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                themes.add(theme);
            }
            return themes;
        }
    }
}
