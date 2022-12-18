package controllers;

import controllers.adminControllers.AdminViewController;
import controllers.stuffControlers.StuffViewController;
import dataHolders.StuffDataHolder;
import entity.Stuff;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import command.LoginCommand;
import lombok.extern.slf4j.Slf4j;
import response.ErrorResponse;
import response.LoginResponse;
import start.Client;
import utils.DialogUtils;
import utils.ViewUtils;

import java.io.IOException;

@Slf4j
public class LoginController {
    @FXML
    private TextField emailField;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button regBtn;

    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "hello-view.fxml", "Вход в систему");
    }

    @FXML
    void onLoginBtn() throws IOException {
        LoginCommand loginCommand = new LoginCommand(emailField.getText(), passwordField.getText());
        Client.writeObject(loginCommand);
        Object response = Client.readObject();
        if (response instanceof LoginResponse) {
            Stuff stuff = ((LoginResponse) response).getStuff();
            StuffDataHolder.getInstance().setStuff(stuff);
            //todo roles as enum
            if (stuff.getRole() == 0) {
                new AdminViewController().show((Stage) loginBtn.getScene().getWindow());
            }
            else {
                new StuffViewController().show((Stage) loginBtn.getScene().getWindow());
            }
        } else if (response instanceof ErrorResponse) {
            DialogUtils.showError("Пользователя не существует", "Ошибка!");
            String errorMessage = ((ErrorResponse) response).getErrorMessage();
            log.error("Ошибка во время логина: {}", errorMessage);
        } else {
            DialogUtils.showError("Ошибка на сервере!", "Ошибка!");
            log.error("Unknown response {}", response);
        }
    }
}
