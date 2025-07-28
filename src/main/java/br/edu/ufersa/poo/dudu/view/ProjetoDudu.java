package br.edu.ufersa.poo.dudu.view;

import br.edu.ufersa.poo.dudu.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjetoDudu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        ProjetoDudu.stage = stage;
        telaLogin();
    }

    private static void loadTela(String nomeArquivo){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource
                ("/br/edu/ufersa/poo/dudu/" + nomeArquivo));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Locadora Dudu");
        stage.setScene(scene);
        stage.show();
    }

    public static void telaLogin(){
        loadTela("login.fxml");
    }
    public static void alugueis(){
        loadTela("alugueis.fxml");
    }
    public static void cadastro(){
        loadTela("cadastro.fxml");
    }
    public static void painelControle(){
        loadTela("painel_controle.fxml");
    }
    public static void relatorios(){
        loadTela("relatorios.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
