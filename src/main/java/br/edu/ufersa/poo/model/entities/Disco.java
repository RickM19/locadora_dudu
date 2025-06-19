package br.edu.ufersa.poo.model.entities;

public class Disco {
	private int idDisco;
	private String nomeBanda;
	private String estilo;
	private String titulo;
	private int qtdExemplares;
	private int qtdParaAluguel;
	private double valorAluguel;
	
	// construtor
	public Disco(int id, String nomeBanda, String estilo, String titulo, int qtdExemplares, int qtdParaAluguel, double valorAluguel) {
		setIdDisco(id);
		setNomeBanda(nomeBanda);
		setEstilo(estilo);
		setTitulo(titulo);
		setQtdExemplares(qtdExemplares);
		setQtdParaAluguel(qtdParaAluguel);
		setValorAluguel(valorAluguel);
	}

    // getters e setters
     
	public int getIdDisco() {
		return idDisco;
	}

	public void setIdDisco(int idDisco) {
		if(idDisco<0) {
			System.out.println("Entrada inválida");
			return;
		}
		this.idDisco = idDisco;
	}

	public String getNomeBanda() {
		return nomeBanda;
	}

	public void setNomeBanda(String nomeBanda) {
		if(nomeBanda!=null) this.nomeBanda = nomeBanda;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		if(estilo!=null) this.estilo = estilo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if(titulo!=null) this.titulo = titulo;
	}

	public int getQtdExemplares() {
		return qtdExemplares;
	}

	public void setQtdExemplares(int qtdExemplares) {
		if(qtdExemplares<0) {
			System.out.println("Entrada inválida");
			return;
		}
		this.qtdExemplares = qtdExemplares;
	}

	public int getQtdParaAluguel() {
		return qtdParaAluguel;
	}

	public void setQtdParaAluguel(int qtdParaAluguel) {
		if(qtdParaAluguel<0) {
			System.out.println("Entrada inválida");
			return;
		}
		this.qtdParaAluguel = qtdParaAluguel;
	}

	public double getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(double valorAluguel) {
		if(valorAluguel<0.0) {
			System.out.println("Entrada inválida");
			return;
		}
		this.valorAluguel = valorAluguel;
	}

	//metodos

}
