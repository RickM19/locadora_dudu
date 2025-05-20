package br.edu.ufersa.poo.model.entities;


public class Livro {
	private int id;
	private String titulo;
	private int anoPublicacao;
	private String genero;
	private String autor;
	private int qtdPaginas;
	private int qtdExemplares;
	private int qtdDisponivelAluguel;
	private double valorAluguel;
	
	
	//Getters
	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getAutor() {
		return autor;
	}
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	public String getGenero() {
		return genero;
	}
	public int getQtdPaginas() {
		return qtdPaginas;
	}
	public double getValorAluguel() {
		return valorAluguel;
	}
	
	
	
	//Setters
	public void setId(int id) {
		if (id >= 0) {
			this.id = id;
		}
	}
	public void setTitulo(String titulo) {
		if (titulo != null && titulo.length() != 0) {
			this.titulo = titulo;
		}
	}
	public void setAutor(String autor) {
		if (autor != null && autor.length() != 0) {
			this.autor = autor;
		}
	}
	public void setAnoPublicacao(int anoPublicacao) {
		if (anoPublicacao > 0)
			this.anoPublicacao = anoPublicacao;
	}
	public void setGenero(String genero) {
		if (genero != null && genero.length() != 0) {
			this.genero = genero;
		}
	}
	public void setQtdPaginas(int qtdPaginas) {
		if (qtdPaginas > 0) {
			this.qtdPaginas = qtdPaginas;
		}
	}
	public void setQtdExemplares(int qtdExemplares) {
		if (qtdExemplares > 0) {
			this.qtdExemplares = qtdExemplares;
		}
	}
	public void setValorAluguel(double valorAluguel) {
		if (valorAluguel > 0) {
			this.valorAluguel = valorAluguel;
		}
	}
	

	public Livro(
			int id, 
			String titulo, 
			String autor, 
			int anoPublicacao, 
			String genero, 
			int qtdPaginas, 
			int qtdExemplares, 
			double valorAluguel
			) 
	{
		setId(id);
		setTitulo(titulo);
		setAutor(autor);
		setAnoPublicacao(anoPublicacao);
		setGenero(genero);
		setQtdPaginas(qtdPaginas);
		setQtdExemplares(qtdExemplares);
		setValorAluguel(valorAluguel);
		qtdDisponivelAluguel = this.qtdExemplares;

	}

	public void cadastrarLivro(
			String titulo, 
			String autor, 
			int anoPublicacao, 
			String genero, 
			int qtdPaginas, 
			int qtdExemplares, 
			double valorAluguel
			) 
	{
		boolean exists = false;
		for (Livro l : BancoSimulado.livros) {
			boolean isSame = l.getTitulo().equals(titulo) && l.getAutor().equals(autor);
			if (isSame)
				exists = true;
		}

		if (!exists) {
			int id = BancoSimulado.idGenerator;
			Livro novoLivro = new Livro(id, titulo,autor, anoPublicacao, genero, qtdPaginas, qtdExemplares, valorAluguel);
			BancoSimulado.livros.add(novoLivro);
			BancoSimulado.idGenerator++;
		}
	}
	
	public void alugar() {
		if(qtdDisponivelAluguel > 0) {
			qtdDisponivelAluguel--;
		}
	}
	
	public void devolver() {
		if(qtdDisponivelAluguel < qtdExemplares) {
			qtdDisponivelAluguel++;
		}
	}
	
	public void excluirLivro(int id) {
		if(id >= 0) {
			BancoSimulado.livros.removeIf(l -> l.getId() == id);
		}
	}
	
	public void alterarEstoque(int id, int novaQtd) {
		for(Livro l: BancoSimulado.livros) {
			if(l.getId() == id) {
				l.setQtdExemplares(novaQtd);
			}		
		}
	}
}
