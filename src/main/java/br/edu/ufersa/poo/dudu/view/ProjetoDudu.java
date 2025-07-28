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
        this.stage = stage;
        telaLogin();
    }

    private static void loadTela(String nomeArquivo){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource
                ("/br/edu/ufersa/poo/dudu" + nomeArquivo));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void telaLogin(){
        stage.setTitle("Login");
        loadTela("login.fxml");
    }
    public static void alugueis(){
        stage.setTitle("Alugueis");
        loadTela("alugueis.fxml");
    }
    public static void cadastro(){
        stage.setTitle("Cadastro");
        loadTela("cadastro.fxml");
    }
    public static void painelControle(){
        stage.setTitle("Locadora Dudu");
        loadTela("painel_controle.fxml");
    }
    public static void relatorios(){
        stage.setTitle("Relatórios");
        loadTela("relatorios.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
