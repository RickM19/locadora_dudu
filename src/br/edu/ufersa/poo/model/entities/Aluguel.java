package br.edu.ufersa.poo.model.entities;

public class Aluguel {
	//Atributos
	private int idAluguel;
	private int idCliente;
	private String dataInicio;
	private String dataFim;
	private double valorTotal;
	private boolean finalizado;
	
	//Getters
	public int getIdAluguel() {
		return this.idAluguel;
	}
	public int getIdCliente() {
		return this.idCliente;
	}
	public String getDataInicio() {
		return this.dataInicio;
	}
	public String getDataFim() {
		return this.dataFim;
	}
	public double getValorTotal() {
		return this.valorTotal;
	}
	public boolean getFinalizado() {
		return this.finalizado;
	}
	
	//Setters
	public void setIdAluguel(int idAluguel) {
		if (idAluguel >= 0) this.idAluguel = idAluguel;
	}
	public void setIdCliente(int idCliente) {
		if (idCliente >= 0) this.idCliente = idCliente;
	}
	public void setDataInicio(String dataInicio) {
		if (dataInicio != null) this.dataInicio = dataInicio;
	}
	public void setDataFim(String dataFim) {
		if (dataFim != null) this.dataFim = dataFim;
	}
	public void setValorTotal(double valorTotal) {
		if (valorTotal >= 0) this.valorTotal = valorTotal;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	//Construtores
	public Aluguel(int idAluguel, int idCliente, String dataInicio, String dataFim, double valorTotal, boolean finalizado) {
		setIdAluguel(idAluguel);
		setIdCliente(idCliente);
		setDataInicio(dataInicio);
		setDataFim(dataFim);
		setValorTotal(valorTotal);
		setFinalizado(finalizado);
	}
	
	//Métodos
}
