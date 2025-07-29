package br.edu.ufersa.poo.dudu.controller;

import br.edu.ufersa.poo.dudu.model.entities.Cliente;
import br.edu.ufersa.poo.dudu.model.services.ClienteServiceImpl;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ClientesController {
    @FXML
    private TextField buscar;

    @FXML
    private TextField cpf;

    @FXML
    private TableColumn<Cliente, String> cpfcol;

    @FXML
    private TextField endereco;

    @FXML
    private TableColumn<Cliente, String> enderecocol;

    @FXML
    private TextField nome;

    @FXML
    private TableColumn<Cliente, String> nomecol;

    @FXML
    private TableView<Cliente> tb;

    private ClienteServiceImpl clienteService;
    private ObservableList<Cliente> clienteOL;

    @FXML
    public void initialize(){
        clienteService = new ClienteServiceImpl();
        clienteOL = FXCollections.observableArrayList();

        cpfcol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        nomecol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        enderecocol.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        tb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
        loadClientes();
        buscar.textProperty().addListener((observable, oldValue, newValue) -> filterClientes(newValue));
    }

    private void loadClientes(){
        try {
            List<Cliente> clientes = clienteService.listarTodos();
            clienteOL.setAll(clientes);
            tb.setItems(clienteOL);
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao carregar clientes", "Ocorreu um erro ao buscar os clientes do banco de dados: " + e.getMessage());
        }
    }

    private void filterClientes(String search){
        final String searchText = search.toLowerCase().trim();
        if(search.isEmpty())
            tb.setItems(clienteOL);
        else{
            List<Cliente> achados = clienteOL.stream().filter(cliente ->
                    (cliente.getNome() != null && cliente.getNome().toLowerCase().contains(searchText)) ||
                            (cliente.getCpf() != null && cliente.getCpf().toLowerCase().contains(searchText)) ||
                            (cliente.getEndereco() != null && cliente.getEndereco().toLowerCase().contains(searchText))).collect(Collectors.toList());
            tb.setItems(FXCollections.observableArrayList(achados));
        }
        clearFields();
        tb.getSelectionModel().clearSelection();
    }

    private void showClienteDetails(Cliente cliente){
        if(cliente!=null){
            cpf.setText(cliente.getCpf());
            nome.setText(cliente.getNome());
            endereco.setText(cliente.getEndereco());
            cpf.setEditable(false);
        }
        else{
            clearFields();
            cpf.setEditable(true);
        }
    }

    private void clearFields(){
        cpf.clear();
        nome.clear();
        endereco.clear();
        cpf.setEditable(true);
    }


    @FXML
    void adicionarButton(ActionEvent event){
        try {
            String cpf1 = cpf.getText().trim();
            String nome1 = nome.getText().trim();
            String endereco1 = endereco.getText().trim();

            Cliente novoCliente = new Cliente(nome1, cpf1, endereco1);
            clienteService.cadastrar(novoCliente);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
            loadClientes();
            clearFields();
        } catch (IllegalArgumentException e){
            showAlert(Alert.AlertType.WARNING, "Erro de Validação", e.getMessage());
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao adicionar cliente", "Não foi possível adicionar o cliente: " + e.getMessage());
        }
    }

    @FXML
    void atualizarButton(ActionEvent event){
        Cliente clienteSelecionado = tb.getSelectionModel().getSelectedItem();
        if(clienteSelecionado == null){
            showAlert(Alert.AlertType.WARNING, "Nenhum cliente selecionado", "Selecione um cliente na tabela.");
            return;
        }

        try {
            String cpf1 = cpf.getText().trim();
            String nome1 = nome.getText().trim();
            String endereco1 = endereco.getText().trim();
            Cliente clienteAtualizar = new Cliente(nome1, cpf1, endereco1);
            clienteService.atualizar(clienteAtualizar);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cliente atualizado com sucesso!");
            loadClientes();
            clearFields();
        } catch (IllegalArgumentException e){
            showAlert(Alert.AlertType.WARNING, "Erro de validação", e.getMessage());
        } catch (RuntimeException e){
            showAlert(Alert.AlertType.ERROR, "Erro ao atualizar cliente", "Não foi possível atualizar cliente: " + e.getMessage());
        }
    }

    @FXML
    void deletarButton(ActionEvent event){
        Cliente clienteSelecionado = tb.getSelectionModel().getSelectedItem();
        if(clienteSelecionado == null){
            showAlert(Alert.AlertType.WARNING, "Nenhum cliente selecionado", "Selecione um cliente na tabela.");
            return;
        }

        try {
            clienteService.excluir(clienteSelecionado);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Cliente deletado com sucesso!");
            loadClientes();
            clearFields();
        } catch (IllegalArgumentException e){
            showAlert(Alert.AlertType.WARNING, "Erro de validação", e.getMessage());
        } catch (RuntimeException e){
            showAlert(Alert.AlertType.ERROR, "Erro ao deletar cliente", "Não foi possível deletar cliente: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String titulo, String mensagem){
Alert alert = new Alert(type);
alert.setTitle(titulo);
alert.setContentText(mensagem);
alert.showAndWait();
    }

    public void sairButton(){ProjetoDudu.telaLogin();}
    public void relatoriosButton(){ProjetoDudu.relatorios();}
    public void alugueisButton(){ProjetoDudu.alugueis();}
    public void produtosButton(){ProjetoDudu.produtos();}
}
