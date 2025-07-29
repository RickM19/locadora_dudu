package br.edu.ufersa.poo.dudu.model.enums;

public enum TipoProduto {
    DISCO ("Disco"),
    LIVRO ("Livro");

    private final String descricao;
    public String getDescricao() { return descricao; }

    TipoProduto(String descricao) { this.descricao = descricao; }
}
