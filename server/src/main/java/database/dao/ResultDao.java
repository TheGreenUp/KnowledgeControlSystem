package database.dao;

import database.ConnectionManager;
import entity.KnlgResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResultDao {
    private static final ResultDao resultDao = new ResultDao();
    private final ConnectionManager connectionManager;

    public static ResultDao getInstance() {
        return resultDao;
    }

    private ResultDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    public static final String CREATE_RESULT = "INSERT INTO knlg_results(stuff_id, correct_answers, result) VALUES(?,?,?)";

    public void createResult(KnlgResults result) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(CREATE_RESULT);
                statement.setInt(1, result.getStuff_id());
                statement.setInt(2, result.getCorrect_answers());
                statement.setString(3, result.getResult());
                statement.executeUpdate();
        }

    }
}
