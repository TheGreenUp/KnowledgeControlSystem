package database.dao;

import database.ConnectionManager;
import entity.KnlgQuestions;
import entity.KnlgThemes;
import entity.Stuff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDao {
    private static final QuestionDao questionDao = new QuestionDao();
    private final ConnectionManager connectionManager;

    public static QuestionDao getInstance() {
        return questionDao;
    }

    private QuestionDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    //==

    public static final String CREATE_QUESTIONS = "INSERT INTO knlg_questions(question, theme_id) VALUES (?,?)";
    public static final String GET_QUESTIONS_BY_THEME = "SELECT * FROM knlg_questions WHERE theme_id = ?";

    //==

    public void createQuestions(ArrayList<KnlgQuestions> questions) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            for (int i = questions.size() - 1; i >= 0; i--) {
                PreparedStatement statement = connection.prepareStatement(CREATE_QUESTIONS);
                statement.setString(1, questions.get(i).getQuestion());
                statement.setInt(2, questions.get(i).getTheme_id());
                statement.executeUpdate();
            }
        }
    }

    public ArrayList<KnlgQuestions> getQuestions(ArrayList<KnlgQuestions> questions) throws SQLException {
        ArrayList<KnlgQuestions> newQuestions = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(GET_QUESTIONS_BY_THEME);
                statement.setInt(1, questions.get(0).getTheme_id());
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    newQuestions.add(KnlgQuestions.builder()
                            .id(resultSet.getInt("id"))
                            .question(resultSet.getString("question"))
                            .theme_id(resultSet.getInt("theme_id"))
                            .build());
                }
                return newQuestions;
        }
    }
    public ArrayList<KnlgQuestions> getQuestions(KnlgThemes theme) throws SQLException {
        ArrayList<KnlgQuestions> newQuestions = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_QUESTIONS_BY_THEME);
            statement.setInt(1, theme.getId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                newQuestions.add(KnlgQuestions.builder()
                        .id(resultSet.getInt("id"))
                        .question(resultSet.getString("question"))
                        .theme_id(resultSet.getInt("theme_id"))
                        .build());
            }
            return newQuestions;
        }
    }


}
