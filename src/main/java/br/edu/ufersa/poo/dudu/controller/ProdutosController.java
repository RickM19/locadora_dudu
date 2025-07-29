package br.edu.ufersa.poo.dudu.controller;

import br.edu.ufersa.poo.dudu.model.entities.*;
import br.edu.ufersa.poo.dudu.model.factory.ConcreteProdutoFactory;
import br.edu.ufersa.poo.dudu.model.services.*;
import br.edu.ufersa.poo.dudu.view.ProjetoDudu;
import static br.edu.ufersa.poo.dudu.util.StringUtils.normalizar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ProdutosController {

    @FXML private TextField buscar;
    @FXML private ComboBox<String> tipoProduto;
    @FXML private TextField titulo, autorBanda, categoria, unidades, paginas, anoPublicacao, valor;
    @FXML private TableView<Produto> tabelaProdutos;
    @FXML private TableColumn<Produto, String> tituloCol, autorCol, categoriaCol, unidadesCol, valorCol;
    @FXML private TableColumn<Produto, Integer> paginasCol, anoCol;

    private LivroService livroService;
    private DiscoService discoService;
    private ObservableList<Produto> produtosOL;
    private UserService userService;
    private ConcreteProdutoFactory factory;

    @FXML
    public void initialize() {
        userService = new UsuarioServiceImpl();
        livroService = new LivroServiceImpl();
        discoService = new DiscoServiceImpl();
        factory = new ConcreteProdutoFactory();
        produtosOL = FXCollections.observableArrayList();

        tipoProduto.setItems(FXCollections.observableArrayList("LIVRO", "DISCO"));

        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorCol.setCellValueFactory(new PropertyValueFactory<>("autorBanda"));
        categoriaCol.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        unidadesCol.setCellValueFactory(new PropertyValueFactory<>("qtdDisponivelAluguel"));
        paginasCol.setCellValueFactory(cellData -> {
            Produto produto = cellData.getValue();
            if (produto instanceof Livro livro) {
                return new SimpleIntegerProperty(livro.getQtdPaginas()).asObject();
            } else {
                return null;
            }
        });
        anoCol.setCellValueFactory(cellData -> {
            Object item = cellData.getValue();
            if (item instanceof Livro livro) {
                return new SimpleIntegerProperty(livro.getAnoPublicacao()).asObject();
            }
            return null; // ou null, se preferir
        });
        valorCol.setCellValueFactory(new PropertyValueFactory<>("valorAluguel"));

        tabelaProdutos.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> mostrarDetalhesProduto(newVal));

        buscar.textProperty().addListener((obs, oldVal, newVal) -> filtrarProdutos(newVal));

        carregarProdutos();
        tipoProduto.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean isDisco = "DISCO".equals(newVal);
            paginas.setDisable(isDisco);
            anoPublicacao.setDisable(isDisco);
        });
    }

    private void carregarProdutos() {
        try {
            List<Produto> todos = FXCollections.observableArrayList();
            todos.addAll(livroService.listarTodos());
            todos.addAll(discoService.listarTodos());

            produtosOL.setAll(todos);
            tabelaProdutos.setItems(produtosOL);
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao carregar produtos", e.getMessage());
        }
    }

    private void filtrarProdutos(String termo) {
        String filtro = termo.toLowerCase().trim();
        if (filtro.isEmpty()) {
            tabelaProdutos.setItems(produtosOL);
        } else {
            String filtroNormalizado = normalizar(filtro);

            List<Produto> filtrados = produtosOL.stream().filter(p -> {
                boolean tituloMatch = normalizar(p.getTitulo()).contains(filtroNormalizado);
                boolean categoriaMatch = normalizar(p.getCategoria()).contains(filtroNormalizado);
                boolean autorBandaMatch;

                if (p instanceof Livro livro) {
                    autorBandaMatch = normalizar(livro.getAutor()).contains(filtroNormalizado);
                } else if (p instanceof Disco disco) {
                    autorBandaMatch = normalizar(disco.getNomeBanda()).contains(filtroNormalizado);
                } else {
                    autorBandaMatch = false;
                }
                return tituloMatch || categoriaMatch || autorBandaMatch;
            }).toList();
            tabelaProdutos.setItems(FXCollections.observableArrayList(filtrados));
        }
        limparCampos();
        tabelaProdutos.getSelectionModel().clearSelection();
    }

    private void mostrarDetalhesProduto(Produto produto) {
        if (produto != null) {
            titulo.setText(produto.getTitulo());
            categoria.setText(produto.getCategoria());
            tipoProduto.setValue(produto instanceof Livro ? "LIVRO" : "DISCO");

            if (produto instanceof Livro livro) {
                autorBanda.setText(livro.getAutor());
                unidades.setText(String.valueOf(livro.getQtdDisponivelAluguel()));
                paginas.setText(String.valueOf(livro.getQtdPaginas()));
                anoPublicacao.setText(String.valueOf(livro.getAnoPublicacao()));
                valor.setText(String.valueOf(livro.getValorAluguel()));
            } else if (produto instanceof Disco disco) {
                autorBanda.setText(disco.getNomeBanda());
                unidades.setText(String.valueOf(disco.getQtdDisponivelAluguel()));
                paginas.setText(""); // ou "0", caso queira exibir algo padrão
                valor.setText(String.valueOf(disco.getValorAluguel()));
            }
        } else {
            limparCampos();
        }
    }

    private void limparCampos() {
        titulo.clear();
        autorBanda.clear();
        categoria.clear();
        unidades.clear();
        paginas.clear();
        anoPublicacao.clear();
        valor.clear();
        tipoProduto.getSelectionModel().clearSelection();
        paginas.setDisable(false);
        anoPublicacao.setDisable(false);
    }

    @FXML
    void adicionarButton(ActionEvent event) {
        try {
            Produto novo = criarProdutoDosCampos();

            if (novo instanceof Livro) livroService.cadastrar((Livro) novo);
            else discoService.cadastrar((Disco) novo);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto adicionado com sucesso!");
            carregarProdutos();
            limparCampos();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.WARNING, "Erro de validação", e.getMessage());
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao adicionar produto", e.getMessage());
        }
    }

    @FXML
    void atualizarButton(ActionEvent event) {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um produto.");
            return;
        }

        try {
            Produto atualizado = criarProdutoDosCampos();
            atualizado.setId(selecionado.getId());
            if (tipoProduto.getValue().equals("LIVRO")) {
                livroService.alterarEstoque((Livro) atualizado);
            } else {
                discoService.alterarEstoque((Disco) atualizado);
            }

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto atualizado com sucesso!");
            carregarProdutos();
            limparCampos();
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.WARNING, "Erro de validação", e.getMessage());
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao atualizar produto", e.getMessage());
        }
    }

    @FXML
    void deletarButton(ActionEvent event) {
        Produto selecionado = tabelaProdutos.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um produto.");
            return;
        }

        try {
            if (selecionado instanceof Livro) livroService.excluir((Livro) selecionado);
            else discoService.excluir((Disco) selecionado);

            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto removido com sucesso!");
            carregarProdutos();
            limparCampos();
        } catch (RuntimeException e) {
            showAlert(Alert.AlertType.ERROR, "Erro ao remover produto", e.getMessage());
        }
    }

    private Produto criarProdutoDosCampos() {
        String tipo = tipoProduto.getValue();
        if (tipo == null) throw new IllegalArgumentException("Selecione o tipo do produto");

        String tituloTxt = titulo.getText().trim();
        String autorTxt = autorBanda.getText().trim();
        String categoriaTxt = categoria.getText().trim();
        int unidadesNum = Integer.parseInt(unidades.getText().trim());
        int paginasNum;
        int anoNum;
        if (tipo.equalsIgnoreCase("LIVRO")) {
            paginasNum = Integer.parseInt(paginas.getText().trim());
            anoNum = Integer.parseInt(anoPublicacao.getText().trim());
        } else {
            paginasNum = 0;
            anoNum = 0;
        }

        double valorNum = Double.parseDouble(valor.getText().trim());

        return factory.criarProduto(
                tipo,
                tituloTxt,
                autorTxt,
                categoriaTxt,
                unidadesNum,
                paginasNum,
                anoNum,
                valorNum
        );
    }

    private void showAlert(Alert.AlertType tipo, String titulo, String msg) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Navegação
    public void alugueisButton() { ProjetoDudu.alugueis(); }
    public void clientesButton() { ProjetoDudu.clientes(); }
    public void relatoriosButton() { ProjetoDudu.relatorios(); }
    public void sairButton() {
        userService.deslogar();
        ProjetoDudu.telaLogin();
    }
}
