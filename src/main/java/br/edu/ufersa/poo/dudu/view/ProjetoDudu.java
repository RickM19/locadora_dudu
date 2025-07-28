package br.edu.ufersa.poo.dudu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjetoDudu extends Application {
    public static Stage stage;

    public void start(Stage stage) {
        ProjetoDudu.stage = stage;
        telaLogin();
    }

    public static void telaLogin() {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjetoDudu.class.getResource("/br/edu/ufersa/poo/dudu/login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("LocadoraDudu");
        stage.setScene(scene);
        stage.show();
    }

    public static void painelControle() {
        FXMLLoader fxmlLoader = new FXMLLoader(ProjetoDudu.class.getResource("/br/edu/ufersa/poo/dudu/painel_controle.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("LocadoraDudu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
