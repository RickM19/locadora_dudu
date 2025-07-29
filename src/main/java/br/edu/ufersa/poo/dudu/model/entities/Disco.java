package br.edu.ufersa.poo.dudu.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DISCO")
public class Disco extends Produto {
    @Column(nullable=true, length=50)
    private String nomeBanda;

    // construtor
	public Disco(String nomeBanda, String estilo, String titulo, int qtdExemplares, double valorAluguel) {
		super(titulo,estilo,qtdExemplares,valorAluguel);
        setNomeBanda(nomeBanda);
    }

	public Disco(){}

    // getters e setters
	public String getAutorBanda() { return nomeBanda; }
	public String getTipo() {
		return "DISCO";
	}
    public String getNomeBanda() {
		return nomeBanda;
	}

	public void setNomeBanda(String nomeBanda) {
		if(nomeBanda==null || nomeBanda.isEmpty())
			throw new IllegalArgumentException("O nome da banda não pode ser nulo ou vazio.");
		this.nomeBanda = nomeBanda;
	}
}
