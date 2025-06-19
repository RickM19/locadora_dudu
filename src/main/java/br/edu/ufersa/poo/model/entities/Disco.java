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
	public void cadastrar(String nomeBanda, String estilo, String titulo, int qtdExemplares, int qtdParaAluguel, double valorAluguel) {
		for(Disco d: BancoSimulado.discos){
			if(d.getNomeBanda().equals(nomeBanda) && d.getTitulo().equals(titulo)){
				System.out.println("Disco existente.");
				return;
				}
			}
		int id=BancoSimulado.idGenerator;
		Disco novoDisco=new Disco(id,nomeBanda,estilo,titulo,qtdExemplares,qtdParaAluguel,valorAluguel);
		BancoSimulado.discos.add(novoDisco);
		BancoSimulado.idGenerator++;;
	}
	
	public void alugar() {
		if(getQtdParaAluguel()<=0) {
			System.out.println("Discos indisponíveis para aluguel.");
			return;
		}
		
		this.qtdParaAluguel--;
	}
	
	public void devolver() {
		if(getQtdParaAluguel()<getQtdExemplares()) {
			this.qtdParaAluguel++;
			System.out.println("Disco adicionado ao estoque.");
			}
	}
	
	public void excluir(int id) {
		if(id<0) {
			System.out.println("Entrada inválida.");
			return;
			}
		BancoSimulado.discos.removeIf(d -> d.getIdDisco() == id);
		System.out.println("Disco excluido.");
	}
	
	public void alterarEstoque(int id, int valor) {
		boolean encontrado=false;
		for(Disco d:BancoSimulado.discos) {
			if(d.getIdDisco()==id) {
				d.setQtdExemplares(valor);
				encontrado=true;
				System.out.println("Estoque alterado.");
				return;
				}
		}
			if(!encontrado)
				System.out.println("Disco não encontrado.");
		
	}
}
