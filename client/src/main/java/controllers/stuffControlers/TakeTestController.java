package controllers.stuffControlers;

import command.CreateResultCommand;
import command.GetAnswersCommand;
import command.GetQuestionsCommand;
import dataHolders.StuffDataHolder;
import dataHolders.ThemeDataHolder;
import entity.KnlgAnswers;
import entity.KnlgQuestions;
import entity.KnlgResults;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import response.*;
import start.Client;
import utils.DialogUtils;
import utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TakeTestController implements Initializable {
    //region Переменные
    //region Ответы
    @FXML
    private Label answer_1_1;

    @FXML
    private Label answer_1_2;

    @FXML
    private Label answer_1_3;

    @FXML
    private Label answer_1_4;

    @FXML
    private Label answer_2_1;

    @FXML
    private Label answer_2_2;

    @FXML
    private Label answer_2_3;

    @FXML
    private Label answer_2_4;

    @FXML
    private Label answer_3_1;

    @FXML
    private Label answer_3_2;

    @FXML
    private Label answer_3_3;

    @FXML
    private Label answer_3_4;

    @FXML
    private Label answer_4_1;

    @FXML
    private Label answer_4_2;

    @FXML
    private Label answer_4_3;

    @FXML
    private Label answer_4_4;

    //endregion

    //region Кнопачки
    @FXML
    private Button createTestBtncompleteTestBtn;
    @FXML
    private Button backBtn;
    //endregion

    //region Табки
    @FXML
    private Tab questionTab_1;

    @FXML
    private Tab questionTab_2;

    @FXML
    private Tab questionTab_3;

    @FXML
    private Tab questionTab_4;
    //endregion

    //region Правильные ответы

    @FXML
    private RadioButton corans_1_1;

    @FXML
    private RadioButton corans_1_2;

    @FXML
    private RadioButton corans_1_3;

    @FXML
    private RadioButton corans_1_4;

    @FXML
    private RadioButton corans_2_1;

    @FXML
    private RadioButton corans_2_2;

    @FXML
    private RadioButton corans_2_3;

    @FXML
    private RadioButton corans_2_4;

    @FXML
    private RadioButton corans_3_1;

    @FXML
    private RadioButton corans_3_2;

    @FXML
    private RadioButton corans_3_3;

    @FXML
    private RadioButton corans_3_4;

    @FXML
    private RadioButton corans_4_1;

    @FXML
    private RadioButton corans_4_2;

    @FXML
    private RadioButton corans_4_3;

    @FXML
    private RadioButton corans_4_4;
    //endregion

    //region Вопросы
    @FXML
    private Label question_1;

    @FXML
    private Label question_2;

    @FXML
    private Label question_3;

    @FXML
    private Label question_4;
    //endregion

    static ArrayList<KnlgAnswers> correctAnswers;
    static int numberOfCorrectAnswers = 0;
    static String testResult = "";
    //endregion

    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuffViews/take-test.fxml", "Тестирование");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetQuestionsCommand command = new GetQuestionsCommand(ThemeDataHolder.getInstance().getThemes());
        Client.writeObject(command);
        Response response = (Response) Client.readObject();
        if (response instanceof GetQuestionsReponse) {
            initQuestions(((GetQuestionsReponse) response).getQuestions());

            GetAnswersCommand getAnswersCommand = new GetAnswersCommand(((GetQuestionsReponse) response).getQuestions());
            Client.writeObject(getAnswersCommand);
            response = (Response) Client.readObject();

            if (response instanceof GetAnswersResponse) {
                ArrayList<KnlgAnswers> answers = ((GetAnswersResponse) response).getAnswers();

                correctAnswers = getCorrectAnswers(answers);

                initAnswers(answers);

            }
        }
    }

    @FXML
    void onCompleteTestBtn() throws IOException {
        if (!isAnswersSelected()) {
            return;
        }
        checkAnswers();
        getTestResult();
        sendResult();
        onBackBtn();
    }

    @FXML
    void onBackBtn() throws IOException {
        new StuffViewController().show((Stage) backBtn.getScene().getWindow());
    }

    void sendResult() {
        KnlgResults result = new KnlgResults(
                StuffDataHolder.getInstance().getStuff().getId(),
                numberOfCorrectAnswers,
                testResult);
        CreateResultCommand command = new CreateResultCommand(result);
        Client.writeObject(command);
        Response response = (Response) Client.readObject();
        if (response instanceof CreateResultResponse) {

        } else if (response instanceof ErrorResponse) {
            DialogUtils.showError(((ErrorResponse) response).getErrorMessage(), "Ошибка!");
        }


    }


    //todo как-нибудь перенести в сервис
    void initQuestions(ArrayList<KnlgQuestions> questions) {
        question_1.setText(questions.get(0).getQuestion());
        question_2.setText(questions.get(1).getQuestion());
        question_3.setText(questions.get(2).getQuestion());
        question_4.setText(questions.get(3).getQuestion());
    }

    void initAnswers(ArrayList<KnlgAnswers> answers) {
        //записываем в обратном порядке
        answer_1_1.setText(answers.get(0).getAnswer_text());
        answer_2_1.setText(answers.get(1).getAnswer_text());
        answer_3_1.setText(answers.get(2).getAnswer_text());
        answer_4_1.setText(answers.get(3).getAnswer_text());

        answer_1_2.setText(answers.get(4).getAnswer_text());
        answer_2_2.setText(answers.get(5).getAnswer_text());
        answer_3_2.setText(answers.get(6).getAnswer_text());
        answer_4_2.setText(answers.get(7).getAnswer_text());

        answer_1_3.setText(answers.get(8).getAnswer_text());
        answer_2_3.setText(answers.get(9).getAnswer_text());
        answer_3_3.setText(answers.get(10).getAnswer_text());
        answer_4_3.setText(answers.get(11).getAnswer_text());

        answer_1_4.setText(answers.get(12).getAnswer_text());
        answer_2_4.setText(answers.get(13).getAnswer_text());
        answer_3_4.setText(answers.get(14).getAnswer_text());
        answer_4_4.setText(answers.get(15).getAnswer_text());
    }

    void checkAnswers() {
        String correctAnswer_1 = correctAnswers.get(0).getAnswer_text();
        String correctAnswer_2 = correctAnswers.get(1).getAnswer_text();
        String correctAnswer_3 = correctAnswers.get(2).getAnswer_text();
        String correctAnswer_4 = correctAnswers.get(3).getAnswer_text();

        if (corans_1_1.isSelected()) {
            if (answer_1_1.getText().equals(correctAnswer_1)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_2_1.isSelected()) {
            if (answer_2_1.getText().equals(correctAnswer_1)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_3_1.isSelected()) {
            if (answer_3_1.getText().equals(correctAnswer_1)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_4_1.isSelected()) {
            if (answer_4_1.getText().equals(correctAnswer_1)) {
                numberOfCorrectAnswers++;
            }
        }

        if (corans_1_2.isSelected()) {
            if (answer_1_2.getText().equals(correctAnswer_2)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_2_2.isSelected()) {
            if (answer_2_2.getText().equals(correctAnswer_2)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_3_2.isSelected()) {
            if (answer_3_2.getText().equals(correctAnswer_2)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_4_2.isSelected()) {
            if (answer_4_2.getText().equals(correctAnswer_2)) {
                numberOfCorrectAnswers++;
            }
        }

        if (corans_1_3.isSelected()) {
            if (answer_1_3.getText().equals(correctAnswer_3)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_2_3.isSelected()) {
            if (answer_2_3.getText().equals(correctAnswer_3)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_3_3.isSelected()) {
            if (answer_3_3.getText().equals(correctAnswer_3)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_4_3.isSelected()) {
            if (answer_4_3.getText().equals(correctAnswer_3)) {
                numberOfCorrectAnswers++;
            }
        }

        if (corans_1_4.isSelected()) {
            if (answer_1_4.getText().equals(correctAnswer_4)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_2_4.isSelected()) {
            if (answer_2_4.getText().equals(correctAnswer_4)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_3_4.isSelected()) {
            if (answer_3_4.getText().equals(correctAnswer_4)) {
                numberOfCorrectAnswers++;
            }
        }
        if (corans_4_4.isSelected()) {
            if (answer_4_4.getText().equals(correctAnswer_4)) {
                numberOfCorrectAnswers++;
            }
        }

    }

    void getTestResult() {
        if (numberOfCorrectAnswers >= 3) {
            DialogUtils.showOk("Тест успешно пройден! ", "Успех!");
            testResult = "Успешно пройден";
        } else {
            DialogUtils.showError("Тест успешно завален!", "Неудача!");
            testResult = "Завален";
        }
    }

    boolean isAnswersSelected() {
        if (!(corans_1_1.isSelected() || corans_2_1.isSelected() || corans_3_1.isSelected() || corans_4_1.isSelected())) {
            DialogUtils.showError("Вы не выбрали ответ в первом вопросе!", "Ошибка!");
            return false;
        }
        if (!(corans_1_2.isSelected() || corans_2_2.isSelected() || corans_3_2.isSelected() || corans_4_2.isSelected())) {
            DialogUtils.showError("Вы не выбрали ответ во втором вопросе!", "Ошибка!");
            return false;
        }
        if (!(corans_1_3.isSelected() || corans_2_3.isSelected() || corans_3_3.isSelected() || corans_4_3.isSelected())) {
            DialogUtils.showError("Вы не выбрали ответ в третьем вопросе!", "Ошибка!");
            return false;
        }
        if (!(corans_1_4.isSelected() || corans_2_4.isSelected() || corans_3_4.isSelected() || corans_4_4.isSelected())) {
            DialogUtils.showError("Вы не выбрали ответ в четвертом вопросе!", "Ошибка!");
            return false;
        }
        return true;
    }

    ArrayList<KnlgAnswers> getCorrectAnswers(ArrayList<KnlgAnswers> answers) {
        ArrayList<KnlgAnswers> correctAnswers = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).is_correct()) correctAnswers.add(answers.get(i));
        }
        return correctAnswers;
    }


    //todo -------------------------------
}
