package start;

import controllers.adminControllers.AdminViewController;
import dataHolders.StuffDataHolder;
import entity.Stuff;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.ViewUtils;

import java.io.IOException;

public class ClientApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//         StuffDataHolder.getInstance().setStuff(
//                new Stuff(0,0,"Admin", "Admin", "da", "123"));
//        StuffDataHolder.getInstance().setStuff(
//                new Stuff(0,1,"Stuff", "Stuffovich", "net", "123"));
       // ViewUtils.loadView(stage, "stuffViews/stuff-view.fxml", "Клиенто");
        ViewUtils.loadView(stage, "hello-view.fxml", "Клиенто");

       // ViewUtils.loadView(stage, "hello-view.fxml", "Добро пожаловать");

    }

    public static void main(String[] args) {
        launch();
    }
}