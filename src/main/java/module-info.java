module br.edu.ufersa.poo.dudu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    requires jakarta.persistence;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires java.sql;

    opens br.edu.ufersa.poo.dudu.model.entities;

    opens br.edu.ufersa.poo.dudu to javafx.fxml;
    exports br.edu.ufersa.poo.dudu;

    exports br.edu.ufersa.poo.dudu.controller;
    opens br.edu.ufersa.poo.dudu.controller to javafx.fxml;
    exports br.edu.ufersa.poo.dudu.exceptions;
    opens br.edu.ufersa.poo.dudu.exceptions to javafx.fxml;
    exports br.edu.ufersa.poo.dudu.view;
    opens br.edu.ufersa.poo.dudu.view to javafx.fxml;

}