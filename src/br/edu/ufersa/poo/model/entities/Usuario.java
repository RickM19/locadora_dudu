package br.edu.ufersa.poo.model.entities;

public class Usuario {
	private int id;
	private String nomeUsuario;
	private String email;
	private String senha;
	private boolean isAdmin;
	
	//Getters
	public int getId() {
		return id;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public String email() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	//Setters
	public void setId(int id) {
		if(id >= 0)
			this.id = id;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		if(nomeUsuario != null && !nomeUsuario.isEmpty())
			this.nomeUsuario = nomeUsuario;
	}
	public void setEmail(String email) {
		if(email != null && !email.isEmpty())
			this.email = email;
	}
	public  void setSenha(String senha) {
		if(senha != null && !senha.isEmpty())
			this.senha = senha;
		else
			System.out.println("A senha é um campo obrigatório!");
	}
	public void setIsAdmin(String nomeUsuario) {
		if(nomeUsuario != null && nomeUsuario.equals("Dudu"))
			isAdmin = true;
		else
			isAdmin = false;
	}
	public Usuario(int id, String nomeUsuario, String email, String senha) {
		setId(id);
		setNomeUsuario(nomeUsuario);
		setEmail(email);
		setSenha(senha);
		setIsAdmin(nomeUsuario);
	}

	public void cadastrarUsuario(String nomeUsuario, String email, String senha) {
		for (Usuario u : BancoSimulado.usuarios) {
			boolean isSame = u.getNomeUsuario().equals(nomeUsuario);
			if (isSame) {
				System.out.println("Ja existe um usuário cadastrado com esse nome!");
				return;
			}
				
		}
			int id = BancoSimulado.idGenerator;
			Usuario novoUser = new Usuario(id, nomeUsuario, email, senha);
			BancoSimulado.usuarios.add(novoUser);
			BancoSimulado.idGenerator++;
			System.out.println("Cadastro realizado com sucesso!");
	}
	
	public void excluirUsuario(int id) {
		if(id < 0) {
			System.out.println("ID inválido!");
			return;
		}
		
		BancoSimulado.usuarios.removeIf(u -> u.getId() == id);
		System.out.println("Usuário excluido com sucesso!");
	}
	
	public void alterarSenha(String novaSenha) {
		setSenha(senha);
	}
}
