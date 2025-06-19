package br.edu.ufersa.poo.model.entities;

public class ItemAlugado {
	//Atributos
	private int idAluguel;
	private int idItem;
	private boolean tipo;
	private String nomeItem;
	private double valorAluguel;
	
	//Getters
	public int getIdAluguel() {
		return this.idAluguel;
	}
	public int getIdItem() {
		return this.idItem;
	}
	public boolean getTipo() {
		return this.tipo;
	}
	public String getNomeItem() {
		return this.nomeItem;
	}
	public double getValorAluguel() {
		return this.valorAluguel;
	}
	
	//Setters
	public void setIdAluguel(int idAluguel) {
		if(idAluguel >= 0) this.idAluguel = idAluguel;
	}
	public void setIdItem(int idItem) {
		if(idItem >= 0) this.idItem = idItem;
	}
	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	public void setNomeItem(String nomeItem) {
		if(nomeItem != null) this.nomeItem = nomeItem;
	}
	public void setValorAluguel(double valorAluguel) {
		if(valorAluguel >= 0) this.valorAluguel = valorAluguel;
	}
	
	//Construtores
	public ItemAlugado(int idAluguel, int idItem, boolean tipo, String nomeItem, double valorAluguel) {
		setIdAluguel(idAluguel);
		setIdItem(idItem);
		setTipo(tipo);
		setNomeItem(nomeItem);
		setValorAluguel(valorAluguel);
	}
	
	//Métodos

}
