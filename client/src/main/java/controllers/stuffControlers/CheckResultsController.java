package controllers.stuffControlers;

import javafx.stage.Stage;
import utils.ViewUtils;

import java.io.IOException;

public class CheckResultsController {
    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuffViews/check-results-view.fxml", "Просмотр результатов");
    }
}
