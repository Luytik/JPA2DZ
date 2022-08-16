package com.owl.jpa2dz;

import CurrencyExchange.Currency;
import CurrencyExchange.GetCurrency;
import controllerjpa.PersistanceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.User;
import users.WalletActionsEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    private User currentUser;
    private GetCurrency getCurrency = new GetCurrency();
    private Currency usd = getCurrency.getUSDtoUAH();
    private Currency euro = getCurrency.getEURtoUAH();
    private Currency btc = getCurrency.getBTCtoUAH();

    @FXML
    private Button exitBtn;

    @FXML
    private Label btcLbl;

    @FXML
    private Label uahLbl;

    @FXML
    private Label usdLbl;

    @FXML
    private Label userNameLbl;

    @FXML
    private TextField btcTextF;

    @FXML
    private TextField euroTextF;

    @FXML
    private TextField uahTextF;

    @FXML
    private TextField usdTextF;

    @FXML
    private Label rateBTC;

    @FXML
    private Label rateEURO;

    @FXML
    private Label rateUSD;

    @FXML
    private Label euroLbl;

    @FXML
    private ChoiceBox<String> fromChoiceBox;

    @FXML
    private ChoiceBox<String> toChoiceBox;

    @FXML
    private TextField valueFromF;

    @FXML
    private TextField valueToF;

    @FXML
    private TextField valueToUsersF;

    @FXML
    private ChoiceBox<String> fromToUser;

    @FXML
    private ChoiceBox<String> usersChoiceBox;

    @FXML
    private Button walletActionsBtn;

    @FXML
    void walletActionsOpen(ActionEvent event) throws IOException {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("WalletActions.fxml"));;
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Гаманець");
        stage.setScene(scene);
        WalletActionsController wac = fxmlLoader.getController();
        System.out.println("FROM walletActionsOpen");
        System.out.println(currentUser.getId());
        wac.setUser(currentUser);
        stage.show();
    }

    @FXML
    void toUsersOpen(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("WalletActions.fxml"));;
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Гаманець");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void currencyChangeOpen(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("WalletActions.fxml"));;
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Гаманець");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void btcAdd(ActionEvent event) {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        double currentValue = currentUser.getBankAccount().getCcyBTC();

        em.getTransaction().begin();
        try {
            currentUser.getBankAccount().setCcyBTC(currentValue + Double.valueOf(btcTextF.getText()));
            WalletActionsEntity wa = new WalletActionsEntity("BCT", Double.valueOf(btcTextF.getText()), currentUser, new Date());
            em.persist(wa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ioe) {
                e.printStackTrace();
            }
        }
        btcLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyBTC()));
    }

    @FXML
    void uahAdd(ActionEvent event) {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        double currentValue = currentUser.getBankAccount().getCcyUAH();

        em.getTransaction().begin();
        try {
            currentUser.getBankAccount().setCcyUAH(currentValue + Double.valueOf(uahTextF.getText()));
            WalletActionsEntity wa = new WalletActionsEntity("UAH", Double.valueOf(uahTextF.getText()), currentUser, new Date());
            em.persist(wa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                ;
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ioe) {
                e.printStackTrace();
            }
        }
        uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
    }

    @FXML
    void usdAdd(ActionEvent event) {

        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        double currentValue = currentUser.getBankAccount().getCcyUSD();

        em.getTransaction().begin();
        try {
            currentUser.getBankAccount().setCcyUSD(currentValue + Double.valueOf(usdTextF.getText()));
            WalletActionsEntity wa = new WalletActionsEntity("USD", Double.valueOf(usdTextF.getText()), currentUser, new Date());
            em.persist(wa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                ;
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ioe) {
                e.printStackTrace();
            }
        }
        usdLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUSD()));
    }

    @FXML
    void euroAdd(ActionEvent event) {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        double currentValue = currentUser.getBankAccount().getCcyEURO();

        em.getTransaction().begin();
        try {
            currentUser.getBankAccount().setCcyEURO(currentValue + Double.valueOf(euroTextF.getText()));
            WalletActionsEntity wa = new WalletActionsEntity("EURO", Double.valueOf(euroTextF.getText()), currentUser, new Date());
            em.persist(wa);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

            } catch (IOException ioe) {
                e.printStackTrace();
            }
        }
        euroLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyEURO()));
    }

    @FXML
    void fromToUAH(ActionEvent event) {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        String from = fromChoiceBox.getValue();
        em.getTransaction().begin();
        try {
            double valueFrom;
            double result;
            valueFrom = Double.parseDouble(valueFromF.getText());
            if (valueFrom < 0) {
                openErrorWindow();
                return;
            }
            switch (from) {
                case "USD":
                    if (currentUser.getBankAccount().getCcyUSD() < valueFrom) {
                        openErrorWindow();
                    }
                    result = valueFrom * Double.parseDouble(usd.getSale());
                    currentUser.getBankAccount().minusToccyUSD(valueFrom);
                    currentUser.getBankAccount().addToccyUAH(result);
                    usdLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUSD()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
                case "BTC":
                    if (currentUser.getBankAccount().getCcyBTC() < valueFrom) {
                        openErrorWindow();
                    }
                    result = valueFrom * Double.parseDouble(usd.getSale()) * Double.parseDouble(btc.getSale());
                    currentUser.getBankAccount().minusToccyBTC(valueFrom);
                    currentUser.getBankAccount().addToccyUAH(result);
                    btcLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyBTC()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
                case "EURO":
                    if (currentUser.getBankAccount().getCcyEURO() < valueFrom) {
                        openErrorWindow();
                    }
                    result = valueFrom * Double.parseDouble(euro.getSale());
                    currentUser.getBankAccount().minusToccyEURO(valueFrom);
                    currentUser.getBankAccount().addToccyUAH(result);
                    euroLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyEURO()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            openErrorWindow();
        }


    }

    @FXML
    void fromUAHto(ActionEvent event) {
        EntityManager em = PersistanceService.getEmf().createEntityManager();
        Query query = em.createQuery("select u from User u where u.login = : name");
        query.setParameter("name", userNameLbl.getText());
        currentUser = (User) query.getSingleResult();
        String to = toChoiceBox.getValue();
        System.out.println(to);
        em.getTransaction().begin();
        try {
            double valueTo;
            double result;
            valueTo = Double.parseDouble(valueToF.getText());
            if (valueTo < 0) {
                openErrorWindow();
                return;
            }
            if (currentUser.getBankAccount().getCcyUAH() < valueTo) {
                openErrorWindow();
                return;
            }
            switch (to) {
                case "USD":
                    result = valueTo / Double.parseDouble(usd.getSale());
                    currentUser.getBankAccount().addToccyUSD(result);
                    currentUser.getBankAccount().minusToccyUAH(valueTo);
                    usdLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUSD()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
                case "BTC":
                    result = valueTo / (Double.parseDouble(usd.getSale()) * Double.parseDouble(btc.getSale()));
                    currentUser.getBankAccount().addToccyBTC(result);
                    currentUser.getBankAccount().minusToccyUAH(valueTo);
                    btcLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyBTC()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
                case "EURO":
                    result = valueTo / Double.parseDouble(euro.getSale());
                    currentUser.getBankAccount().addToccyEURO(result);
                    currentUser.getBankAccount().minusToccyUAH(valueTo);
                    euroLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyEURO()));
                    uahLbl.setText(String.valueOf(currentUser.getBankAccount().getCcyUAH()));
                    break;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            openErrorWindow();
        }
    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("EnterLogin.fxml"));
        ;
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Логін");
        stage.setScene(scene);
        stage.show();


        Stage currentStage = (Stage) exitBtn.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void toAnotherUsers(ActionEvent event) {
        try {
            EntityManager em = PersistanceService.getEmf().createEntityManager();
            String typeToUser = fromToUser.getValue();
            String toUserName = usersChoiceBox.getValue();
            double value = Double.parseDouble(valueToUsersF.getText());
            if (value < 0) {
                openErrorWindow();
                return;
            }

            Query queryCurrentUser = em.createQuery("select u from User u where u.login = : name");
            queryCurrentUser.setParameter("name", userNameLbl.getText());
            User fromUser = (User) queryCurrentUser.getSingleResult();

            Query queryToUser = em.createQuery("select u from User u where u.login = : name");
            queryToUser.setParameter("name", toUserName);
            User toUser = (User) queryToUser.getSingleResult();
            em.getTransaction().begin();
            switch (typeToUser) {
                case "USD":
                    if (fromUser.getBankAccount().getCcyUSD() < value) {
                        openErrorWindow();
                        return;
                    }
                    fromUser.getBankAccount().minusToccyUSD(value);
                    toUser.getBankAccount().addToccyUSD(value);
                    usdLbl.setText(String.valueOf(fromUser.getBankAccount().getCcyUSD()));
                    break;
                case "BTC":
                    if (fromUser.getBankAccount().getCcyBTC() < value) {
                        openErrorWindow();
                        return;
                    }
                    fromUser.getBankAccount().minusToccyBTC(value);
                    btcLbl.setText(String.valueOf(fromUser.getBankAccount().getCcyBTC()));
                    toUser.getBankAccount().addToccyBTC(value);
                    break;
                case "EURO":
                    if (fromUser.getBankAccount().getCcyEURO() < value) {
                        openErrorWindow();
                        return;
                    }
                    fromUser.getBankAccount().minusToccyEURO(value);
                    toUser.getBankAccount().addToccyEURO(value);
                    euroLbl.setText(String.valueOf(fromUser.getBankAccount().getCcyEURO()));
                    break;
                case "UAH":
                    if (fromUser.getBankAccount().getCcyUAH() < value) {
                        openErrorWindow();
                        return;
                    }
                    fromUser.getBankAccount().minusToccyUAH(value);
                    toUser.getBankAccount().addToccyUAH(value);
                    uahLbl.setText(String.valueOf(fromUser.getBankAccount().getCcyUAH()));
                    break;
            }
            em.getTransaction().commit();

        } catch (Exception e) {
            openErrorWindow();
        }

    }

    public void setUserNameLbl(String text) {
        userNameLbl.setText(text);
    }

    public void setBtcLbl(String text) {
        btcLbl.setText(text);
    }

    public void setUahLbl(String text) {
        uahLbl.setText(text);
    }

    public void setUsdLbl(String text) {
        usdLbl.setText(text);
    }

    public void setEuroLbl(String text) {
        euroLbl.setText(text);
    }

    public void setUsersChoiceBox(List<String> userList) {
        usersChoiceBox.getItems().addAll(userList);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rateEURO.setText(String.valueOf(euro.getSale()));
        rateUSD.setText(String.valueOf(usd.getSale()));
        rateBTC.setText(String.valueOf(btc.getSale()));
        fromChoiceBox.getItems().addAll("USD", "EURO", "BTC");
        toChoiceBox.getItems().addAll("USD", "EURO", "BTC");
        fromToUser.getItems().addAll("USD", "EURO", "BTC", "UAH");
    }

    public void openErrorWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Error.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
