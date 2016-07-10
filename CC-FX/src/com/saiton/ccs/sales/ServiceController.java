package com.saiton.ccs.sales;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.salesdao.ServiceDAO;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServiceController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
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
    
    ServiceDAO serviceDAO = new ServiceDAO();

    private Stage stage;

    private String userId;
    private String userName;
    private String userCategory;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean view = false;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableColumn<?, ?> tcItemPrice;
    @FXML
    private TextField txtUserName;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Key Events">
    void txtItemNameOnKeyReleased(ActionEvent event) {

    }

    void txtSellingPriceOnKeyReleased(ActionEvent event) {

    }

    @FXML
    private void txtItemNameOnKeyReleased(KeyEvent event) {
    }

    @FXML
    private void txtSellingPriceOnKeyReleased(KeyEvent event) {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Action Events">
    @FXML
    private void btnServiceSearchOnAction(ActionEvent event) {

    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {

    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {

    }

  

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click Events">
    void tblRequestNoteListOnMouseClicked(ActionEvent event) {

    }
    
      @FXML
    private void tblRequestNoteListOnMouseClicked(MouseEvent event) {
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
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

     private void setUserAccessLevel() {

        userId = UserSession.getInstance().getUserInfo().getEid();

        userName = UserSession.getInstance().getUserInfo().getName();
        userCategory = UserSession.getInstance().getUserInfo().getCategory();
        txtUserName.setText(userName);

        String title = stage.getTitle();

        if (!title.isEmpty()) {

            UserPermission userPermission = UserSession.getInstance().
                    findPermission(title);

            if (userPermission.canInsert() == true) {
                insert = true;
            }

            if (userPermission.canDelete() == true) {
                delete = true;
            }

            if (userPermission.canUpdate() == true) {
                update = true;
            }

            if (userPermission.canView() == true) {
                view = true;
            }

            if (insert == true && delete == true && update == true && view
                    == true) {
                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == false && delete == true && update == true
                    && view
                    == true) {
                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == true && delete == false && update == true
                    && view
                    == true) {
                setUiMode(UiMode.ALL_BUT_DELETE);

            } else if (insert == true && delete == true && update == false
                    && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == true && delete == true && update == true
                    && view
                    == false) {
                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == true
                    && view
                    == true) {

                setUiMode(UiMode.FULL_ACCESS);

            } else if (insert == false && delete == true && update == false
                    && view
                    == true) {
                setUiMode(UiMode.DELETE);

            } else if (insert == false && delete == true && update == true
                    && view
                    == false) {
                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == true && delete == false && update == false
                    && view
                    == true) {

                setUiMode(UiMode.ALL_BUT_DELETE);

            } else if (insert == true && delete == false && update == true
                    && view
                    == false) {
                setUiMode(UiMode.SAVE);

            } else if (insert == true && delete == true && update == false
                    && view
                    == false) {
                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == false
                    && view
                    == true) {

                setUiMode(UiMode.READ_ONLY);

            } else if (insert == false && delete == false && update == true
                    && view
                    == false) {
                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == false && delete == true && update == false
                    && view
                    == false) {
                setUiMode(UiMode.NO_ACCESS);

            } else if (insert == true && delete == false && update == false
                    && view
                    == false) {
                setUiMode(UiMode.SAVE);

            } else if (insert == false && delete == false && update == false
                    && view
                    == false) {
                setUiMode(UiMode.NO_ACCESS);

            }
        } else {

            setUiMode(UiMode.NO_ACCESS);

        }

    }

    private void setUiMode(UiMode uiMode) {

        switch (uiMode) {

            case SAVE:
                disableUi(false);
                deactivateSearch();

                btnDelete.setVisible(false);

                break;

            case DELETE:
                disableUi(false);

                btnSave.setDisable(true);
                btnSave.setVisible(false);

                deactivateCombo();

                break;

            case READ_ONLY:
                disableUi(false);
                deactivateCombo();
                btnDelete.setVisible(false);

                btnSave.setDisable(true);
                btnSave.setVisible(false);

                break;

            case ALL_BUT_DELETE:
                disableUi(false);

                btnDelete.setVisible(false);

                break;

            case FULL_ACCESS:
                disableUi(false);
                btnDelete.setVisible(false);
                break;

            case NO_ACCESS:
                disableUi(true);

                break;

        }

    }

    private void disableUi(boolean state) {

        tblItemList.setDisable(state);
        tblItemList.setVisible(!state);

        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);

        btnSave.setDisable(state);
        btnSave.setVisible(!state);

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
    }

    private void deactivateSearch() {

//        componentControl.deactivateSearch(lblItemName, txtItemName,
//                btnItemNameSearch,
//                220.00, 0.00);
    }

    private void deactivateCombo() {
//        componentControl.controlCComboBox(lblItemId1, cmbBatchNo, btnBatchNo,
//                220.00, 0.0, true);
    }
    
    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stage = stage;
        setUserAccessLevel();
    }

//</editor-fold>
   

    
}
