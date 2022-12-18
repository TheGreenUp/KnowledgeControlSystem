package controllers.adminControllers;

import javafx.stage.Stage;
import utils.ViewUtils;

import java.io.IOException;

public class AnalyticsController {
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "adminViews/analytics-view.fxml", "Аналитика");
    }
}
