package com.owl.jpa2dz;

import controllerjpa.PersistanceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.BankAccount;
import users.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnterLoginController {

    EntityManager entityManager = PersistanceService.getEmf().createEntityManager();

    @FXML
    private Button enterBtn;

    @FXML
    private TextField inputLoginField;

    private long mainUserId;

    @FXML
    void enter(ActionEvent event) {

        long userNameId = presenceUserInDB(inputLoginField.getText());

        if (userNameId == 0) {
            User newUser = new User(inputLoginField.getText());
            BankAccount bankAccount = new BankAccount(0.0, 0.0, 0.0, 0.0, UUID.randomUUID().toString());
            newUser.setBankAccount(bankAccount);
            entityManager.getTransaction().begin();
            entityManager.persist(newUser);
            entityManager.persist(bankAccount);
            userNameId = newUser.getId();
            entityManager.getTransaction().commit();
        }

        User user = entityManager.getReference(User.class, userNameId);
        BankAccount bankAccount = user.getBankAccount();
        mainUserId = user.getId();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("MainWindow.fxml"));;
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle(user.getLogin());
            stage.setScene(scene);
            MainWindowController mainWindowController = fxmlLoader.getController();
            mainWindowController.setUserNameLbl(user.getLogin());
            mainWindowController.setUsersChoiceBox(getAllUsersExceptMe());
            mainWindowController.setUsdLbl(String.valueOf(bankAccount.getCcyUSD()));
            mainWindowController.setUahLbl(String.valueOf(bankAccount.getCcyUAH()));
            mainWindowController.setBtcLbl(String.valueOf(bankAccount.getCcyBTC()));
            mainWindowController.setEuroLbl(String.valueOf(bankAccount.getCcyEURO()));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        entityManager.close();
        Stage stage = (Stage) enterBtn.getScene().getWindow();
        stage.close();
    }

    final long presenceUserInDB(String name) {
        User user;
        try {
            Query query = entityManager.createQuery("select u from User u where u.login = :name");
            query.setParameter("name", name);
            user = (User) query.getSingleResult();
        } catch (NoResultException nr) {
            return 0;
        }
        return user.getId();
    }

    public List<String> getAllUsersExceptMe(){
        List<String> userNameList = new ArrayList<>();
        User currentUser = entityManager.getReference(User.class, mainUserId);
        Query query1 = entityManager.createQuery("select u from User u");
        List<User> list = (List<User>) query1.getResultList();
        int indexInList = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == currentUser.getId()){
                indexInList = i;
                break;
            }
        }
        list.remove(indexInList);
        for(User u : list){
            userNameList.add(u.getLogin());
        }
        return userNameList;
    }
}
