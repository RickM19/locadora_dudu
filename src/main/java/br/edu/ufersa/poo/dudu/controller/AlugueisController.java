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
    @FXML private TableColumn<Aluguel, String> colValorTotal;
    @FXML private TableColumn<Aluguel, String> colFinalizado;

    @FXML private TextField consultaField;

    @FXML private TextField nomeClienteField;
    @FXML private TextField tituloField;
    @FXML private ComboBox<TipoProduto> tipoField;
    @FXML private DatePicker dataFimField;

    @FXML private Button botaoFinalizar;
    @FXML private Button botaoCadastrar;
    @FXML private Button botaoAtualizar;
    @FXML private Button botaoExcluir;

    private final AluguelService aluguelService = new AluguelServiceImpl();
    private final ObservableList<Aluguel> aluguelList = FXCollections.observableArrayList();
    private Aluguel novoAluguel = new Aluguel();

    private final LivroService livroService = new LivroServiceImpl();
    private final DiscoService discoService = new DiscoServiceImpl();

    private void showAlert(Alert.AlertType type, String titulo, String mensagem){
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void preencherCampos(Aluguel aluguel){
        final ClienteService clienteService = new ClienteServiceImpl();
        Cliente clienteTemp = new Cliente();
        clienteTemp.setNome(nomeClienteField.getText());
        Cliente clienteEncontrado = clienteService.buscarPorNome(clienteTemp);
        if(clienteEncontrado == null) {
            throw new IllegalArgumentException("Cliente inexistente!");
        }

        Produto produtoEncontrado;
        String titulo = tituloField.getText();
        switch (tipoField.getValue()){
            case DISCO:
                Disco discoOriginal = (Disco) aluguel.getItem();
                Disco discoTemp = new Disco();

                if(discoOriginal != null) discoService.devolver(discoOriginal);
                discoTemp.setTitulo(titulo);
                produtoEncontrado = discoService.buscarPorTitulo(discoTemp);
                if(produtoEncontrado == null) {
                    throw new IllegalArgumentException("Produto inexistente!");
                }
                discoService.alugar((Disco) produtoEncontrado);
                break;
            case LIVRO:
                Livro livroTemp = new Livro();

                livroTemp.setTitulo(titulo);
                produtoEncontrado = livroService.buscarPorTitulo(livroTemp);
                if(produtoEncontrado == null) {
                    throw new IllegalArgumentException("Produto inexistente!");
                }
                livroService.alugar((Livro) produtoEncontrado);
                break;
            default:
                throw new IllegalArgumentException("Tipo inválido!");
        }



        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataFimField.getValue();
        long dias = ChronoUnit.WEEKS.between(dataInicio, dataFim);
        double valorTotal = dias * produtoEncontrado.getValorAluguel();

        aluguel.setCliente(clienteEncontrado);
        aluguel.setItem(produtoEncontrado);
        aluguel.setDataInicio(dataInicio);
        aluguel.setDataFim(dataFim);
        aluguel.setValorTotal(valorTotal);
        aluguel.setFinalizado(false);
    }
    private void limparCampos() {
        nomeClienteField.clear();
        tituloField.clear();
        dataFimField.setValue(null);
        consultaField.clear();
        novoAluguel = new Aluguel();
    }

    private void configurarTabela(){
        colCliente.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        colItem.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItem().getTitulo()));
        colDataInicio.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataInicio().toString()));
        colDataFim.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataFim().toString()));
        colValorTotal.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("R$ %.2f", cellData.getValue().getValorTotal())));
        colFinalizado.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFinalizado() ? "Sim" : "Não"));

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
            boolean isFinalizado = aluguel.getFinalizado();

            nomeClienteField.setText(aluguel.getCliente().getNome());
            tituloField.setText(aluguel.getItem().getTitulo());
            tipoField.setValue(TipoProduto.valueOf(aluguel.getItem().getTipo()));
            dataFimField.setValue(aluguel.getDataFim());

            nomeClienteField.setDisable(isFinalizado);
            tituloField.setDisable(isFinalizado);
            tipoField.setDisable(isFinalizado);
            dataFimField.setDisable(isFinalizado);

            botaoCadastrar.setDisable(isFinalizado);
            botaoAtualizar.setDisable(isFinalizado);
            botaoFinalizar.setDisable(isFinalizado);
            botaoExcluir.setDisable(isFinalizado);
        }else {
            limparCampos();

            nomeClienteField.setDisable(false);
            tituloField.setDisable(false);
            tipoField.setDisable(false);
            dataFimField.setDisable(false);

            botaoCadastrar.setDisable(false);
            botaoCadastrar.setDisable(false);
            botaoFinalizar.setDisable(true);
        }
    }
    private void realizarPesquisa(String termo){
        final String textoBusca = termo.toLowerCase().trim();

        if(termo.isEmpty()) aluguelTable.setItems(aluguelList);
        else {
            List<Aluguel> achados = aluguelList.stream().filter(aluguel ->
                            (aluguel.getCliente() != null &&
                                    aluguel.getCliente().getNome().toLowerCase().contains(textoBusca)) ||
                                    (aluguel.getItem() != null &&
                                            aluguel.getItem().getTitulo().toLowerCase().contains(textoBusca)))
                    .collect(Collectors.toList());
            aluguelTable.setItems(FXCollections.observableArrayList(achados));
        }
    }

    @FXML
    void initialize(){
        configurarTabela();
        configurarTipo();
        configurarPesquisa();

        botaoFinalizar.setDisable(true);
    }

    @FXML
    void cadastrarAluguel(ActionEvent event) {
        try {
            preencherCampos(novoAluguel);

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
                if (aluguelSelecionado.getFinalizado()) {
                    showAlert(Alert.AlertType.WARNING, "Aluguel já finalizado",
                            "Não é possível editar um aluguel já finalizado.");
                    return;
                }

                Aluguel aluguelParaAtualizar = aluguelService.buscarPorId(aluguelSelecionado);
                preencherCampos(aluguelParaAtualizar);

                aluguelService.atualizar(aluguelParaAtualizar);

                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Aluguel atualizado com sucesso!");
                carregarAlugueis();
                limparCampos();
            }
            catch (IllegalArgumentException e) {
                showAlert(Alert.AlertType.WARNING, "Erro de Validação", e.getMessage());
            }
            catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro ao atualizar aluguel",
                        "Não foi possível atualizar o aluguel: " + e.getMessage());
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
                switch (TipoProduto.valueOf(aluguelSelecionado.getItem().getTipo())){
                    case DISCO:
                        Disco discoEncontrado = (Disco) aluguelSelecionado.getItem();
                        discoService.devolver(discoEncontrado);
                        break;
                    case LIVRO:
                        Livro livroEncontrado = (Livro) aluguelSelecionado.getItem();
                        livroService.devolver(livroEncontrado);
                        break;
                    default:
                        throw new IllegalArgumentException("Tipo inválido!");
                }
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
    void finalizarAluguel(ActionEvent event) {
        Aluguel aluguelSelecionado = aluguelTable.getSelectionModel().getSelectedItem();
        if (aluguelSelecionado == null)
            showAlert(Alert.AlertType.WARNING, "Nenhum aluguel selecionado",
                    "Selecione um aluguel na tabela para finalizar.");
        else {
            try {
                Aluguel aluguelParaFinalizar = aluguelService.buscarPorId(aluguelSelecionado);

                switch (TipoProduto.valueOf(aluguelParaFinalizar.getItem().getTipo())) {
                    case DISCO -> discoService.devolver((Disco) aluguelParaFinalizar.getItem());
                    case LIVRO -> livroService.devolver((Livro) aluguelParaFinalizar.getItem());
                }

                aluguelService.finalizar(aluguelParaFinalizar);

                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Aluguel finalizado com sucesso!");
                carregarAlugueis();
                limparCampos();
            }
            catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro ao finalizar aluguel",
                        "Não foi possível finalizar o aluguel: " + e.getMessage());
            }
        }
    }

    @FXML void abrirClientes(ActionEvent event) { ProjetoDudu.clientes(); }
    @FXML void abrirProdutos(ActionEvent event) { ProjetoDudu.produtos(); }
    @FXML void abrirRelatorios(ActionEvent event) { ProjetoDudu.relatorios(); }
    @FXML void sair(ActionEvent event) {
        final UserService userService = new UsuarioServiceImpl();
        userService.deslogar();
        ProjetoDudu.telaLogin();
    }
    @FXML void cadastroButton(){ProjetoDudu.cadastro();}
}
