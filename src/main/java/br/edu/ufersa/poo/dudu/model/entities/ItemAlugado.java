package br.edu.ufersa.poo.dudu.model.entities;

import br.edu.ufersa.poo.dudu.model.enums.TipoProduto;
import jakarta.persistence.*;

@Entity
@Table(name="Itens_Alugados")
public class ItemAlugado {
	//Atributos
	@ManyToOne
	@JoinColumn(name = "id_aluguel", nullable = false)
	private Aluguel aluguel;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto produto;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoProduto tipoProduto;

	@Column(nullable = false, length = 50)
	private String nomeItem;

	@Column(nullable = false)
	private double valorAluguel;
	
	//Getters
	public Aluguel getAluguel() {
		return this.aluguel;
	}
	public Produto getProduto() {
		return this.produto;
	}
	public TipoProduto getTipo() {
		return this.tipoProduto;
	}
	public String getNomeItem() {
		return this.nomeItem;
	}
	public double getValorAluguel() {
		return this.valorAluguel;
	}
	
	//Setters
	public void setAluguel(Aluguel aluguel) {
		if(aluguel != null) this.aluguel = aluguel;
		else throw new IllegalArgumentException("O aluguel está vazio");
	}
	public void setProduto(Produto produto) {
		if(produto != null) this.produto = produto;
		else throw new IllegalArgumentException("O id do item está vazio");
	}
	public void setTipo(TipoProduto tipo) {
		this.tipoProduto = tipo;
	}
	public void setNomeItem(String nomeItem) {
		if(nomeItem != null && !nomeItem.isEmpty()) this.nomeItem = nomeItem;
		else throw new IllegalArgumentException("O nome do item está nulo ou vazio");
	}
	public void setValorAluguel(double valorAluguel) {
		if(valorAluguel >= 0) this.valorAluguel = valorAluguel;
		else throw new IllegalArgumentException("O valor do aluguel deve ser positivo");
	}
	
	//Construtores
	public ItemAlugado(){}
	public ItemAlugado(Aluguel aluguel, Produto produto, TipoProduto tipo, String nomeItem, double valorAluguel) {
		setAluguel(aluguel);
		setProduto(produto);
		setTipo(tipo);
		setNomeItem(nomeItem);
		setValorAluguel(valorAluguel);
	}
	
	//Métodos

}
