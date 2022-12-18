package controllers.adminControllers;

import command.stuffManagment.AddStuffCommand;
import command.stuffManagment.DeleteStuffCommand;
import command.stuffManagment.StuffListCommand;
import command.stuffManagment.UpdateStuffCommand;
import entity.Stuff;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import response.stuffManagment.AddStuffResponse;
import response.ErrorResponse;
import response.stuffManagment.DeleteStuffResponse;
import response.stuffManagment.StuffListResponse;
import response.stuffManagment.UpdateStuffResponse;
import start.Client;
import utils.DialogUtils;
import utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStuffController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        StuffListCommand stuffListCommand = new StuffListCommand();
        Client.writeObject(stuffListCommand);
        Object response = Client.readObject();
        if (response instanceof StuffListResponse) {
            stufflist = FXCollections.observableList((List<Stuff>) ((StuffListResponse) response).getStuff());
            setCellTable(stufflist);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }

    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "adminViews/manage-stuff-view.fxml", "Управление сотрудниками");
    }

    @FXML
    private ComboBox cbGetRoles;

    //region Таблица
    @FXML
    private TableView<Stuff> stuffTable;
    @FXML
    private TableColumn<Stuff, String> columnEmail;

    @FXML
    private TableColumn<Stuff, String> columnName;

    @FXML
    private TableColumn<Stuff, Integer> columnId;

    @FXML
    private TableColumn<Stuff, String> columnSurname;

    @FXML
    private TableColumn<Stuff, String> columnPassword;

    @FXML
    private TableColumn<Stuff, Integer> columnRole;
    //endregion

    //region Кнопачки
    @FXML
    private Button addStuffButton;
    @FXML
    private Button deleteStuffButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button updateButton;
    //endregion

    //region Текстовые поля
    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField password;
    //endregion

    ObservableList<Stuff> stufflist;
    int chosenStuffIndex = -1;
    int chosenStuffId = -1;

    @FXML
    void getSelected(MouseEvent event) {
        chosenStuffIndex = stuffTable.getSelectionModel().getSelectedIndex();
        if (chosenStuffIndex <= -1) {//проверка на то, выделил ли что-нибудь пользователь или нет
            return;
        }
        lname.setText(columnSurname.getCellData(chosenStuffIndex));
        fname.setText(columnName.getCellData(chosenStuffIndex));
        email.setText(columnEmail.getCellData(chosenStuffIndex));
        password.setText(columnPassword.getCellData(chosenStuffIndex));
        //todo
        // cbGetRoles.setValue(getStringRoleByComboBox ((int) columnRole.getCellData(chosenStuffIndex)));
        chosenStuffId = columnId.getCellData(chosenStuffIndex);
    }

    @FXML
    void onAddStuffButton() {
        AddStuffCommand addStuffCommand = new AddStuffCommand(getChosenStuff());
        Client.writeObject(addStuffCommand);
        Object response = Client.readObject();
        if (response instanceof AddStuffResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {
            DialogUtils.showAlert(((ErrorResponse) response).getErrorMessage(), "Ошибка!");
        } else {
            DialogUtils.showAlert("Неизвестная ошибка!", "Ошибка!");
        }
    }

    @FXML
    void onDeleteStuffButton() {
        if (!DialogUtils.deleteConfirmation()) return;
        DeleteStuffCommand deleteStuffCommand = new DeleteStuffCommand(getChosenStuff());
        Client.writeObject(deleteStuffCommand);
        Object response = Client.readObject();
        if (response instanceof DeleteStuffResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {
            DialogUtils.showError(((ErrorResponse) response).getErrorMessage(), "Ошибка!");
        } else {
            System.out.println("Unknown error");
        }
    }

    @FXML
    void onUpdateButton() {
        UpdateStuffCommand updateStuffCommand = new UpdateStuffCommand(
                Stuff.builder()
                        .id(chosenStuffId)
                        .email(email.getText())
                        .name(fname.getText())
                        .surname(lname.getText())
                        .password(password.getText())
                        .role(1)//todo pass role
                        .build());
        Client.writeObject(updateStuffCommand);
        Object response = Client.readObject();
        if (response instanceof UpdateStuffResponse) {
            refreshTable();
        } else if (response instanceof ErrorResponse) {

        } else {
            //просто ошибка
        }
    }
    @FXML
    void onExitButton() throws IOException {
        new AdminViewController().show((Stage) exitButton.getScene().getWindow());
    }

    private void setCellTable(ObservableList<Stuff> stufflist) {
        columnId.setCellValueFactory(new PropertyValueFactory<Stuff, Integer>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<Stuff, String>("name"));
        columnSurname.setCellValueFactory(new PropertyValueFactory<Stuff, String>("surname"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Stuff, String>("email"));
        columnPassword.setCellValueFactory(new PropertyValueFactory<Stuff, String>("password"));
        columnRole.setCellValueFactory(new PropertyValueFactory<Stuff, Integer>("role"));
        stuffTable.setItems(stufflist);
    }

    private void refreshTable() {
        StuffListCommand stuffListCommand = new StuffListCommand();
        Client.writeObject(stuffListCommand);
        Object response = Client.readObject();
        if (response instanceof StuffListResponse) {
            stufflist = FXCollections.observableList((List<Stuff>) ((StuffListResponse) response).getStuff());
            setCellTable(stufflist);
        } else if (response instanceof ErrorResponse) {

        } else {
            System.out.println("Unknown error");
        }
    }
    private Stuff getChosenStuff(){
        return Stuff.builder()
                .id(chosenStuffId)
                .email(email.getText())
                .name(fname.getText())
                .surname(lname.getText())
                .password(password.getText())
                .role(1)//todo pass role
                .build();
    }

}
