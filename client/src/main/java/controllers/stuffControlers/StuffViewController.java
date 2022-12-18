package controllers.stuffControlers;

import controllers.LoginController;
import dataHolders.StuffDataHolder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StuffViewController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.setText("Дратути, " +
                StuffDataHolder.getInstance().getStuff().getName() + " " +
                StuffDataHolder.getInstance().getStuff().getSurname());
    }
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuffViews/stuff-view.fxml", "Главное меню");
    }

    //region Кнопачки
    @FXML
    private Button checkResultsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label helloLabel;

    @FXML
    private Button takeTestBtn;
    //endregion

    @FXML
    void onTakeTestBtn() throws IOException {
        new ChooseTestController().show((Stage) takeTestBtn.getScene().getWindow());

    }
    @FXML
    void onCheckResultsBtn() throws IOException {
        new CheckResultsController().show((Stage) checkResultsBtn.getScene().getWindow());
    }
    @FXML
    void onExitBtn() throws IOException {
        new LoginController().show((Stage) exitBtn.getScene().getWindow());
    }


}
