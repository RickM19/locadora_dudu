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
	public void cadastrar(String nome, String cpf, String endereco){
		for(Cliente c: BancoSimulado.clientes){
			if(c.getCpf().equals(cpf)){
				System.out.println("Cliente existente.");
				return;
				}
			}
		Cliente novoCliente=new Cliente(nome,cpf,endereco);
		BancoSimulado.clientes.add(novoCliente);
		System.out.println("Novo cliente " + novoCliente.getNome() + " cadastrado.");
	}

	public void excluir(String cpf){
		if (cpf == null) {
	        System.out.println("CPF inválido.");
	        return;
	        }
		boolean removido = BancoSimulado.clientes.removeIf(c -> c.getCpf().equals(cpf));
		if(removido)
			System.out.println("CPF " + cpf + " removido.");
		
		}
	
}
