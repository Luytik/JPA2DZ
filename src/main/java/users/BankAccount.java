package users;


import javax.persistence.*;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public BankAccount() {
    }

    public BankAccount(Double ccyUSD, Double ccyUAH, Double ccyBTC, Double ccyEURO, String uniqueNumber) {
        this.ccyUSD = ccyUSD;
        this.ccyUAH = ccyUAH;
        this.ccyBTC = ccyBTC;
        this.ccyEURO = ccyEURO;
        this.uniqueNumber = uniqueNumber;
    }

    @Column(name = "USD")
    private Double ccyUSD;

    @Column(name = "UAH")
    private Double ccyUAH;

    @Column(name = "BTC")
    private Double ccyBTC;

    @Column(name = "EURO")
    private Double ccyEURO;


    @Column(nullable = true, unique = true)
    private String uniqueNumber;

    @OneToOne(mappedBy = "bankAccount")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCcyUSD() {
        return ccyUSD;
    }

    public void setCcyUSD(Double ccyUSD) {
        this.ccyUSD = ccyUSD;
    }

    public Double getCcyUAH() {
        return ccyUAH;
    }

    public void setCcyUAH(Double ccyUAH) {
        this.ccyUAH = ccyUAH;
    }

    public Double getCcyBTC() {
        return ccyBTC;
    }

    public void setCcyBTC(Double ccyBTC) {
        this.ccyBTC = ccyBTC;
    }

    public Double getCcyEURO() {
        return ccyEURO;
    }

    public void setCcyEURO(Double ccyEURO) {
        this.ccyEURO = ccyEURO;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addToccyUSD(double value){
        ccyUSD += value;
    }
    public void addToccyUAH(double value){
        ccyUAH += value;
    }
    public void addToccyBTC(double value){
        ccyBTC += value;
    }
    public void addToccyEURO(double value){
        ccyEURO += value;
    }

    public void minusToccyUSD(double value){
        ccyUSD -= value;
    }
    public void minusToccyUAH(double value){
        ccyUAH -= value;
    }
    public void minusToccyBTC(double value){
        ccyBTC -= value;
    }
    public void minusToccyEURO(double value){
        ccyEURO -= value;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", ccyUSD=" + ccyUSD +
                ", ccyUAH=" + ccyUAH +
                ", ccyBTC=" + ccyBTC +
                ", ccyEURO=" + ccyEURO +
                ", uniqueNumber='" + uniqueNumber + '\'' +
                '}';
    }
}
