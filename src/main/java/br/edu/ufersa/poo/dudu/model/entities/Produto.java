package br.edu.ufersa.poo.dudu.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Produtos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 40)
    private String titulo;

    @Column(nullable = false, length = 30)
    private String categoria;

    @Column(nullable = false)
    private int qtdExemplares;

    @Column(nullable = false)
    private int qtdDisponivelAluguel;

    @Column(nullable = false)
    private double valorAluguel;

    // getters
    public long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getCategoria() {
        return categoria;
    }
    public int getExemplares() { return qtdExemplares; }
    public int getQtdDisponivelAluguel() { return qtdDisponivelAluguel; }
    public double getValorAluguel() { return valorAluguel; }

    // setters
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty())
            throw new IllegalArgumentException("O título é obrigatório!");
        this.titulo = titulo;
    }
    public void setQtdExemplares(int qtdExemplares) {
        if (qtdExemplares <= 0)
            throw new IllegalArgumentException("Quantidade inserida inválida!");
        this.qtdExemplares = qtdExemplares;
    }
    public void setQtdDisponivelAluguel(int qtd) {
        if(qtd > qtdExemplares || qtd < 0)
            throw new IllegalArgumentException("Quantidade disponivel inválida!");
        qtdDisponivelAluguel = qtd;
    }
    public void setValorAluguel(double valorAluguel) {
        if (valorAluguel <= 0)
            throw new IllegalArgumentException("Valor inválido!");
        this.valorAluguel = valorAluguel;
    }
    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty())
            throw new IllegalArgumentException("Genero é um campo obrigatório!");
        this.categoria = categoria;
    }
    public void alugar() {
        if(qtdDisponivelAluguel <= 0)
            throw new IllegalStateException("Quantidade de livros insuficientes para aluguel!");
        qtdDisponivelAluguel--;
    }
    public void devolver(){
        if(qtdDisponivelAluguel >= qtdExemplares) {
            throw new IllegalStateException("Tentativa de devolução inválida!");
        }
        qtdDisponivelAluguel++;
    }
    public Produto(){};
    public Produto(String titulo,String categoria, int qtdExemplares, double valorAluguel){
        setTitulo(titulo);
        setCategoria(categoria);
        setQtdExemplares(qtdExemplares);
        setValorAluguel(valorAluguel);
        qtdDisponivelAluguel = this.qtdExemplares;
    }
}
