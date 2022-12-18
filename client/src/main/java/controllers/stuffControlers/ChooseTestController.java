package controllers.stuffControlers;

import command.GetAllThemesCommand;
import dataHolders.ThemeDataHolder;
import entity.KnlgThemes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import response.ErrorResponse;
import response.GetAllThemesResponse;
import start.Client;
import utils.DialogUtils;
import utils.ViewUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseTestController implements Initializable {
    @FXML
    private TableColumn<KnlgThemes, String> columnTestDesc;

    @FXML
    private TableColumn<KnlgThemes, String> columnTestName;
    @FXML
    private TableColumn<KnlgThemes, Integer> columnTestId;

    @FXML
    private TableView<KnlgThemes> infoTable;


    @FXML
    private Button backBtn;

    @FXML
    private Button takeTestBtn;

    @FXML
    void onBackBtn() throws IOException {
        new StuffViewController().show((Stage) backBtn.getScene().getWindow());
    }

    @FXML
    void onTakeTestBtn() throws IOException {
        if (ThemeDataHolder.getInstance().getThemes() == null) {
            DialogUtils.showError("Не выбрана тема!", "Ошибка!");
            return;
        }
        new TakeTestController().show((Stage) takeTestBtn.getScene().getWindow());
    }

    public void show(Stage stage) throws IOException {
        ViewUtils.loadView(stage, "stuffViews/choose-test.fxml", "Тестирование");
    }

    ObservableList<KnlgThemes> themeList;
    int chosenThemeIndex = -1;


    @FXML
    void getSelected(MouseEvent mouseEvent) {
        chosenThemeIndex = infoTable.getSelectionModel().getSelectedIndex();
        if (chosenThemeIndex <= -1) {//проверка на то, выделил ли что-нибудь пользователь или нет
            return;
        }
        ThemeDataHolder.getInstance().setThemes(
                new KnlgThemes(columnTestId.getCellData(chosenThemeIndex),
                        columnTestName.getCellData(chosenThemeIndex),
                        columnTestDesc.getCellData(chosenThemeIndex)));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GetAllThemesCommand command = new GetAllThemesCommand();
        Client.writeObject(command);
        Object response = Client.readObject();
        if (response instanceof GetAllThemesResponse) {
            themeList = FXCollections.observableList((List<KnlgThemes>) ((GetAllThemesResponse) response).getThemes());
            setCellTable(themeList);
        } else if (response instanceof ErrorResponse) {
            DialogUtils.showError(((ErrorResponse) response).getErrorMessage(), "Ошибка!");
        } else {
            System.out.println("Unknown error");
        }
    }
    private void setCellTable(ObservableList<KnlgThemes> themes) {
        columnTestName.setCellValueFactory(new PropertyValueFactory<KnlgThemes, String>("themeText"));
        columnTestDesc.setCellValueFactory(new PropertyValueFactory<KnlgThemes, String>("description"));
        columnTestId.setCellValueFactory(new PropertyValueFactory<KnlgThemes, Integer>("id"));
        infoTable.setItems(themes);
    }
}
