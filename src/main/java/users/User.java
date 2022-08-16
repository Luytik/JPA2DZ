package users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String login;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankaccount_id")
    private BankAccount bankAccount;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<WalletActionsEntity> listWalletActions;

    public User(){

    }

    public User(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<WalletActionsEntity> getListWalletActions() {
        return listWalletActions;
    }

    public void setListWalletActions(List<WalletActionsEntity> listWalletActions) {
        this.listWalletActions = listWalletActions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
