package br.edu.ufersa.poo.dudu.model.entities;

import br.edu.ufersa.poo.dudu.model.enums.TipoUsuario;
import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 30)
	private String nomeUsuario;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 30)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private TipoUsuario tipoUsuario;
	
	//Getters
	public long getId() {
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
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	
	//Setters

	public void setNomeUsuario(String nomeUsuario) {
		if(nomeUsuario == null || nomeUsuario.isEmpty())
			throw new IllegalArgumentException("Nome de usuário é obrigatório!");
		this.nomeUsuario = nomeUsuario;
	}
	public void setEmail(String email) {
		if(email == null || email.isEmpty())
			throw new IllegalArgumentException("O Email é um campo obrigatório!");
		this.email = email;
	}
	public  void setSenha(String senha) {
		if(senha == null || senha.isEmpty())
			throw new IllegalArgumentException("A senha é um campo obrigatório!");
		this.senha = senha;
	}
	public void setTipoUsuario(String nomeUsuario) {
		if(nomeUsuario == null || nomeUsuario.equals("Dudu"))
			tipoUsuario = TipoUsuario.ADMIN;
		else
			tipoUsuario = TipoUsuario.COMUM;
	}
	public Usuario(){};

	public Usuario(String nomeUsuario, String email, String senha) {
		setNomeUsuario(nomeUsuario);
		setEmail(email);
		setSenha(senha);
		setTipoUsuario(nomeUsuario);
	}

}
