package controllers.adminControllers;

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

public class AdminViewController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloLabel.setText("Здравствуйте, " + StuffDataHolder.getInstance().getStuff().getName() + " " + StuffDataHolder.getInstance().getStuff().getSurname());
    }
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "adminViews/admin-view.fxml", "Администрирование");
    }

    //region Кнопачки
    @FXML
    private Button exitBtn;
    @FXML
    private Label helloLabel;
    @FXML
    private Button analyticsBtn;

    @FXML
    private Button createTestBtn;

    @FXML
    private Button manageStuffBtn;
    //endregion
    @FXML
    void onManageStuffBtn() throws IOException {
        new ManageStuffController().show((Stage) manageStuffBtn.getScene().getWindow());
    }
    @FXML
    void onCreateTestBtn() throws IOException {
        new CreateTestsController().show((Stage) createTestBtn.getScene().getWindow());

    }

    @FXML
    void onAnalyticsBtn() throws IOException {
        new AnalyticsController().show((Stage) analyticsBtn.getScene().getWindow());
    }

    @FXML
    void onExitBtn() throws IOException {
        new LoginController().show((Stage) exitBtn.getScene().getWindow());
    }
}
