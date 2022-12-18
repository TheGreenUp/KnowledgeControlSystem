package database.dao;

import database.ConnectionManager;
import entity.KnlgAnswers;
import entity.KnlgQuestions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnswerDao {
    private static final AnswerDao answerDao = new AnswerDao();
    private final ConnectionManager connectionManager;

    public static AnswerDao getInstance() {
        return answerDao;
    }

    private AnswerDao() {
        connectionManager = ConnectionManager.getInstance();
    }

    //

    public static final String CREATE_ANSWERS = "INSERT INTO knlg_answers(question_id, answer_text, is_correct) VALUES (?,?,?)";
    public static final String GET_ANSWERS = "SELECT * FROM knlg_answers WHERE question_id = ?";

    //


    public void createAnswers(ArrayList<KnlgAnswers> answers) throws SQLException {
        try (Connection connection = connectionManager.getConnection()) {
            for (int i = answers.size() - 1; i >= 0; i--) {
                PreparedStatement statement = connection.prepareStatement(CREATE_ANSWERS);
                statement.setInt(1, answers.get(i).getQuestion_id());
                statement.setString(2, answers.get(i).getAnswer_text());
                statement.setInt(3, (int) answers.get(i).intCorrectAnswer());
                statement.executeUpdate();
            }
        }

    }

    public ArrayList<KnlgAnswers> getAnswers(ArrayList<KnlgQuestions> questions) throws SQLException {
        ArrayList<KnlgAnswers> answers = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            for (int i = questions.size() - 1; i >= 0; i--) {
                PreparedStatement statement = connection.prepareStatement(GET_ANSWERS);
                statement.setInt(1, questions.get(i).getId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    answers.add(KnlgAnswers.builder()
                            .id(resultSet.getInt("id"))
                            .question_id(resultSet.getInt("question_id"))
                            .answer_text(resultSet.getString("answer_text"))
                            .is_correct(boolCorrect(resultSet.getInt("is_correct")))
                            .build());
                }
            }
            return answers;
        }
    }

    boolean boolCorrect(int i) {
        //todo рефактор, вынести в отдельный сервис
        if (i == 0) return false;
        else return true;
    }
}
