package br.edu.ufersa.poo.model.entities;

public class Cliente {
	private String nome;
	private String cpf;
	private String endereco;
	

	//construtor
	public Cliente(String nome, String cpf, String endereco) {
		setNome(nome);
		setCpf(cpf);
		setEndereco(endereco);
	}

	//getters e setters
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		if(nome!=null) 
			this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		if(cpf != null)
			this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		if(endereco!=null)
			this.endereco = endereco;
	}
	
	//metodos

	
}
