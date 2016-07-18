package com.saiton.ccs.sales;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.salesdao.ServiceDAO;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextAreaValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

public class ServiceController implements Initializable, Validatable,
        StagePassable {

    //<editor-fold defaultstate="collapsed" desc="Initcomponent">
    @FXML
    private Button btnClose;

    @FXML
    private Button btnServiceSearch;

    @FXML
    private TableView<?> tblItemList;

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
    private TextField txtUserName;

    private final ValidationSupport validationSupportTableData
            = new ValidationSupport();
    private final ValidationSupport validationSupportTable
            = new ValidationSupport();

    private MessageBox mb;
    boolean isupdate = false;
    int no = 1;

    Item item = new Item();
    private ObservableList TableItemData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Item, String> tcServiceId;
    @FXML
    private TableColumn<Item, String> tcServiceName;
    @FXML
    private TableColumn<Item, String> tcServicePrice;
    @FXML
    private TableColumn<Item, String> tcServiceDescription;

    private final FormatAndValidate fav = new FormatAndValidate();
    
//    int no = 1;

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Key Events">
    @FXML
    void txtItemNameOnKeyReleased(KeyEvent event) {
        validatorInitialization();
    }

    @FXML
    void txtSellingPriceOnKeyReleased(KeyEvent event) {

        validatorInitialization();
        boolean validationSupportResult = false;
        boolean isAvalible = false;

        if (event.getCode() == KeyCode.ENTER) {

            ValidationResult v = validationSupportTableData.
                    getValidationResult();
            if (v != null) {

                validationSupportResult = validationSupportTableData.isInvalid();
                if (validationSupportResult == true) {
                    mb.ShowMessage(stage, ErrorMessages.MandatoryError,
                            "Error",
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);

                } else if (validationSupportResult == false) {

                    if (isupdate == false) {

                        isAvalible = serviceDAO.checkingItemNameAvailability(
                                txtService.getText()
                        );

                        if (isAvalible == false) {
                            if (tblItemList.getItems().size() != 0) {
                                int n = tblItemList.getItems().size();
                                for (int s = 0; s < n; s++) {

                                    item = (Item) tblItemList.getItems().get(s);

                                    if ((txtServiceId.getText()).equals(
                                            (item.getColServiceId()))
                                            && tblItemList.getItems().size() > 0) {
                                        TableItemData.remove(s);
                                        n--;

                                    }

                                    if (txtServiceId.getText().equals(
                                            item.getColServiceId())
                                            && tblItemList.getItems().size() > 0) {
                                        if (!item.getColServiceName().equals(
                                                txtService.getText())) {
                                            item.setColServiceName(
                                                    txtService.getText());
                                        }
                                    }

                                }
                            }

                            item = new Item();

                            item.colServiceId.setValue(txtServiceId.getText());
                            item.colServiceName.setValue(txtService.getText());
                            item.colServicePrice.setValue(txtPrice.
                                    getText());
                            item.colServiceDescription.setValue(txtDescription.
                                    getText());

                            TableItemData.add(item);

                            no = no + 1;
                            txtServiceId.setText(serviceDAO.generateIDOOnDemand(
                                    no));

                            //Clear item components for new entry
                            clearComponentsForNewEntry();
//                            txtItemId.setText(itemDAO.generateID());

                        } else {
                            mb.ShowMessage(stage,
                                    ErrorMessages.InvalidItemName,
                                    "Error",
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }

                    } else {
                        if (tblItemList.getItems().size() != 0) {
                            //Removing existing item for update or new addition
                            int n = tblItemList.getItems().size();
                            for (int s = 0; s < n; s++) {
                                item = (Item) tblItemList.getItems().get(s);
                                if ((txtServiceId.getText()).
                                        equals(
                                                (item.getColServiceId()))
                                        && tblItemList.getItems().size() > 0) {
                                    TableItemData.remove(s);
                                    n--;
                                }

                                if (txtServiceId.getText().equals(
                                        item.getColServiceId())
                                        && tblItemList.getItems().size() > 0) {
                                    if (!item.getColServiceName().equals(
                                            txtService.getText())) {
                                        item.colServiceName.setValue(
                                                txtService.getText());
                                    }
                                }
                            }
                        }
                        //Adding items to the table
                        item = new Item();
                        item.colServiceId.setValue(txtServiceId.getText());

                        TableItemData.add(item);

                        //Resetting fields for next item
                        txtServiceId.setText(serviceDAO.generateID());

                        clearComponentsForNewEntry();

                        update = true;
                        btnSave.setVisible(true);

                    }

                }
            }
        }
        validatorInitialization();

    }

    @FXML
    private void txtItemDescOnKeyReleased(KeyEvent event) {
        validatorInitialization();
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

        validatorInitialization();
        boolean validationSupportTableResult = false;
        boolean isTableContentSaved = false;
        Item itemTable;

        ValidationResult v = validationSupportTable.getValidationResult();

        if (v != null) {
            validationSupportTableResult = validationSupportTable.isInvalid();

            if (validationSupportTableResult == true) {
                
                mb.ShowMessage(stage, ErrorMessages.MandatoryError,
                        "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            } else if (validationSupportTableResult == false) {
                
                if (isupdate == false) {

                    if (tblItemList.getItems().size() != 0) {
                        for (int i = 0; i < tblItemList.getItems().size(); i++) {
                            itemTable = (Item) tblItemList.getItems().get(i);

                            isTableContentSaved = serviceDAO.addServiceItem(
                                    itemTable.getColServiceId(),
                                    itemTable.getColServiceName(),
                                    itemTable.getColServiceDescription(),
                                    Double.parseDouble(itemTable.
                                            getColServicePrice()),
                                    userId);

                        }
                    }

                    if (isTableContentSaved == true) {

                        mb.ShowMessage(stage,
                                ErrorMessages.SuccesfullyCreated, "Information",
                                MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                MessageBox.MessageType.MSG_OK);
                        clearInput();

                    } else {
                        mb.ShowMessage(stage,
                                ErrorMessages.NotSuccesfullyCreated, "Error",
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);
                    }
                    //Save Action Event
                } else {

                    MessageBox.MessageOutput option = mb.ShowMessage(stage,
                            ErrorMessages.Update, "Information",
                            MessageBox.MessageIcon.MSG_ICON_NONE,
                            MessageBox.MessageType.MSG_YESNO);
                    if (option.equals(MessageBox.MessageOutput.MSG_YES)) {

                        if (tblItemList.getItems().size() != 0) {
                            for (int i = 0; i < tblItemList.getItems().size();
                                    i++) {
                                itemTable = (Item) tblItemList.getItems().get(i);

                                isTableContentSaved = serviceDAO.addServiceItem(
                                        itemTable.getColServiceId(),
                                        itemTable.getColServiceName(),
                                        itemTable.getColServiceDescription(),
                                        Double.parseDouble(itemTable.
                                                getColServicePrice()),
                                        userId);

                            }
                        }
                        if (isTableContentSaved == true) {

                            mb.ShowMessage(this.stage,
                                    ErrorMessages.SuccesfullyUpdated,
                                    "Information",
                                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                    MessageBox.MessageType.MSG_OK);
                            clearInput();

                        } else {
                            mb.ShowMessage(stage,
                                    ErrorMessages.NotSuccesfullyUpdated, "Error",
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }
                    }
                }
            }
        }

    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
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

        tcServiceId.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colServiceId"));
        tcServiceName.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colServiceName"));
        tcServicePrice.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colServicePrice"));
        tcServiceDescription.setCellValueFactory(
                new PropertyValueFactory<Item, String>(
                        "colServiceDescription"));

        tblItemList.setItems(TableItemData);

        mb = SimpleMessageBoxFactory.createMessageBox();
        txtServiceId.setText(serviceDAO.generateID());

        btnDelete.setVisible(false);

    }

    @Override
    public boolean isValid() {

        return true;

    }

    @Override
    public void clearInput() {
            txtDescription.clear();
            txtPrice.clear();
            txtService.clear();
            txtServiceId.clear();
            TableItemData.clear();
            txtServiceId.setText(serviceDAO.generateID());
            
    }

    private void clearComponentsForNewEntry() {

        txtDescription.clear();
        txtPrice.clear();
        txtService.clear();
//        txtServiceId.clear();

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
        validatorInitialization();
    }

    private void validatorInitialization() {

        validationSupportTableData.registerValidator(txtService,
                new CustomTextAreaValidationImpl(txtService,
                        !fav.validName(txtService.getText()),
                        ErrorMessages.Error));

        validationSupportTableData.registerValidator(txtDescription,
                new CustomTextAreaValidationImpl(txtDescription,
                        !fav.validName(txtDescription.getText()),
                        ErrorMessages.Error));

        validationSupportTableData.registerValidator(txtDescription,
                new CustomTextAreaValidationImpl(txtDescription,
                        !fav.validName(txtDescription.getText()),
                        ErrorMessages.Error));

        validationSupportTableData.registerValidator(txtPrice,
                new CustomTextFieldValidationImpl(txtPrice,
                        !fav.chkPrice(txtPrice.getText()),
                        ErrorMessages.InvalidPrice));
        
        validationSupportTable.registerValidator(tblItemList,
                new CustomTableViewValidationImpl(tblItemList,
                        !fav.validTableView(tblItemList),
                        ErrorMessages.EmptyListView));

    }

    public class Item {

        public SimpleStringProperty colServiceId = new SimpleStringProperty(
                "tcServiceId");
        public SimpleStringProperty colServiceName = new SimpleStringProperty(
                "tcServiceName");
        public SimpleStringProperty colServicePrice
                = new SimpleStringProperty(
                        "tcServicePrice");
        public SimpleStringProperty colServiceDescription
                = new SimpleStringProperty(
                        "tcServiceDescription");

        public String getColServiceId() {
            return colServiceId.get();
        }

        public String getColServiceName() {
            return colServiceName.get();
        }

        public String getColServicePrice() {
            return colServicePrice.get();
        }

        public String getColServiceDescription() {
            return colServiceDescription.get();
        }

        public void setColServiceName(String serviceName) {
            colServiceName.setValue(serviceName);
        }

    }

//</editor-fold>
}
