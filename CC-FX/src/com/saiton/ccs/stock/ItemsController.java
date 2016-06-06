/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saiton.ccs.stock;

import com.saiton.ccs.base.UserPermission;
import com.saiton.ccs.base.UserSession;
import com.saiton.ccs.msgbox.MessageBox;
import com.saiton.ccs.msgbox.SimpleMessageBoxFactory;
import com.saiton.ccs.popup.ItemInfoPopup;
import com.saiton.ccs.stockdao.ItemDAO;
import com.saiton.ccs.uihandle.ComponentControl;
import com.saiton.ccs.uihandle.StagePassable;
import com.saiton.ccs.uihandle.UiMode;
import com.saiton.ccs.validations.CustomComboboxValidationImpl;
import com.saiton.ccs.validations.CustomTableViewValidationImpl;
import com.saiton.ccs.validations.CustomTextFieldValidationImpl;
import com.saiton.ccs.validations.ErrorMessages;
import com.saiton.ccs.validations.FormatAndValidate;
import com.saiton.ccs.validations.Validatable;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.PopOver;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author MewanZ
 */
public class ItemsController extends AnchorPane implements Initializable,
        Validatable, StagePassable {

    //<editor-fold defaultstate="collapsed" desc="initcomponents">
    @FXML
    private TableColumn<Item, String> tcPrice;

    @FXML
    private Label lblItemId1;

    @FXML
    private Button btnRefresh;

    @FXML
    private Button btnItemNameSearch;

    @FXML
    private TableColumn<Item, String> tcItemName;

    @FXML
    private TableView<Item> tblItemList;

    @FXML
    private Button btnDelete;

    @FXML
    private TableColumn<Item, String> tcBatchNo;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtItemName;

    @FXML
    private Button btnClose;

    @FXML
    private TextField txtUserId;

    @FXML
    private TableColumn<Item, String> tcItemId;

    @FXML
    private Label lblItemId;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblItemId11;

    @FXML
    private TextField txtPrice;

    @FXML
    private Label lblRequestId;

    @FXML
    private ComboBox<String> cmbBatchNo;

    @FXML
    private Button btnBatchNo;

    @FXML
    private Label lblItemName;

    //</editor-fold> 
    private final ValidationSupport validationSupportTableData
            = new ValidationSupport();
    private final ValidationSupport validationSupportTable
            = new ValidationSupport();
    private final FormatAndValidate fav = new FormatAndValidate();
    private MessageBox mb;
    private Stage stageExtra;
    Item item = new Item();
    private ObservableList TableItemData = FXCollections.observableArrayList();
    ItemDAO itemDAO = new ItemDAO();
    boolean isupdate = false;
    int no = 1;

    private String userId;
    private String userName;
    private String userCategory;
    private boolean insert = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean view = false;

    private ComponentControl componentControl = new ComponentControl();

    //item info popup---------------------------------------
    private TableView itemTable = new TableView();
    private PopOver itemPop;
    private ObservableList<ItemInfoPopup> itemData = FXCollections.
            observableArrayList();
    private ItemInfoPopup itemPopup = new ItemInfoPopup();
    ObservableList<String> batchNoList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcItemId.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colItemId"));
        tcItemName.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colItemName"));
        tcBatchNo.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colBatchNo"));
        tcPrice.setCellValueFactory(new PropertyValueFactory<Item, String>(
                "colPrice"));
        tblItemList.setItems(TableItemData);

        mb = SimpleMessageBoxFactory.createMessageBox();
        txtItemId.setText(itemDAO.generateID());
//        userId = "EM0001";
//        txtUserId.setText(userId);
        btnDelete.setVisible(false);
        batchNoList = cmbBatchNo.getItems();
        batchNoList.clear();
        batchNoList = FXCollections.observableArrayList(
                itemDAO.generateBatchID(txtItemId.getText()));
        cmbBatchNo.setItems(batchNoList);
        cmbBatchNo.getSelectionModel().selectFirst();
//        btnDelete.setVisible(false);
    }

    private void loadBatchNoToCombobox(String keyword) {

        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = itemDAO.searchItemBatchDetails(
                keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {
                    batchNoList.add(itemInfo.get(i).get(0));
                }
            }
            cmbBatchNo.setItems(batchNoList);
            cmbBatchNo.getSelectionModel().selectFirst();

        }
        try {
            if (itemDAO.getPrice(txtItemId.getText(),
                    cmbBatchNo.getValue().toString()) != null) {
                txtPrice.setText(itemDAO.getPrice(txtItemId.getText(),
                        cmbBatchNo.getValue().toString()));
            }
        } catch (Exception e) {

        }

    }

    private void loadBatch() {
        boolean value = false;
        if (isupdate == false) {
            batchNoList.clear();
        }
        String batch = itemDAO.generateBatchID(txtItemId.getText());
        for (int i = 0; i < batchNoList.size(); i++) {
            if (batchNoList.get(i).equals(batch)) {
                value = true;
            }
        }
        if (value == false) {
            batchNoList.add(itemDAO.generateBatchID(txtItemId.getText()));
            cmbBatchNo.getSelectionModel().selectLast();
        }

    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void clearInput() {

        TableItemData.clear();
        no = 1;
        txtUserId.setText(userName);
        btnSave.setVisible(true);
        btnDelete.setVisible(false);
        update = false;
        isupdate = false;
        txtItemId.setText(itemDAO.generateID());
        txtItemName.clear();
        txtPrice.clear();
        loadBatch();
        validatorInitialization();
    }

    @Override
    public void clearValidations() {
        no = 1;
        txtItemName.clear();

        batchNoList.clear();

        txtPrice.clear();
        isupdate = true;

        int count = TableItemData.size();
        if (count == 0) {

//            btnSave.setVisible(false);
            btnDelete.setVisible(true);

        }

        validatorInitialization();
    }

    @Override
    public void setStage(Stage stage, Object[] obj) {

        this.stageExtra = stage;
        setUserAccessLevel();
        //item popup------------------------
        itemTable = itemPopup.tableViewLoader(itemData);

        itemTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                try {
                    btnDelete.setVisible(true);
                    ItemInfoPopup p = null;
                    p = (ItemInfoPopup) itemTable.getSelectionModel().
                            getSelectedItem();
                    if (p.getColItemID() != null) {
                        clearValidations();

                        txtItemId.setText(p.getColItemID());
                        txtItemName.setText(p.getColItemName());
                        txtUserId.setText(itemDAO.getUserName(
                                txtItemId.getText()));
                        batchNoList.clear();
                        loadBatchNoToCombobox(p.getColItemID());
                    }

                } catch (NullPointerException n) {

                }

                itemPop.hide();
                validatorInitialization();

            }

        });

        itemTable.setOnMousePressed(e -> {

            if (e.getButton() == MouseButton.SECONDARY) {

                itemPop.hide();
                validatorInitialization();

            }

        });

        itemPop = new PopOver(itemTable);

        stage.setOnCloseRequest(e -> {

            if (itemPop.isShowing()) {
                e.consume();
                itemPop.hide();

            }
        });
        validatorInitialization();
    }

    @FXML
    void btnItemNameSearchOnAction(ActionEvent event) {
        itemTableDataLoader(txtItemName.getText());
        itemTable.setItems(itemData);
        if (!itemData.isEmpty()) {
            itemPop.show(btnItemNameSearch);
        }
        validatorInitialization();
    }

    @FXML
    void btnRefreshOnAction(ActionEvent event) {
        clearInput();
    }

    private void itemTableDataLoader(String keyword) {

        itemData.clear();
        ArrayList<ArrayList<String>> itemInfo
                = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> list = itemDAO.searchItemDetails(keyword);

        if (list != null) {

            for (int i = 0; i < list.size(); i++) {

                itemInfo.add(list.get(i));
            }

            if (itemInfo != null && itemInfo.size() > 0) {
                for (int i = 0; i < itemInfo.size(); i++) {

                    itemPopup = new ItemInfoPopup();
                    itemPopup.colItemID.setValue(itemInfo.get(i).get(0));
                    itemPopup.colItemName.setValue(itemInfo.get(i).get(1));
                    itemData.add(itemPopup);
                }
            }

        }

    }

    @FXML
    void btnBatchNoOnAction(ActionEvent event) {
        loadBatch();

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        validatorInitialization();
        boolean validationSupportTableResult = false;
        boolean isTableContentSaved = false;
        Item itemTable;

        ValidationResult v = validationSupportTable.getValidationResult();

        if (v != null) {
            validationSupportTableResult = validationSupportTable.isInvalid();

            if (validationSupportTableResult == true) {

                mb.
                        ShowMessage(stageExtra, ErrorMessages.MandatoryError,
                                "Error",
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);

            } else if (validationSupportTableResult == false) {
                if (isupdate == false) {

                    if (tblItemList.getItems().size() != 0) {
                        for (int i = 0; i < tblItemList.getItems().size(); i++) {
                            itemTable = (Item) tblItemList.getItems().get(i);

                            isTableContentSaved = itemDAO.additem(
                                    itemTable.getColItemId(),
                                    itemTable.getColItemName(),
                                    userId,
                                    itemTable.getColBatchNo(),
                                    Double.parseDouble(itemTable.getColPrice()));

                        }
                    }

                    if (isTableContentSaved == true) {

                        mb.ShowMessage(stageExtra,
                                ErrorMessages.SuccesfullyCreated, "Information",
                                MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                MessageBox.MessageType.MSG_OK);
                        clearInput();

                    } else {
                        mb.ShowMessage(stageExtra,
                                ErrorMessages.NotSuccesfullyCreated, "Error",
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);
                    }
                    //Save Action Event
                } else {

                    MessageBox.MessageOutput option = mb.ShowMessage(stageExtra,
                            ErrorMessages.Update, "Information",
                            MessageBox.MessageIcon.MSG_ICON_NONE,
                            MessageBox.MessageType.MSG_YESNO);
                    if (option.equals(MessageBox.MessageOutput.MSG_YES)) {

                        if (tblItemList.getItems().size() != 0) {
                            for (int i = 0; i < tblItemList.getItems().size();
                                    i++) {
                                itemTable = (Item) tblItemList.getItems().get(i);

                                isTableContentSaved = itemDAO.additem(
                                        itemTable.getColItemId(),
                                        itemTable.getColItemName(),
                                        userId,
                                        itemTable.getColBatchNo(),
                                        Double.parseDouble(
                                                itemTable.getColPrice()));

                            }
                        }
                        if (isTableContentSaved == true) {

                            mb.ShowMessage(this.stageExtra,
                                    ErrorMessages.SuccesfullyUpdated,
                                    "Information",
                                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                    MessageBox.MessageType.MSG_OK);
                            clearInput();

                        } else {
                            mb.ShowMessage(stageExtra,
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
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        boolean isDeleted = false;
        Item itemTable;
        validatorInitialization();
        boolean validationSupportResult = false;

        ValidationResult v = validationSupportTable.getValidationResult();

        if (v != null) {
            validationSupportResult = validationSupportTable.isInvalid();

            if (validationSupportResult == true) {

                mb.ShowMessage(stageExtra, ErrorMessages.NoData, "Error",
                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                        MessageBox.MessageType.MSG_OK);

            } else if (validationSupportResult == false) {

                MessageBox.MessageOutput option = mb.ShowMessage(stageExtra,
                        ErrorMessages.Delete, "Information",
                        MessageBox.MessageIcon.MSG_ICON_NONE,
                        MessageBox.MessageType.MSG_YESNO);
                if (option.equals(MessageBox.MessageOutput.MSG_YES)) {

                    if (tblItemList.getItems().size() != 0) {
                        for (int i = 0; i < tblItemList.getItems().size(); i++) {
                            itemTable = (Item) tblItemList.getItems().get(i);
                            if (itemDAO.checkingItemAvailability(
                                    itemTable.getColItemId())) {
                                isDeleted = itemDAO.deleteItem(
                                        itemTable.getColItemId());
                            } else {
                                mb.ShowMessage(stageExtra,
                                        ErrorMessages.InvalidId, "Error",
                                        MessageBox.MessageIcon.MSG_ICON_FAIL,
                                        MessageBox.MessageType.MSG_OK);
                            }

                        }

                        if (isDeleted == true) {

                            mb.ShowMessage(stageExtra,
                                    ErrorMessages.SuccesfullyDeleted,
                                    "Information",
                                    MessageBox.MessageIcon.MSG_ICON_SUCCESS,
                                    MessageBox.MessageType.MSG_OK);
                            clearInput();

                        } else {
                            mb.ShowMessage(stageExtra, ErrorMessages.Error,
                                    "Error",
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }

                    } else {
                        mb.ShowMessage(stageExtra,
                                ErrorMessages.NoData, "Error",
                                MessageBox.MessageIcon.MSG_ICON_FAIL,
                                MessageBox.MessageType.MSG_OK);
                    }

                }

            }
        }
    }

    @FXML
    void cmbBatchNoOnAction(ActionEvent event) {

        try {
            if (itemDAO.getPrice(txtItemId.getText(),
                    cmbBatchNo.getValue().toString()) != null) {
                txtPrice.setText(itemDAO.getPrice(txtItemId.getText(),
                        cmbBatchNo.getValue().toString()));
            } else {
                txtPrice.clear();
            }
        } catch (Exception e) {
            txtPrice.clear();
        }

    }

    @FXML
    void tblRequestNoteListOnMouseClicked(
            javafx.scene.input.MouseEvent mouseEvent) {
        try {

            if (mouseEvent.getClickCount() == 2) {

                boolean model = tblItemList.getSelectionModel().isEmpty();

                if (model == false) {
                    TableItemData.remove(
                            tblItemList.getSelectionModel().getSelectedIndex());

                }
                validatorInitialization();

            }
        } catch (Exception e) {

        }
    }

    @FXML
    void txtItemNameOnKeyReleased(KeyEvent event) {
        if (txtItemName.getText().length() >= 3) {
            itemTableDataLoader(txtItemName.getText());
            itemTable.setItems(itemData);
            if (!itemData.isEmpty()) {
                itemPop.show(btnItemNameSearch);
            }else{
                itemPop.hide();
            }
            validatorInitialization();
        }
    }

    @FXML
    void txtPriceOnKeyReleased(KeyEvent event) {
        boolean validationSupportResult = false;
        boolean isAvalible = false;

        if (event.getCode() == KeyCode.ENTER) {

            ValidationResult v = validationSupportTableData.
                    getValidationResult();
            if (v != null) {

                validationSupportResult = validationSupportTableData.isInvalid();
                if (validationSupportResult == true) {
                    mb.ShowMessage(stageExtra, ErrorMessages.MandatoryError,
                            "Error",
                            MessageBox.MessageIcon.MSG_ICON_FAIL,
                            MessageBox.MessageType.MSG_OK);

                } else if (validationSupportResult == false) {

                    if (isupdate == false) {

                        isAvalible = itemDAO.checkingItemNameAvailability(
                                txtItemName.getText()
                        );

                        if (isAvalible == false) {
                            if (tblItemList.getItems().size() != 0) {
                                int n = tblItemList.getItems().size();
                                for (int s = 0; s < n; s++) {
                                    item = (Item) tblItemList.getItems().get(s);
                                    if ((txtItemId.getText() + cmbBatchNo.
                                            getValue()).equals(
                                                    (item.getColItemId() + item.
                                                    getColBatchNo()))
                                            && tblItemList.getItems().size() > 0) {
                                        TableItemData.remove(s);
                                        n--;
                                    }

                                    if (txtItemId.getText().equals(
                                            item.getColItemId())
                                            && tblItemList.getItems().size() > 0) {
                                        if (!item.getColItemName().equals(
                                                txtItemName.getText())) {
                                            item.setColColItemName(
                                                    txtItemName.getText());
                                        }
                                    }

                                }
                            }

                            item = new Item();
                            item.colItemId.setValue(txtItemId.getText());
                            item.colItemName.setValue(txtItemName.getText());
                            item.colBatchNo.setValue(
                                    cmbBatchNo.getValue().toString());
                            item.colPrice.setValue(txtPrice.getText());
                            TableItemData.add(item);
                            no = no + 1;
                            txtItemId.setText(itemDAO.generateIDOOnDemand(no));
                            loadBatchNoToCombobox(txtItemId.getText());
                            txtItemName.clear();
                            txtPrice.clear();

                        } else {
                            mb.ShowMessage(stageExtra,
                                    ErrorMessages.InvalidItemName,
                                    "Error",
                                    MessageBox.MessageIcon.MSG_ICON_FAIL,
                                    MessageBox.MessageType.MSG_OK);
                        }

                    } else {
                        if (tblItemList.getItems().size() != 0) {
                            int n = tblItemList.getItems().size();
                            for (int s = 0; s < n; s++) {
                                item = (Item) tblItemList.getItems().get(s);
                                if ((txtItemId.getText() + cmbBatchNo.getValue()).
                                        equals(
                                                (item.getColItemId() + item.
                                                getColBatchNo()))
                                        && tblItemList.getItems().size() > 0) {
                                    TableItemData.remove(s);
                                    n--;
                                }

                                if (txtItemId.getText().equals(
                                        item.getColItemId())
                                        && tblItemList.getItems().size() > 0) {
                                    if (!item.getColItemName().equals(
                                            txtItemName.getText())) {
                                        item.setColColItemName(
                                                txtItemName.getText());
                                    }
                                }
                            }
                        }

                        item = new Item();
                        item.colItemId.setValue(txtItemId.getText());
                        item.colItemName.setValue(txtItemName.getText());
                        item.colBatchNo.setValue(
                                cmbBatchNo.getValue().toString());
                        item.colPrice.setValue(txtPrice.getText());
                        TableItemData.add(item);

                        txtItemId.setText(itemDAO.generateID());
                        loadBatchNoToCombobox(txtItemId.getText());
                        txtItemName.clear();
                        txtPrice.clear();

                        update = true;
                        btnSave.setVisible(true);

                    }

                }
            }
        }
        validatorInitialization();
    }

    private void validatorInitialization() {

        validationSupportTableData.registerValidator(txtItemName,
                new CustomTextFieldValidationImpl(txtItemName,
                        !fav.validName(txtItemName.getText()),
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

        public SimpleStringProperty colItemId = new SimpleStringProperty(
                "tcItemId");
        public SimpleStringProperty colItemName = new SimpleStringProperty(
                "tcItemName");
        public SimpleStringProperty colBatchNo = new SimpleStringProperty(
                "tcBatchNo");
        public SimpleStringProperty colPrice = new SimpleStringProperty(
                "tcPrice");

        public String getColItemId() {
            return colItemId.get();
        }

        public String getColItemName() {
            return colItemName.get();
        }

        public String getColBatchNo() {
            return colBatchNo.get();
        }

        public String getColPrice() {
            return colPrice.get();
        }

        public void setColColItemName(String itemName) {
            colItemName.setValue(itemName);
        }

    }

    //<editor-fold defaultstate="collapsed" desc="Methods">
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

        txtItemId.setDisable(state);
        txtItemId.setVisible(!state);

        txtItemName.setDisable(state);
        txtItemName.setVisible(!state);

        btnItemNameSearch.setDisable(state);
        btnItemNameSearch.setVisible(!state);

        txtUserId.setDisable(state);
        txtUserId.setVisible(!state);

        cmbBatchNo.setDisable(state);
        cmbBatchNo.setVisible(!state);

        btnBatchNo.setDisable(state);
        btnBatchNo.setVisible(!state);

        txtPrice.setDisable(state);
        txtPrice.setVisible(!state);

        tblItemList.setDisable(state);
        tblItemList.setVisible(!state);

        btnDelete.setDisable(state);
        btnDelete.setVisible(!state);

        btnSave.setDisable(state);
        btnSave.setVisible(!state);

        btnClose.setDisable(state);
        btnClose.setVisible(!state);
    }

    private void setUserAccessLevel() {

        userId = UserSession.getInstance().getUserInfo().getEid();

        userName = UserSession.getInstance().getUserInfo().getName();
        userCategory = UserSession.getInstance().getUserInfo().getCategory();
        txtUserId.setText(userName);

        String title = stageExtra.getTitle();

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

    private void deactivateSearch() {
        componentControl.deactivateSearch(lblItemName, txtItemName,
                btnItemNameSearch,
                220.00, 0.00);

    }

    private void deactivateCombo() {
        componentControl.controlCComboBox(lblItemId1, cmbBatchNo, btnBatchNo,
                220.00, 0.0, true);
    }

//</editor-fold>
}
