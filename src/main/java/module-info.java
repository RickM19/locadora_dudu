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

    opens br.edu.ufersa.poo.dudu to javafx.fxml;
    exports br.edu.ufersa.poo.dudu;
}