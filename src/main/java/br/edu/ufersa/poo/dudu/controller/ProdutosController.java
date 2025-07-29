package br.edu.ufersa.poo.dudu.controller;



import br.edu.ufersa.poo.dudu.model.services.UserService;
import br.edu.ufersa.poo.dudu.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;

import javafx.fxml.FXML;


public class ProdutosController {
    private UserService userService;

    @FXML
    public void initialize(){
        userService = new UsuarioServiceImpl();
    }

    public void alugueisButton() {
        ProjetoDudu.alugueis();
    }
    public void clientesButton() {
        ProjetoDudu.clientes();
    }

    public void relatoriosButton() {
        ProjetoDudu.relatorios();
    }


    public void sairButton() {
        userService.deslogar();
        ProjetoDudu.telaLogin();
    }

}