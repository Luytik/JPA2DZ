module com.owl.jpa2dz {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;

    opens com.owl.jpa2dz to javafx.fxml, org.hibernate.orm.core, javafx.base;
    opens controllerjpa;
    opens CurrencyExchange;
    opens users to org.hibernate.orm.core;

    exports users;
    exports com.owl.jpa2dz;
}