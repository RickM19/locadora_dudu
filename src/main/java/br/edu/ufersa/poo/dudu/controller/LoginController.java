package br.edu.ufersa.poo.dudu.controller;
import br.edu.ufersa.poo.dudu.exceptions.AuthenticationException;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.model.services.UserService;
import br.edu.ufersa.poo.dudu.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.swing.*;

public class LoginController {
    @FXML
    private Label erro;
    @FXML
    private Button login;
    @FXML
    private PasswordField senha;
    @FXML
    private TextField usuario;

    UserService service = new UsuarioServiceImpl();
    Usuario userLogante = new Usuario();

    public void autenticar() {
        userLogante.setNomeUsuario(usuario.getText());
        userLogante.setSenha(senha.getText());
        try {
            service.fazerLogin(userLogante);
            JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
            ProjetoDudu.produtos();

        } catch (AuthenticationException e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }
}
