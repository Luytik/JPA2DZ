package users;

import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WalletActionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String what;

    private Double value;

    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    String from;

    public WalletActionsEntity() {
    }

    public WalletActionsEntity(String what, Double value, User user, Date date) {
        this.what = what;
        this.value = value;
        this.user = user;
        this.date = date;
        from = user.getLogin();
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public Date dateProperty() {
        return this.date;
    }
}
