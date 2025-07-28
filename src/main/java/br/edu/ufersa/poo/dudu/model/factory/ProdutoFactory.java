package br.edu.ufersa.poo.dudu.model.factory;

import br.edu.ufersa.poo.dudu.model.entities.Disco;
import br.edu.ufersa.poo.dudu.model.entities.Livro;
import br.edu.ufersa.poo.dudu.model.entities.Produto;

public class ProdutoFactory {
    public Produto criarProduto(String tipo) {
        if (tipo.equalsIgnoreCase("LIVRO")) {
            return new Livro();
        } else if (tipo.equalsIgnoreCase("DISCO")) {
            return new Disco();
        }
        throw new IllegalArgumentException("Tipo desconhecido: " + tipo);
    }
}
