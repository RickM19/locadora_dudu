package br.edu.ufersa.poo.dudu.controller;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.services.AluguelService;
import br.edu.ufersa.poo.dudu.model.services.AluguelServiceImpl;
import br.edu.ufersa.poo.dudu.model.services.UserService;
import br.edu.ufersa.poo.dudu.model.services.UsuarioServiceImpl;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RelatoriosController {
    @FXML
    private TextField buscar;
    @FXML
    private ComboBox<Month> cb;
    @FXML private TableView<Aluguel> tb;
    @FXML private TableColumn<Aluguel, String> produtoCol, clienteCol, inicioCol, fimCol, valorCol;
    @FXML private Label total;

    private final AluguelService aluguel = new AluguelServiceImpl();
    private ObservableList<Aluguel> aluguelOL = FXCollections.observableArrayList();

    @FXML
    public void initialize(){
       configurarColunasTabela();
       configurarComboBox();
       configurarBusca();
       alugueisConcluidos();
       filtros();
    }

    private void configurarColunasTabela(){
        clienteCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCliente().getNome()));
        produtoCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getItem().getTitulo()));
        valorCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.format("R$ %.2f", cellData.getValue().getValorTotal())));
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyy");
        inicioCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataInicio().format(formatar)));
        fimCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataFim().format(formatar)));
    }

    private void configurarComboBox(){
        cb.getItems().addAll(Month.values());
        cb.setConverter(new StringConverter<Month>() {
            @Override
            public String toString(Month m) {
                return m == null ? "" : m.getDisplayName(TextStyle.FULL_STANDALONE, new Locale("pt", "BR"));
            }
            @Override
            public Month fromString(String s){
                return null;
            }
        });
        cb.valueProperty().addListener((obs, oldMonth, newMonth) -> filtros());
    }

    private void configurarBusca(){
        buscar.textProperty().addListener((observable, oldValue, newValue) -> filtros());
    }

    private void alugueisConcluidos(){
        try {
            List<Aluguel> todos = aluguel.listarTodos();
            List<Aluguel> concluidos = todos.stream().filter(Aluguel::getFinalizado).collect(Collectors.toList());
            aluguelOL.setAll(concluidos);
        } catch (RuntimeException e){
            showAlert(Alert.AlertType.ERROR, "Erro ao carregar aluguéis", "Ocorreu um erro: " + e.getMessage());
        }
    }

    private void filtros(){
        List<Aluguel> filtrado = aluguelOL.stream().collect(Collectors.toList());
        Month mesSelecionado = cb.getSelectionModel().getSelectedItem();
        if(mesSelecionado != null){
            filtrado = filtrado.stream()
                    .filter(aluguel -> aluguel.getDataInicio().getMonth()==mesSelecionado||
                            aluguel.getDataFim().getMonth()==mesSelecionado).collect(Collectors.toList());
        }
        String search = buscar.getText();
        if(search != null && !search.isEmpty()){
            final String searchLower = search.toLowerCase().trim();
            filtrado = filtrado.stream()
                    .filter(aluguel -> aluguel.getCliente() != null &&
                            aluguel.getCliente().getNome().toLowerCase().contains(searchLower))
                    .collect(Collectors.toList());
        }
        tb.setItems(FXCollections.observableArrayList(filtrado));
        calculaTotal();
    }

    private void calculaTotal(){
        double t = tb.getItems().stream()
                .mapToDouble(Aluguel::getValorTotal).sum();
        total.setText(String.format("%.2f", t));
    }

    private void showAlert(Alert.AlertType type, String titulo, String mensagem){
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public void alugueisButton() { ProjetoDudu.alugueis(); }
    public void clientesButton() { ProjetoDudu.clientes(); }
    public void produtosButton() { ProjetoDudu.produtos(); }
    public void sairButton() {
        final UserService userService = new UsuarioServiceImpl();
        userService.deslogar();
        ProjetoDudu.telaLogin();
    }
    public void cadastroButton(){ProjetoDudu.cadastro();}
}
