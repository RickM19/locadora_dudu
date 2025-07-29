package br.edu.ufersa.poo.dudu.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Aluguel")
public class Aluguel {
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAluguel;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "id_produto", nullable = false)
	private Produto itemAlugado;

	@Column(nullable = false)
	private LocalDate dataInicio;

	@Column(nullable = false)
	private LocalDate dataFim;

	@Column(nullable = false)
	private double valorTotal;

	@Column(nullable = false)
	private boolean finalizado;

	//Getters
	public long getIdAluguel() {
		return this.idAluguel;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public Produto getItemAlugado() { return this.itemAlugado; }
	public LocalDate getDataInicio() {
		return this.dataInicio;
	}
	public LocalDate getDataFim() {
		return this.dataFim;
	}
	public double getValorTotal() {
		return this.valorTotal;
	}
	public boolean getFinalizado() {
		return this.finalizado;
	}

	//Setters
	public void setCliente(Cliente cliente) {
		if (cliente != null) this.cliente = cliente;
		else throw new IllegalArgumentException("O Cliente está vazio.");
	}
	public void setItemAlugado(Produto itemAlugado) {
		if(itemAlugado != null) this.itemAlugado = itemAlugado;
		else throw new IllegalArgumentException("O Produto está vazio.");
	}
	public void setDataInicio(LocalDate dataInicio) {
		if (dataInicio != null) this.dataInicio = dataInicio;
		else throw new IllegalArgumentException("A data de inicio está vazia.");
	}
	public void setDataFim(LocalDate dataFim) {
		if (dataFim != null) this.dataFim = dataFim;
		else throw new IllegalArgumentException("A data de fim está vazia.");
	}
	public void setValorTotal(double valorTotal) {
		if (valorTotal >= 0) this.valorTotal = valorTotal;
		else throw new IllegalArgumentException("O valor total está vazio");
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	//Construtores
	public Aluguel(){}
	public Aluguel(Cliente cliente, Produto itemAlugado, LocalDate dataInicio, LocalDate dataFim, double valorTotal,
				   boolean finalizado) {
		setCliente(cliente);
		setItemAlugado(itemAlugado);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setValorTotal(valorTotal);
		setFinalizado(finalizado);
	}
}
