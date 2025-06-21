package br.edu.ufersa.poo.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Clientes")
public class Cliente {
    @Id
    @Column(nullable=false,unique=true,length=11)
    private String cpf;

    @Column(nullable=false,length=100)
	private String nome;

    @Column(nullable=false,length=150)
	private String endereco;
	
	//construtor
	public Cliente(String nome, String cpf, String endereco) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}

	public Cliente(){}

	//getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null || nome.isEmpty())
			throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if(cpf == null || cpf.isEmpty())
			throw new IllegalArgumentException("O cpf não pode ser nulo ou vazio.");
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if(endereco==null || endereco.isEmpty())
			throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio.");
		this.endereco = endereco;
	}
}
