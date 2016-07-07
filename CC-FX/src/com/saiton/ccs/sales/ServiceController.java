package com.saiton.ccs.sales;

import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ServiceController  implements Initializable, Validatable,
        StagePassable{

    @FXML
    private Button btnClose;

    @FXML
    private TableColumn<?, ?> tcItemId;

    @FXML
    private TableColumn<?, ?> tcItemName;

    @FXML
    private Button btnServiceSearch;

    @FXML
    private TableView<?> tblItemList;

    @FXML
    private TableColumn<?, ?> tcItemDescripton;

    @FXML
    private Label lblItemId;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtServiceId;

    @FXML
    private TextArea txtService;

    @FXML
    private TextField txtPrice;
    
    private Stage stage;

    @FXML
    void txtItemNameOnKeyReleased(ActionEvent event) {

    }

    @FXML
    void btnServiceSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSellingPriceOnKeyReleased(ActionEvent event) {

    }

    @FXML
    void tblRequestNoteListOnMouseClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {

    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @Override
    public boolean isValid() {
        
        return true;
        
    }

    @Override
    public void clearInput() {
        
    }

    @Override
    public void clearValidations() {
        
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {
    this.stage = stage;
        
    }

}
