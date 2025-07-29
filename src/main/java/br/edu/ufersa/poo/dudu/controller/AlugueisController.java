package br.edu.ufersa.poo.dudu.controller;

import br.edu.ufersa.poo.dudu.model.entities.*;
import br.edu.ufersa.poo.dudu.model.enums.TipoProduto;
import br.edu.ufersa.poo.dudu.model.services.*;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class AlugueisController {
    @FXML private TableView<Aluguel> aluguelTable;

    @FXML private TableColumn<Aluguel, String> colCliente;
    @FXML private TableColumn<Aluguel, String> colItem;
    @FXML private TableColumn<Aluguel, String> colDataInicio;
    @FXML private TableColumn<Aluguel, String> colDataFim;

    @FXML private TextField consultaField;

    @FXML private TextField nomeClienteField;
    @FXML private TextField tituloField;
    @FXML private ComboBox<TipoProduto> tipoField;
    @FXML private DatePicker dataFimField;

    @FXML private Button botaoFinalizar;

    private final AluguelService aluguelService = new AluguelServiceImpl();
    private final ObservableList<Aluguel> aluguelList = FXCollections.observableArrayList();
    private Aluguel novoAluguel = new Aluguel();
    private boolean modoEdicao = false;

    private void showAlert(Alert.AlertType type, String titulo, String mensagem){
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void preencherCampos(){
        final ClienteService clienteService = new ClienteServiceImpl();

        Produto produtoEncontrado;
        String titulo = tituloField.getText();
        switch (tipoField.getValue()){
            case DISCO:
                final DiscoService discoService = new DiscoServiceImpl();
                Disco discoTemp = new Disco();
                discoTemp.setTitulo(titulo);
                produtoEncontrado = discoService.buscarPorTitulo(discoTemp);
                break;
            case LIVRO:
                final LivroService livroService = new LivroServiceImpl();
                Livro livroTemp = new Livro();
                livroTemp.setTitulo(titulo);
                produtoEncontrado = livroService.buscarPorTitulo(livroTemp);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido!");
        }

        Cliente clienteTemp = new Cliente(nomeClienteField.getText(), "cpf", "endereço");
        Cliente clienteEncontrado = clienteService.buscarPorNome(clienteTemp);

        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataFimField.getValue();
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);
        double valorTotal = dias * produtoEncontrado.getValorAluguel();

        novoAluguel = new Aluguel(
                clienteEncontrado,
                produtoEncontrado,
                dataInicio,
                dataFim,
                valorTotal,
                false);
    }
    private void limparCampos() {
        nomeClienteField.clear();
        tituloField.clear();
        dataFimField.setValue(null);
        consultaField.clear();
        novoAluguel = new Aluguel();
    }

    private void configurarTabela(){
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colItem.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItemAlugado().getTitulo()));
        colDataInicio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataInicio().toString()));
        colDataFim.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataFim().toString()));

        carregarAlugueis();
    }
    private void carregarAlugueis() {
        aluguelList.clear();
        List<Aluguel> alugueis = aluguelService.listarTodos();
        aluguelList.addAll(alugueis);
        aluguelTable.setItems(aluguelList);
    }

    private void configurarTipo(){
        tipoField.getItems().setAll(TipoProduto.values());

        tipoField.setCellFactory(param -> new ListCell<TipoProduto>() {
            @Override
            protected void updateItem(TipoProduto tipo, boolean empty) {
                super.updateItem(tipo, empty);
                setText(empty || tipo == null ? null : tipo.getDescricao());
            }
        });

        tipoField.setConverter(new StringConverter<TipoProduto>() {
            @Override
            public String toString(TipoProduto tipo) {
                return tipo != null ? tipo.getDescricao() : "";
            }

            @Override
            public TipoProduto fromString(String string) {
                if (string == null || string.isEmpty()) return null;
                for (TipoProduto tipo : TipoProduto.values()) {
                    if (tipo.getDescricao().equalsIgnoreCase(string)) return tipo;
                }
                return null;
            }
        });
    }

    private void configurarPesquisa(){
        aluguelTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        mostrarDadosAluguel(newValue));
        carregarAlugueis();
        consultaField.textProperty().addListener(
                (observable, oldValue, newValue) ->
                        realizarPesquisa(newValue));
    }

    private void mostrarDadosAluguel(Aluguel aluguel){
        if(aluguel != null){
            nomeClienteField.setText(aluguel.getCliente().getNome());
            tituloField.setText(aluguel.getItemAlugado().getTitulo());
        }else {
            limparCampos();
        }
    }
    private void realizarPesquisa(String termo){
        final String textoBusca = termo.toLowerCase().trim();

        if(termo.isEmpty()) aluguelTable.setItems(aluguelList);
        else {
            List<Aluguel> achados = aluguelList.stream().filter(aluguel ->
                            (aluguel.getCliente() != null &&
                                    aluguel.getCliente().getNome().toLowerCase().contains(textoBusca)) ||
                                    (aluguel.getItemAlugado() != null &&
                                            aluguel.getItemAlugado().getTitulo().toLowerCase().contains(textoBusca)))
                    .collect(Collectors.toList());
            aluguelTable.setItems(FXCollections.observableArrayList(achados));
        }
    }

    @FXML
    void initialize(){
        configurarTabela();

        configurarTipo();
        configurarPesquisa();
    }

    @FXML
    void cadastrarAluguel(ActionEvent event) {
        try {
            preencherCampos();

            aluguelService.cadastrar(novoAluguel);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Aluguel cadastrado com sucesso!");
            carregarAlugueis();
            limparCampos();
        }
        catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.WARNING, "Erro de Validação", e.getMessage());
        }
        catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao adicionar aluguel",
                    "Não foi possível cadastrar o aluguel: " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void atualizarAluguel(ActionEvent event) {
        Aluguel aluguelSelecionado = aluguelTable.getSelectionModel().getSelectedItem();
        if(aluguelSelecionado == null)
            showAlert(Alert.AlertType.WARNING, "Nenhum aluguel selecionado",
                    "Selecione um aluguel na tabela.");
        else{
            try {
                preencherCampos();

                aluguelService.atualizar(novoAluguel);

                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Aluguel atualizado com sucesso!");
                carregarAlugueis();
                limparCampos();
            }
            catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.WARNING, "Erro de Validação", e.getMessage());
            }
            catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro ao adicionar aluguel",
                        "Não foi possível cadastrar o aluguel: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @FXML
    void excluirAluguel(ActionEvent event) {
        Aluguel aluguelSelecionado = aluguelTable.getSelectionModel().getSelectedItem();
        if(aluguelSelecionado == null)
            showAlert(Alert.AlertType.WARNING, "Nenhum aluguel selecionado",
                    "Selecione um aluguel na tabela.");
        else {
            try {
                aluguelService.excluir(aluguelSelecionado);
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Aluguel deletado com sucesso!");
                carregarAlugueis();
                limparCampos();
            }
            catch (IllegalArgumentException e){
                showAlert(Alert.AlertType.WARNING, "Erro de validação", e.getMessage());
            }
            catch (RuntimeException e){
                showAlert(Alert.AlertType.ERROR, "Erro ao deletar aluguel",
                        "Não foi possível deletar aluguel: " + e.getMessage());
            }
        }
    }

    @FXML
    void finalizarAluguel(ActionEvent event){

    }

    @FXML void abrirClientes(ActionEvent event) { ProjetoDudu.clientes(); }
    @FXML void abrirProdutos(ActionEvent event) { ProjetoDudu.produtos(); }
    @FXML void abrirRelatorios(ActionEvent event) { ProjetoDudu.relatorios(); }
    @FXML void sair(ActionEvent event) {
        final UserService userService = new UsuarioServiceImpl();
        userService.deslogar();
        ProjetoDudu.telaLogin();
    }
}
