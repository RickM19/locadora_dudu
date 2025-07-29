package br.edu.ufersa.poo.dudu.view;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.model.enums.TipoUsuario;
import br.edu.ufersa.poo.dudu.util.DatabaseSeeder;
import br.edu.ufersa.poo.dudu.util.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;



public class ProjetoDudu extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        ProjetoDudu.stage = stage;
        DatabaseSeeder.seedUsuarioDudu();
        telaLogin();
    }

    private static void loadTela(String nomeArquivo){
        FXMLLoader fxmlLoader = new FXMLLoader(ProjetoDudu.class.getResource
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
        Session sessionInstance = Session.getInstance();
        Usuario usuarioLogado = sessionInstance.getUsuarioLogado();
        if(usuarioLogado.getTipoUsuario() == TipoUsuario.ADMIN)
            loadTela("cadastro.fxml");
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Acesso negado!");
            alert.setHeaderText(null);
            alert.setContentText("Apenas o administrador pode acessar essa tela.");
            alert.showAndWait();
        }
    }
    public static void produtos(){
        loadTela("produtos.fxml");
    }
    public static void clientes(){
        loadTela("clientes.fxml");
    }

    public static void relatorios(){
        loadTela("relatorios.fxml");
    }

    public static void main(String[] args) {
        launch();
    }
}
