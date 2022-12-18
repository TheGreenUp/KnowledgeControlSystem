package controllers.adminControllers;

import entity.KnlgAnswers;
import entity.KnlgQuestions;
import entity.KnlgThemes;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.stage.Stage;
import response.createTest.CreateAnswersResponse;
import response.createTest.CreateQuestionResponse;
import response.createTest.CreateThemeResponse;
import service.TestCreationService;
import utils.DialogUtils;
import utils.ViewUtils;

import java.io.IOException;
import java.util.ArrayList;

public class CreateTestsController {


    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "adminViews/create-test-view.fxml", "Создание теста");
    }

    //region Переменные
    //region Ответы
    @FXML
    private TextField answerFiled_1_1;

    @FXML
    private TextField answerFiled_1_2;

    @FXML
    private TextField answerFiled_1_3;

    @FXML
    private TextField answerFiled_1_4;

    @FXML
    private TextField answerFiled_2_1;

    @FXML
    private TextField answerFiled_2_2;

    @FXML
    private TextField answerFiled_2_3;

    @FXML
    private TextField answerFiled_2_4;

    @FXML
    private TextField answerFiled_3_1;

    @FXML
    private TextField answerFiled_3_2;

    @FXML
    private TextField answerFiled_3_3;

    @FXML
    private TextField answerFiled_3_4;

    @FXML
    private TextField answerFiled_4_1;

    @FXML
    private TextField answerFiled_4_2;

    @FXML
    private TextField answerFiled_4_3;

    @FXML
    private TextField answerFiled_4_4;
    //endregion

    //region Кнопачки
    @FXML
    private Button backBtn;
    @FXML
    private Button createTestBtn;
    //endregion

    //region Правильный ответ
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

    //region Группы ответов
    @FXML
    private ToggleGroup quesionGroup_1;

    @FXML
    private ToggleGroup quesionGroup_2;

    @FXML
    private ToggleGroup quesionGroup_3;

    @FXML
    private ToggleGroup quesionGroup_4;
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

    //region Вопросы
    @FXML
    private TextArea questionText_1;

    @FXML
    private TextArea questionText_2;

    @FXML
    private TextArea questionText_3;

    @FXML
    private TextArea questionText_4;
    //endregion

    @FXML
    private TextField themeText;
    @FXML
    private TextArea themeDescription;
    //endregion

    //region Селект табок
    @FXML
    void onQuestionTabSelected_1() {

    }

    @FXML
    void onQuestionTabSelected_2() {

    }

    @FXML
    void onQuestionTabSelected_3() {

    }

    @FXML
    void onQuestionTabSelected_4() {

    }
    //endregion

    //region Постановка правильного ответа
    @FXML
    void setCorrectAnswer_1() {
        if (corans_1_1.isSelected()) {
            correctAnswer_1 = corans_1_1.getText();
        }
        if (corans_2_1.isSelected()) {
            correctAnswer_1 = corans_2_1.getText();
        }
        if (corans_3_1.isSelected()) {
            correctAnswer_1 = corans_3_1.getText();
        }
        if (corans_4_1.isSelected()) {
            correctAnswer_1 = corans_4_1.getText();
        }
    }

    @FXML
    void setCorrectAnswer_2() {
        if (corans_1_2.isSelected()) {
            correctAnswer_2 = corans_1_2.getText();
        }
        if (corans_2_2.isSelected()) {
            correctAnswer_2 = corans_2_2.getText();
        }
        if (corans_3_2.isSelected()) {
            correctAnswer_2 = corans_3_2.getText();
        }
        if (corans_4_2.isSelected()) {
            correctAnswer_2 = corans_4_2.getText();
        }
    }

    @FXML
    void setCorrectAnswer_3() {
        if (corans_1_3.isSelected()) {
            correctAnswer_3 = corans_1_3.getText();
        }
        if (corans_2_3.isSelected()) {
            correctAnswer_3 = corans_2_3.getText();
        }
        if (corans_3_3.isSelected()) {
            correctAnswer_3 = corans_3_3.getText();
        }
        if (corans_4_3.isSelected()) {
            correctAnswer_3 = corans_3_3.getText();
        }
    }

    @FXML
    void setCorrectAnswer_4() {
        if (corans_1_4.isSelected()) {
            correctAnswer_4 = corans_1_4.getText();
        }
        if (corans_2_4.isSelected()) {
            correctAnswer_4 = corans_2_4.getText();
        }
        if (corans_3_4.isSelected()) {
            correctAnswer_4 = corans_3_4.getText();
        }
        if (corans_4_4.isSelected()) {
            correctAnswer_4 = corans_4_4.getText();
        }
    }
    //endregion

    static String correctAnswer_1;
    static String correctAnswer_2;
    static String correctAnswer_3;
    static String correctAnswer_4;

    @FXML
    void onCreateTestBtn() throws IOException {
        if (TestCreationService.createTheme(
                themeText.getText(), themeDescription.getText()) instanceof CreateThemeResponse) {
           KnlgThemes theme = TestCreationService.getTheme(new KnlgThemes(themeText.getText(), themeDescription.getText()));
          if (TestCreationService.createQuestions(initQuestions(theme)) instanceof CreateQuestionResponse) {
              ArrayList<KnlgQuestions> questions = TestCreationService.getQuestions(initQuestions(theme));
              ArrayList<KnlgAnswers> answers = initAnswers(questions);
                if (TestCreationService.createAnswers(answers) instanceof CreateAnswersResponse) {
                    DialogUtils.showOk("Тест успешно добавлен!", "Успех!");
                    onBackBtn();
                }
          };
        }
    }

    @FXML
    void onBackBtn() throws IOException {
        new AdminViewController().show((Stage) backBtn.getScene().getWindow());
    }

    private ArrayList<KnlgQuestions> initQuestions(KnlgThemes theme){
        KnlgQuestions question_1 = new KnlgQuestions(theme.getId(),questionText_1.getText());
        KnlgQuestions question_2 = new KnlgQuestions(theme.getId(),questionText_2.getText());
        KnlgQuestions question_3 = new KnlgQuestions(theme.getId(),questionText_3.getText());
        KnlgQuestions question_4 = new KnlgQuestions(theme.getId(),questionText_4.getText());

        ArrayList<KnlgQuestions> questions = new ArrayList<>();

        questions.add(question_1);
        questions.add(question_2);
        questions.add(question_3);
        questions.add(question_4);
        return questions;
    }

    private ArrayList<KnlgAnswers> initAnswers(ArrayList<KnlgQuestions> questions){
        KnlgAnswers question_1_1 = new KnlgAnswers(questions.get(0).getId(),answerFiled_1_1.getText(),corans_1_1.isSelected());
        KnlgAnswers question_2_1 = new KnlgAnswers(questions.get(0).getId(), answerFiled_2_1.getText(),corans_2_1.isSelected());
        KnlgAnswers question_3_1 = new KnlgAnswers(questions.get(0).getId(), answerFiled_3_1.getText(),corans_3_1.isSelected());
        KnlgAnswers question_4_1 = new KnlgAnswers(questions.get(0).getId(), answerFiled_4_1.getText(),corans_4_1.isSelected());

        KnlgAnswers question_1_2 = new KnlgAnswers(questions.get(1).getId(),answerFiled_1_2.getText(),corans_1_2.isSelected());
        KnlgAnswers question_2_2 = new KnlgAnswers(questions.get(1).getId(), answerFiled_2_2.getText(),corans_2_2.isSelected());
        KnlgAnswers question_3_2 = new KnlgAnswers(questions.get(1).getId(), answerFiled_3_2.getText(),corans_3_2.isSelected());
        KnlgAnswers question_4_2 = new KnlgAnswers(questions.get(1).getId(), answerFiled_4_2.getText(),corans_4_2.isSelected());

        KnlgAnswers question_1_3 = new KnlgAnswers(questions.get(2).getId(),answerFiled_1_3.getText(),corans_1_3.isSelected());
        KnlgAnswers question_2_3 = new KnlgAnswers(questions.get(2).getId(), answerFiled_2_3.getText(),corans_2_3.isSelected());
        KnlgAnswers question_3_3 = new KnlgAnswers(questions.get(2).getId(), answerFiled_3_3.getText(),corans_3_3.isSelected());
        KnlgAnswers question_4_3 = new KnlgAnswers(questions.get(2).getId(), answerFiled_4_3.getText(),corans_4_3.isSelected());

        KnlgAnswers question_1_4 = new KnlgAnswers(questions.get(3).getId(),answerFiled_1_4.getText(),corans_1_4.isSelected());
        KnlgAnswers question_2_4 = new KnlgAnswers(questions.get(3).getId(), answerFiled_2_4.getText(),corans_2_4.isSelected());
        KnlgAnswers question_3_4 = new KnlgAnswers(questions.get(3).getId(), answerFiled_3_4.getText(),corans_3_4.isSelected());
        KnlgAnswers question_4_4 = new KnlgAnswers(questions.get(3).getId(), answerFiled_4_4.getText(),corans_4_4.isSelected());

        ArrayList<KnlgAnswers> answers = new ArrayList<>();

        answers.add(question_1_1);
        answers.add(question_2_1);
        answers.add(question_3_1);
        answers.add(question_4_1);


        answers.add(question_1_2);
        answers.add(question_2_2);
        answers.add(question_3_2);
        answers.add(question_4_2);


        answers.add(question_1_3);
        answers.add(question_2_3);
        answers.add(question_3_3);
        answers.add(question_4_3);


        answers.add(question_1_4);
        answers.add(question_2_4);
        answers.add(question_3_4);
        answers.add(question_4_4);

        return answers;

    }


}
