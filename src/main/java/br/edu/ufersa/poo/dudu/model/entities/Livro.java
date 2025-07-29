package br.edu.ufersa.poo.dudu.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LIVRO")
public class Livro extends Produto {
	@Column(nullable = true)
	private int anoPublicacao;

	@Column(nullable = true, length = 40)
	private String autor;

	@Column(nullable = true)
	private int qtdPaginas;

	//Getters
	public String getTipo() {
		return "LIVRO";
	}
	public String getAutor() {
		return autor;
	}
	public String getAutorBanda() { return autor; }
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public int getQtdPaginas() { return qtdPaginas; }
	
	//Setters
	public void setAutor(String autor) {
		if (autor == null || autor.isEmpty())
			throw new IllegalArgumentException("O autor é um campo obrigatório!");
		this.autor = autor;


	}
	public void setAnoPublicacao(int anoPublicacao) {
		if (anoPublicacao <= 0)
			throw new IllegalArgumentException("Ano de publicação inválido!");
		this.anoPublicacao = anoPublicacao;
	}
	public void setQtdPaginas(int qtdPaginas) {
		if (qtdPaginas <= 0)
			throw new IllegalArgumentException("Quantidade de páginas precisa ser maior que zero!");
		this.qtdPaginas = qtdPaginas;

	}

	public Livro(){};
	public Livro(
			String titulo, 
			String autor, 
			int anoPublicacao, 
			String genero, 
			int qtdPaginas,
			int qtdExemplares,
			double valorAluguel
			) 
	{
		super(titulo, genero, qtdExemplares, valorAluguel);
		setAutor(autor);
		setAnoPublicacao(anoPublicacao);
		setQtdPaginas(qtdPaginas);
	}


}
