package br.edu.ufersa.poo.dudu.model.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Alugueis")
public class Aluguel {
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idAluguel;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private Cliente cliente;

	@Column(nullable = false)
	private LocalDate dataInicio;

	@Column(nullable = false)
	private LocalDate dataFim;

	@Column(nullable = false)
	private double valorTotal;

	@Column(nullable = false)
	private boolean finalizado;

	@OneToMany(mappedBy = "aluguel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemAlugado> itensAlugados;

	//Getters
	public long getIdAluguel() {
		return this.idAluguel;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
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
	public List<ItemAlugado> getItensAlugados() {
		return itensAlugados;
	}

	//Setters
	public void setCliente(Cliente cliente) {
		if (cliente != null) this.cliente = cliente;
		else throw new IllegalArgumentException("O id do cliente está vazio.");
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
	public void setItensAlugados(List<ItemAlugado> itensAlugados) {
		if (itensAlugados != null && !itensAlugados.isEmpty()) this.itensAlugados = itensAlugados;
		else throw new IllegalArgumentException("Os itens alugados estão vazios");
	}

	//Construtores
	public Aluguel(){}
	public Aluguel(Cliente cliente, LocalDate dataInicio, LocalDate dataFim, double valorTotal, boolean finalizado,
				   List<ItemAlugado> itensAlugados) {
		setCliente(cliente);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setValorTotal(valorTotal);
		setFinalizado(finalizado);
		setItensAlugados(itensAlugados);
	}
	
	//Métodos

	
}
