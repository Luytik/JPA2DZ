package com.owl.jpa2dz;

import controllerjpa.PersistanceService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import users.User;
import users.WalletActionsEntity;

import javax.persistence.EntityManager;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class WalletActionsController implements Initializable {

    private User currentUser;

    @FXML
    private Label UserLbl;

    @FXML
    private TableColumn<WalletActionsEntity, String> fromCLmn;

    @FXML
    private TableColumn<WalletActionsEntity, Double> howManyCLmn;

    @FXML
    private TableView<WalletActionsEntity> table;

    @FXML
    private TableColumn<WalletActionsEntity, String> typeValueClmn;

    public void setUser(User user){
        this.currentUser = user;
    }

    @FXML
    void update(ActionEvent event) {
        System.out.println(currentUser);
        ObservableList<WalletActionsEntity> data;
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        User user = em.getReference(User.class, currentUser.getId());
        List<WalletActionsEntity> walletActionsList = user.getListWalletActions();
        data = FXCollections.observableArrayList(walletActionsList);
        howManyCLmn.setCellValueFactory(new PropertyValueFactory<>("value"));
        typeValueClmn.setCellValueFactory(new PropertyValueFactory<>("what"));
        fromCLmn.setCellValueFactory(new PropertyValueFactory<>("from"));
        table.setItems(data);
    }

    public void setUserNameLbl(String text) {
        UserLbl.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
