package br.edu.ufersa.poo.dudu.controller;

import br.edu.ufersa.poo.dudu.exceptions.AuthenticationException;
import br.edu.ufersa.poo.dudu.exceptions.CadastroException;
import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.model.services.UserService;
import br.edu.ufersa.poo.dudu.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import javax.swing.*;

public class CadastroController {

    @FXML
    private TextField email;

    @FXML
    private Label erro;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField usuario;

    UserService service = new UsuarioServiceImpl();
    Usuario userCadastro = new Usuario();

    public void autenticar(ActionEvent event) {
        userCadastro.setNomeUsuario(usuario.getText());
        userCadastro.setSenha(senha.getText());
        userCadastro.setEmail(email.getText());
        userCadastro.setTipoUsuario(usuario.getText());
        try {
            service.cadastrar(userCadastro);
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            ProjetoDudu.cadastro();

        } catch (CadastroException e) {
            erro.setText(e.getMessage());
            erro.setTextFill(Color.RED);
            erro.setVisible(true);
        }
    }

    public void sairButton(){
        service.deslogar();
        ProjetoDudu.telaLogin();
    }
    public void relatoriosButton(){ProjetoDudu.relatorios();}
    public void alugueisButton(){ProjetoDudu.alugueis();}
    public void produtosButton(){ProjetoDudu.produtos();}
    public void clientesButton(){ProjetoDudu.clientes();}

}
